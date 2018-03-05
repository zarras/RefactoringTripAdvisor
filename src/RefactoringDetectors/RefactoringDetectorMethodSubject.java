package RefactoringDetectors;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;

import gr.uom.java.ast.AbstractMethodDeclaration;
import gr.uom.java.ast.ClassObject;
import gr.uom.java.ast.MethodObject;
import gr.uom.java.ast.SystemObject;

public abstract class RefactoringDetectorMethodSubject extends RefactoringDetector {
	
	protected ArrayList<AbstractMethodDeclaration> subjectList;
	
	public RefactoringDetectorMethodSubject(String title)
	{
		super(title);	
		
		listOfValidScopes.add(scopeType.PACKAGE_FRAGMENT_ROOT);
		listOfValidScopes.add(scopeType.PACKAGE_FRAGMENT);
		listOfValidScopes.add(scopeType.COMPILATION_UNIT);
		listOfValidScopes.add(scopeType.TYPE);
		listOfValidScopes.add(scopeType.METHOD);
		listOfValidScopes.add(scopeType.NONE);
	}
		
	protected<T> void identifySubjects(T scopeRegion, SystemObject systemObject)
	{
		Set<ClassObject> classObjectsToBeExamined = new LinkedHashSet<ClassObject>();
		
		subjectList = new ArrayList<AbstractMethodDeclaration>();
		
		if (scope != scopeType.NONE)
		 {
			 if (scope == scopeType.PACKAGE_FRAGMENT_ROOT){
				 classObjectsToBeExamined.addAll(systemObject.getClassObjects((IPackageFragmentRoot) scopeRegion));
			 }
			 else if (scope == scopeType.PACKAGE_FRAGMENT){
				 classObjectsToBeExamined.addAll(systemObject.getClassObjects((IPackageFragment) scopeRegion));
			 }
			 else if (scope == scopeType.COMPILATION_UNIT){
				 classObjectsToBeExamined.addAll(systemObject.getClassObjects((ICompilationUnit) scopeRegion));
			 }
			 else if (scope == scopeType.TYPE){
				 classObjectsToBeExamined.addAll(systemObject.getClassObjects((IType) scopeRegion)); 
			 }
			 else if (scope == scopeType.METHOD){
				 AbstractMethodDeclaration methodObject = systemObject.getMethodObject((IMethod) scopeRegion);
				 ClassObject declaringClass = systemObject.getClassObject(methodObject.getClassName());
				 if(declaringClass != null && !declaringClass.isEnum() && !declaringClass.isInterface() && methodObject.getMethodBody() != null)
						subjectList.add(methodObject);
			 }
		 }
		 else
		 {
			 classObjectsToBeExamined.addAll(systemObject.getClassObjects());
		 }
	
		if(!classObjectsToBeExamined.isEmpty())
		{
			for(ClassObject classObject : classObjectsToBeExamined)
			{
				if(!classObject.isEnum() && !classObject.isInterface()) 
				{
					ListIterator<MethodObject> methodIterator = classObject.getMethodIterator();
					while(methodIterator.hasNext()) 
					{
						subjectList.add(methodIterator.next());
					}
				}
			}
		}
	}	
	
}
