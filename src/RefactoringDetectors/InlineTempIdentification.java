package RefactoringDetectors;

import gr.uom.java.ast.ASTReader;
import gr.uom.java.ast.AbstractMethodDeclaration;
import gr.uom.java.ast.LocalVariableDeclarationObject;
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
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import ParsingHelpers.VariableObject;
import UI.MainWindow;

/*	This class implements the detection of Inline Temp opportunities
 *	in the user's code. It searches for local variables that are 
 *	only set once and contain the result of a method call.
 */
public class InlineTempIdentification extends RefactoringDetectorMethodSubject {

	
	private ArrayList<VariableObject> opportunities;
	
	public InlineTempIdentification()
	{
		super("Inline Temp Opportunities");
	}
	
	@Override
	public JFrame getDetectorFrame(Object[] dataForIdentification) {
		MainWindow map = (MainWindow) dataForIdentification[0];
		selectionInfo = map.getSelectionInfo();
		
		beginASTParsing();

		if(opportunities.isEmpty())
			opportunitiesFound = false;
		
		final JList list_1 = new JList();
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setBounds(434, 120, 350, 441);
		final DefaultListModel list2Model = new DefaultListModel();
		list_1.setModel(list2Model);
		mainPanel.add(list_1);
		
		final JList list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				list2Model.clear();
				String value = (String) list.getSelectedValue();
				String[] splitted = value.split("::");
								
				for (int i = 0; i < opportunities.size(); i++) {
					if((opportunities.get(i).getClassObject().getName().equals(splitted[0]))
						&&((opportunities.get(i).getMethodObject().getName().equals(splitted[1]))))
					{
						list2Model.addElement(opportunities.get(i).getType().toString() + " " + opportunities.get(i).getVariable().getName());
					}
				}
				
				list_1.setModel(list2Model);
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 120, 350, 441);
		list.setModel(computeListModel(opportunities));
		mainPanel.add(list);

		JLabel lblChooseAMethod = new JLabel("Choose a method to view its Inline Temp Opportunities");
		lblChooseAMethod.setFont(new Font("Arial", Font.BOLD, 16));
		lblChooseAMethod.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAMethod.setBounds(10, 26, 774, 27);
		mainPanel.add(lblChooseAMethod);
		
		JLabel lblMethods = new JLabel("Methods:");
		lblMethods.setFont(new Font("Arial", Font.BOLD, 16));
		lblMethods.setHorizontalAlignment(SwingConstants.CENTER);
		lblMethods.setBounds(10, 82, 350, 27);
		mainPanel.add(lblMethods);
		
		JLabel lblNewLabel = new JLabel("Temporary Variables:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(434, 82, 350, 27);
		mainPanel.add(lblNewLabel);
		
		return mainFrame;
	}

	@Override
	public boolean opportunitiesFound() {
		return opportunitiesFound;
	}
	
	private DefaultListModel computeListModel(ArrayList<VariableObject> tempVariables)
	{
		DefaultListModel listModel = new DefaultListModel();
		ArrayList<String> listItems = new ArrayList<String>();
		boolean flag = false;
		
		for (int i = 0; i < tempVariables.size(); i++) {
			String classMethodString = tempVariables.get(i).getClassObject().getName() + "::" + tempVariables.get(i).getMethodObject().getName();
			flag = false;
			
			for (int j = 0; j < listItems.size(); j++) {
				if(listItems.get(j).equals(classMethodString))
				{
					flag = true;
				}
			}
			
			if(flag == false)
			{
				listItems.add(classMethodString);
			}
		}
		
		for (int i = 0; i < listItems.size(); i++) {
			listModel.addElement(listItems.get(i));
		}

		return listModel;
	}
	
