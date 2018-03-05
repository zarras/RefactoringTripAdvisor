package RefactoringDetectors;

import gr.uom.java.ast.ASTReader;
import gr.uom.java.ast.ClassObject;
import gr.uom.java.ast.ParameterObject;
import gr.uom.java.ast.SystemObject;
import gr.uom.java.ast.AbstractMethodDeclaration;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JFrame;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import UI.MainWindow;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/*	This class implements the detection of Introduce Parameter Object opportunities
 *	in the user's code. It searches for methods with long parameter lists.
 */
public class IntroduceParameterObjectIdentification extends RefactoringDetectorMethodSubject 
{

	private ArrayList<ClassObject> declaringClasses;
	private ArrayList<AbstractMethodDeclaration> candidateMethods;
	private JLabel lblNewLabel_1;
	
	
	public IntroduceParameterObjectIdentification()
	{
		super("Introduce Parameter Object Opportunities");
	}
	
	@Override
	public JFrame getDetectorFrame(Object[] dataForIdentification) {
		MainWindow map = (MainWindow) dataForIdentification[0];
		selectionInfo = map.getSelectionInfo();
		
		beginASTParsing();
		
		if(candidateMethods.isEmpty())
			opportunitiesFound = false;
		
		JLabel lblNewLabel = new JLabel("Opportunites for Introduce Parameter Object");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 522, 30);
		mainPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(" found in the following methods");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 39, 522, 30);
		mainPanel.add(lblNewLabel_1);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 80, 522, 449);
		list.setModel(computeListModel());
		mainPanel.add(list);
		
		return mainFrame;
	}

	@Override
	public boolean opportunitiesFound() {
		return opportunitiesFound;
	}

	private DefaultListModel computeListModel()
	{
		DefaultListModel listModel = new DefaultListModel();
		
		for (int i = 0; i < candidateMethods.size(); i++) {
			String classAndMethodString = declaringClasses.get(i).getName() + "::" + candidateMethods.get(i).getName();
			listModel.addElement(classAndMethodString);
		}

		return listModel;
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
						
						final SystemObject systemObject = ASTReader.getSystemObject();
						
						identifySubjects(identifyScope(), systemObject);
						
						identifyOpportunities(systemObject, 1);
						
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
		if(!subjectList.isEmpty())
		{
			for(AbstractMethodDeclaration methodObject : subjectList)
			{
				
				ListIterator<ParameterObject> parameterIterator =  methodObject.getParameterListIterator();
				int parameterCounter = 0;

				while(parameterIterator.hasNext())
				{
					parameterCounter++;
					parameterIterator.next();
				}

				if (parameterCounter>=threshold)
				{
					candidateMethods.add(methodObject);
					ClassObject declaringClass = systemObject.getClassObject(methodObject.getClassName());
					declaringClasses.add(declaringClass);
				}
			}
		}
	}
}
