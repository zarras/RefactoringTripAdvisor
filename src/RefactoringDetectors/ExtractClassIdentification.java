package RefactoringDetectors;

import gr.uom.java.distance.CurrentSystem;
import gr.uom.java.distance.DistanceMatrix;
import gr.uom.java.distance.ExtractClassCandidateGroup;
import gr.uom.java.distance.ExtractClassCandidateRefactoring;
import gr.uom.java.distance.MySystem;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ListSelectionModel;

import gr.uom.java.ast.ASTReader;
import gr.uom.java.ast.ClassObject;
import gr.uom.java.ast.SystemObject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import UI.MainWindow;

/*	This class implements the detection of Extract Class opportunities
 *	in the user's code. It uses the hierarchical agglomerative clustering
 *	algorithm implemented by JDeodorant.
 */
public class ExtractClassIdentification  extends RefactoringDetectorClassSubject{

	private ArrayList<ExtractClassCandidateRefactoring> candidates;
	private ArrayList<String> itemArray;
	private ExtractClassCandidateGroup[] extractTable;

	public ExtractClassIdentification()
	{
		super("Extract Class Opportunities");
	}
	
	@Override
	public JFrame getDetectorFrame(Object[] dataForIdentification) {
		
		MainWindow map = (MainWindow) dataForIdentification[0];
		selectionInfo = map.getSelectionInfo();
		
		computeExtractTable();
		
		if((extractTable==null)||(extractTable.length<=1))
			opportunitiesFound = false;
		
		itemArray = new  ArrayList<String>();
		candidates = new ArrayList<ExtractClassCandidateRefactoring>();
		for (int j = 0; j < extractTable.length; j++) {
			if((!extractTable[j].getSource().equals("current system"))&&(extractTable[j].getCandidates()!=null))
			{
				ArrayList<ExtractClassCandidateRefactoring> tempCands = extractTable[j].getCandidates();
				
				for(ExtractClassCandidateRefactoring cand : tempCands)
				{
					candidates.add(cand);
				}
			}
		}
		
		JLabel lblChooseA = new JLabel("Choose an opportunity to view");
		lblChooseA.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseA.setFont(new Font("Arial", Font.BOLD, 16));
		lblChooseA.setBounds(10, 25, 370, 25);
		mainPanel.add(lblChooseA);
		
		JList list_1 = new JList();
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setBounds(430, 81, 354, 209);
		final DefaultListModel list1Model = new DefaultListModel();
		list_1.setModel(list1Model);
		mainPanel.add(list_1);
		
		JList list_2 = new JList();
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_2.setBounds(430, 337, 354, 223);
		final DefaultListModel list2Model = new DefaultListModel();
		list_2.setModel(list2Model);
		mainPanel.add(list_2);
		
		final JList list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String value = (String)list.getSelectedValue();
				Iterator<ExtractClassCandidateRefactoring> iter = candidates.iterator();
				Iterator<String> iter2 = itemArray.iterator();
				boolean flag = true;
				
				list1Model.clear();
				list2Model.clear();
				
				while((iter2.hasNext())&&(flag==true))
				{
					if(value.equals(iter2.next()))
					{
						ExtractClassCandidateRefactoring candidate = iter.next();
						Set<VariableDeclaration> fields = candidate.getExtractedFieldFragments();
						Set<MethodDeclaration> methods = candidate.getExtractedMethods();
						
						for(VariableDeclaration field : fields)
						{
							list1Model.addElement(field.toString());
						}
						for(MethodDeclaration method : methods)
						{
							list2Model.addElement(method.getName().toString());
						}
						
						flag = false;
					}
					else 
					{
						iter.next();
					}
				}
			}
		});
		list.setSelectedIndex(0);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 81, 370, 479);
		list.setModel(computeListModel());
		mainPanel.add(list);
		
		JLabel lblTheCandidateFields = new JLabel("its candidate fields and methods");
		lblTheCandidateFields.setHorizontalAlignment(SwingConstants.CENTER);
		lblTheCandidateFields.setFont(new Font("Arial", Font.BOLD, 16));
		lblTheCandidateFields.setBounds(10, 47, 370, 23);
		mainPanel.add(lblTheCandidateFields);
				
		JLabel lblFields = new JLabel("Fields:");
		lblFields.setFont(new Font("Arial", Font.BOLD, 16));
		lblFields.setHorizontalAlignment(SwingConstants.CENTER);
		lblFields.setBounds(430, 46, 354, 25);
		mainPanel.add(lblFields);
		
		JLabel lblMethods = new JLabel("Methods:");
		lblMethods.setHorizontalAlignment(SwingConstants.CENTER);
		lblMethods.setFont(new Font("Arial", Font.BOLD, 16));
		lblMethods.setBounds(430, 301, 354, 25);
		mainPanel.add(lblMethods);	
		
		return mainFrame;
	}

	@Override
	public boolean opportunitiesFound()
	{
		return opportunitiesFound;
	}
	
	private DefaultListModel computeListModel()
	{
		DefaultListModel listModel = new DefaultListModel();
		int counter = 1;
		
		for(ExtractClassCandidateRefactoring cand : candidates)
		{
			String item = "Opportunity " + counter + " (Source class: " + cand.getSource() + ")";
			counter++;
			listModel.addElement(item);
			itemArray.add(item);
		}
				

		return listModel;
	}
	
	public void computeExtractTable() {
		extractTable = null;
		if(selectionInfo.getSelectedProject() == null)
			System.out.println("null selected project - should not be printed");
		else {
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
									new ASTReader(selectionInfo.getSelectedProject(), monitor);
								}
							});
						}
						SystemObject systemObject = ASTReader.getSystemObject();
						
						identifySubjects(identifyScope(), systemObject);
						identifyOpportunities(systemObject, ps);
								
					} catch (InvocationTargetException e) {
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
	
	private void identifyOpportunities(SystemObject systemObject, IProgressService ps)
	{
		final Set<String> classNamesToBeExamined = new LinkedHashSet<String>();
		try {
			for (ClassObject classObject : subjectList) {
				if (!classObject.isEnum())
					classNamesToBeExamined.add(classObject.getName());
			}
			MySystem system = new MySystem(systemObject, true);
			final DistanceMatrix distanceMatrix = new DistanceMatrix(system);

			ps.busyCursorWhile(new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					distanceMatrix.generateDistances(monitor);
				}
			});

			final List<ExtractClassCandidateRefactoring> extractClassCandidateList = new ArrayList<ExtractClassCandidateRefactoring>();

			ps.busyCursorWhile(new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					extractClassCandidateList.addAll(
							distanceMatrix.getExtractClassCandidateRefactorings(classNamesToBeExamined, monitor));
				}
			});
			HashMap<String, ExtractClassCandidateGroup> groupedBySourceClassMap = new HashMap<String, ExtractClassCandidateGroup>();
			for (ExtractClassCandidateRefactoring candidate : extractClassCandidateList) {
				if (groupedBySourceClassMap.keySet().contains(candidate.getSourceEntity())) {
					groupedBySourceClassMap.get(candidate.getSourceEntity()).addCandidate(candidate);
				} else {
					ExtractClassCandidateGroup group = new ExtractClassCandidateGroup(candidate.getSourceEntity());
					group.addCandidate(candidate);
					groupedBySourceClassMap.put(candidate.getSourceEntity(), group);
				}
			}
			for (String sourceClass : groupedBySourceClassMap.keySet()) {
				groupedBySourceClassMap.get(sourceClass).groupConcepts();
			}

			extractTable = new ExtractClassCandidateGroup[groupedBySourceClassMap.values().size() + 1];
			ExtractClassCandidateGroup currentSystem = new ExtractClassCandidateGroup("current system");
			currentSystem.setMinEP(new CurrentSystem(distanceMatrix).getEntityPlacement());
			extractTable[0] = currentSystem;
			int counter = 1;
			for (ExtractClassCandidateGroup candidate : groupedBySourceClassMap.values()) {
				extractTable[counter] = candidate;
				counter++;
			}
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}