	private void beginASTParsing()
	{
		if(selectionInfo.getSelectedProject() == null)
			System.out.println("null selected project - should not be printed");
		else 
		{
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				public void run(){
					try {
						IWorkbench wb = PlatformUI.getWorkbench();
						IProgressService ps = wb.getProgressService();
							ps.busyCursorWhile(new IRunnableWithProgress() {
								public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
									new ASTReader(selectionInfo.getSelectedProject(), null);
								}
							});
						
						final SystemObject systemObject = ASTReader.getSystemObject();
						
						identifySubjects(identifyScope(), systemObject);
						identifyOpportunities(systemObject);
							
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
	
	private void identifyOpportunities(SystemObject systemObject)
	{
		ArrayList<VariableObject> variables = new ArrayList<VariableObject>();;
		
		if(!subjectList.isEmpty())
		{
			for(AbstractMethodDeclaration methodObject : subjectList)
			{																
				List<LocalVariableDeclarationObject> localVariables = methodObject.getLocalVariableDeclarations();
				
				List<Statement> statements = methodObject.getMethodDeclaration().getBody().statements();
				
				for (LocalVariableDeclarationObject local : localVariables) {		
					Expression initializer = local.getVariableDeclaration().getInitializer();															
					
					variables.add(new VariableObject(systemObject.getClassObject(methodObject.getClassName()),methodObject,local.getType(),local,initializer));
				}		
				
				findAssignments(statements, variables);
			}
		}
		
		opportunities = new ArrayList<VariableObject>();
		
		for (int i = 0; i < variables.size(); i++) {
			if((variables.get(i).getAssignments().size() == 1) 
					&&(variables.get(i).getAssignments().get(0) instanceof MethodInvocation))
			{
				opportunities.add(variables.get(i));
			}
		}
	}
	
	protected void findAssignments(List<Statement> statements, ArrayList<VariableObject> variables)
	{
		Expression expression;
		
		ArrayList<Statement> recursiveStatements = new ArrayList<Statement>();
		
		for (int i = 0; i < statements.size(); i++) {
			Statement statement = statements.get(i);		
			
			if(statement instanceof ExpressionStatement)
			{
				ExpressionStatement expStatement = (ExpressionStatement) statement;
				
				expression = expStatement.getExpression();
				
				if(expression instanceof Assignment)
				{
					Assignment assign = (Assignment) expression;
					
					String left = assign.getLeftHandSide().toString();
					
					for(int j=0; j<variables.size(); j++)
					{
						if(left.equals(variables.get(j).getVariable().getName()))
						{
							variables.get(j).addExpression(assign.getRightHandSide());
						}
					}
				}
			}
			else if(statement instanceof ForStatement)
			{
				ForStatement forStatement = (ForStatement) statement;
				
				Statement forBody = forStatement.getBody();
				
				if(forBody instanceof Block)
				{
					Block forBlock = (Block) forBody;
					findAssignments(forBlock.statements(), variables);
				}
				else 
				{
					recursiveStatements.add(forBody);
					findAssignments(recursiveStatements, variables);
				}
			}
			else if(statement instanceof DoStatement)
			{
				DoStatement doStatement = (DoStatement) statement;
				
				Statement doBody = doStatement.getBody();
				
				if(doBody instanceof Block)
				{
					Block doBlock = (Block) doBody;
					findAssignments(doBlock.statements(), variables);
				}
				else 
				{
					recursiveStatements.add(doBody);
					findAssignments(recursiveStatements, variables);
				}
			}
			else if(statement instanceof WhileStatement)
			{
				WhileStatement whileStatement = (WhileStatement) statement;
				
				Statement whileBody = whileStatement.getBody();
				
				if(whileBody instanceof Block)
				{
					Block whileBlock = (Block) whileBody;
					findAssignments(whileBlock.statements(), variables);
				}
				else 
				{
					recursiveStatements.add(whileBody);
					findAssignments(recursiveStatements, variables);
				}
			}
			else if(statement instanceof IfStatement)
			{
				IfStatement ifStatement = (IfStatement) statement;
				
				Statement thenBody = ifStatement.getThenStatement();
				Statement elseBody = ifStatement.getElseStatement();
				
				if(thenBody instanceof Block)
				{
					Block thenBlock = (Block) thenBody;
					findAssignments(thenBlock.statements(), variables);
				}
				else 
				{
					recursiveStatements.add(thenBody);
					findAssignments(recursiveStatements, variables);
				}
				
				if(elseBody != null)
				{
					if(elseBody instanceof Block)
					{
						Block elseBlock = (Block) elseBody;
						findAssignments(elseBlock.statements(), variables);
					}
					else 
					{
						recursiveStatements.add(elseBody);
						findAssignments(recursiveStatements, variables);
					}
				}
			}
			else if(statement instanceof TryStatement)
			{
				TryStatement tryStatement = (TryStatement) statement;
				
				Block tryBody = tryStatement.getBody();
				Block finallyBody = tryStatement.getFinally();
				
				findAssignments(tryBody.statements(), variables);
				if(finallyBody != null)
					findAssignments(finallyBody.statements(), variables);
			}
		}
	}
}