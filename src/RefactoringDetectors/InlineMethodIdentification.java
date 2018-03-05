package RefactoringDetectors;

import gr.uom.java.ast.ASTReader;
import gr.uom.java.ast.AbstractMethodDeclaration;
import gr.uom.java.ast.ClassObject;
import gr.uom.java.ast.SystemObject;

import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import UI.MainWindow;

/*	This class implements the detection of Inline Method opportunities
 *	in the user's code. It searches for private methods containing a single
 *	statement whose prefix does not include "get","set","add" or "remove".
 */
public class InlineMethodIdentification extends RefactoringDetectorMethodSubject {

	private ArrayList<AbstractMethodDeclaration> candidateMethods;
	private ArrayList<ClassObject> declaringClasses;
		
	public InlineMethodIdentification()
	{
		super("Inline Method Opportunities");
	}
	
	@Override
	public JFrame getDetectorFrame(Object[] dataForIdentification) {
		MainWindow map = (MainWindow) dataForIdentification[0];	
		selectionInfo = map.getSelectionInfo();
		
		beginASTParsing();
	
		if(candidateMethods.isEmpty())
		{
			opportunitiesFound = false;
		}

		final JList list_1 = new JList();
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setBounds(434, 120, 350, 441);
		final DefaultListModel list2Model = new DefaultListModel();
		list_1.setModel(list2Model);
		mainPanel.add(list_1);
		
		final JList list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String value = (String) list.getSelectedValue();
				list2Model.clear();
			
				for (int i = 0; i < declaringClasses.size(); i++) {
					if(declaringClasses.get(i).getName().equals(value))
					{
						list2Model.addElement(candidateMethods.get(i).getName());
					}
				}
				
				list_1.setModel(list2Model);
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 120, 350, 441);
		list.setModel(computeListModel());
		mainPanel.add(list);

		JLabel lblChooseAMethod = new JLabel("Choose a class to view its Inline Method Opportunities");
		lblChooseAMethod.setFont(new Font("Arial", Font.BOLD, 16));
		lblChooseAMethod.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAMethod.setBounds(10, 26, 774, 27);
		mainPanel.add(lblChooseAMethod);
		
		JLabel lblMethods = new JLabel("Classes:");
		lblMethods.setFont(new Font("Arial", Font.BOLD, 16));
		lblMethods.setHorizontalAlignment(SwingConstants.CENTER);
		lblMethods.setBounds(10, 82, 350, 27);
		mainPanel.add(lblMethods);
		
		JLabel lblNewLabel = new JLabel("Methods:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(434, 82, 350, 27);
		mainPanel.add(lblNewLabel);
		
		return mainFrame;
	}
	
	private DefaultListModel computeListModel()
	{
		DefaultListModel listModel = new DefaultListModel();
		ArrayList<String> listItems = new ArrayList<String>();
		boolean flag = false;
		
		for (int i = 0; i < declaringClasses.size(); i++) {
			flag = false;
			
			for (int j = 0; j < listItems.size(); j++) {
				if(listItems.get(j).equals(declaringClasses.get(i).getName()))
				{
					flag = true;
				}
			}
			
			if(flag == false)
			{
				listItems.add(declaringClasses.get(i).getName());
			}
		}
		
		for (int i = 0; i < listItems.size(); i++) {
			listModel.addElement(listItems.get(i));
		}

		return listModel;
	}
	
	@Override
	public boolean opportunitiesFound() {
		return opportunitiesFound;
	}
	
	private void beginASTParsing()
	{
		candidateMethods = new ArrayList<AbstractMethodDeclaration>();
		declaringClasses = new ArrayList<ClassObject>();
		
		if(selectionInfo.getSelectedProject() == null)
			System.out.println("null selected project - should not be printed");
		else 
		{
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				public void run(){
					try {
						IWorkbench wb = PlatformUI.getWorkbench();
						IProgressService ps = wb.getProgressService();
						if(ASTReader.getSystemObject() != null && selectionInfo.getSelectedProject().equals(ASTReader.getExaminedProject())) {
							new ASTReader(selectionInfo.getSelectedProject(), ASTReader.getSystemObject(), null);
						}
						else {
							ps.busyCursorWhile(new IRunnableWithProgress() {
								public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
									new ASTReader(selectionInfo.getSelectedProject(), null);
								}
							});
						}
						int threshold = 1;
						final SystemObject systemObject = ASTReader.getSystemObject();
						
						identifySubjects(identifyScope(), systemObject);
						
						identifyOpportunities(systemObject, threshold);
						
					}
					catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	
	private void identifyOpportunities(SystemObject systemObject, int threshold)
	{
		if (!subjectList.isEmpty())
		{
			for(AbstractMethodDeclaration methodObject : subjectList)
			{
				
				ClassObject declaringClass = systemObject.getClassObject(methodObject.getClassName());
				List<IExtendedModifier> modifiers = methodObject.getMethodDeclaration().modifiers();
				
				boolean isPrivateOrProtected = false;
				
				for (int i = 0; i < modifiers.size(); i++) {
					if(modifiers.get(i) instanceof Modifier)
					{
						Modifier modifier = (Modifier) modifiers.get(i);
						
						if((modifier.isPrivate())||(modifier.isProtected()))
						{
							isPrivateOrProtected = true;
						}
					}
				}
				
				if (isPrivateOrProtected)
				{
					List<Statement> statements = methodObject.getMethodDeclaration().getBody().statements();
					
					if((statements.size() <= threshold)
							&&(!methodObject.getName().startsWith("get"))
							&&(!methodObject.getName().startsWith("set"))
							&&(!methodObject.getName().startsWith("add"))
							&&(!methodObject.getName().startsWith("remove")))
						{
							candidateMethods.add(methodObject);
							declaringClasses.add(declaringClass);
						}
				}
			}
		}
	}

}