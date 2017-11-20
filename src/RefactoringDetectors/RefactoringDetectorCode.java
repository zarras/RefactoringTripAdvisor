package RefactoringDetectors;

import DataHandling.PackageExplorerSelection;
import gr.uom.java.ast.ASTReader;
import gr.uom.java.ast.AbstractMethodDeclaration;
import gr.uom.java.ast.ClassObject;
import gr.uom.java.ast.LocalVariableDeclarationObject;
import gr.uom.java.ast.MethodObject;
import gr.uom.java.ast.SystemObject;
import gr.uom.java.jdeodorant.refactoring.views.SliceAnnotation;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class RefactoringDetectorCode {

	//fields
	protected JFrame mainFrame;
	protected JPanel mainPanel;
	protected PackageExplorerSelection selectionInfo;
	protected boolean opportunitiesFound;
	
	
	
	//abstract methods
	abstract JFrame getDetectorFrame(Object[] dataForIdentification);
	abstract boolean opportunitiesFound();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	private final Set<ClassObject> classObjectsToBeExamined; // = new LinkedHashSet<ClassObject>();
	private final Set<AbstractMethodDeclaration> methodObjectsToBeExamined; // = new LinkedHashSet<AbstractMethodDeclaration>();
	
	private Set<ClassObject> identifyScope(SystemObject systemObject)
	{
		
		if(selectionInfo.getSelectedPackageFragmentRoot() != null) {
			classObjectsToBeExamined.addAll(systemObject.getClassObjects(selectionInfo.getSelectedPackageFragmentRoot()));
		}
		else if(selectionInfo.getSelectedPackageFragment() != null) {
			classObjectsToBeExamined.addAll(systemObject.getClassObjects(selectionInfo.getSelectedPackageFragment()));
		}
		else if(selectionInfo.getSelectedCompilationUnit() != null) {
			classObjectsToBeExamined.addAll(systemObject.getClassObjects(selectionInfo.getSelectedCompilationUnit()));
		}
		else if(selectionInfo.getSelectedType() != null) {
			classObjectsToBeExamined.addAll(systemObject.getClassObjects(selectionInfo.getSelectedType()));
		}
		else if(selectionInfo.getSelectedMethod() != null) {
			AbstractMethodDeclaration methodObject = systemObject.getMethodObject(selectionInfo.getSelectedMethod());
			if(methodObject != null) {
				ClassObject declaringClass = systemObject.getClassObject(methodObject.getClassName());
				if(declaringClass != null && !declaringClass.isEnum() && !declaringClass.isInterface() && methodObject.getMethodBody() != null)
					methodObjectsToBeExamined.add(methodObject);
			}
		}
		else {
			classObjectsToBeExamined.addAll(systemObject.getClassObjects());
		}
		
		return classObjectsToBeExamined;
	}
	
	
	*/
	
}
