package RefactoringDetectors;

import java.util.ArrayList;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;

import gr.uom.java.ast.ClassObject;
import gr.uom.java.ast.SystemObject;

public abstract class RefactoringDetectorClassSubject extends RefactoringDetector {
	
	protected ArrayList<ClassObject> subjectList;
	
	public RefactoringDetectorClassSubject(String title)
	{
		super(title);	
		
		listOfValidScopes.add(scopeType.PACKAGE_FRAGMENT_ROOT);
		listOfValidScopes.add(scopeType.PACKAGE_FRAGMENT);
		listOfValidScopes.add(scopeType.COMPILATION_UNIT);
		listOfValidScopes.add(scopeType.TYPE);
		listOfValidScopes.add(scopeType.NONE);
	}
	
	protected<T> void identifySubjects(T scopeRegion, SystemObject systemObject)
	{
		 subjectList = new ArrayList<ClassObject>();
		 if (scope != scopeType.NONE)
		 {
			 if (scope == scopeType.PACKAGE_FRAGMENT_ROOT){
				 subjectList.addAll(systemObject.getClassObjects((IPackageFragmentRoot) scopeRegion));
			 }
			 else if (scope == scopeType.PACKAGE_FRAGMENT){
				 subjectList.addAll(systemObject.getClassObjects((IPackageFragment) scopeRegion));
			 }
			 else if (scope == scopeType.COMPILATION_UNIT){
				 subjectList.addAll(systemObject.getClassObjects((ICompilationUnit) scopeRegion));
			 }
			 else if (scope == scopeType.TYPE){
				 subjectList.addAll(systemObject.getClassObjects((IType) scopeRegion)); 
			 }
		 }
		 else
		 {
			 subjectList.addAll(systemObject.getClassObjects());
		 }
	}
	
}
