package RefactoringDetectors;

import DataHandling.PackageExplorerSelection;
import gr.uom.java.ast.SystemObject;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.jdt.core.IParent;

import java.lang.reflect.*;

public abstract class RefactoringDetector {
	
	protected JFrame mainFrame;
	protected JPanel mainPanel;
	protected PackageExplorerSelection selectionInfo;
	protected boolean opportunitiesFound;
	protected ArrayList<scopeType> listOfValidScopes;
	protected HashMap<scopeType, Method> reflectionMap;
	protected scopeType scope;
	
	public RefactoringDetector(String title)
	{
		mainFrame = new JFrame();
		mainFrame.setTitle(title);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setLocation(new Point(200, 100));
		mainFrame.setSize(new Dimension(800, 600));
		mainFrame.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/repair.png")));
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainFrame.setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		opportunitiesFound = true;
		
		listOfValidScopes = new ArrayList<scopeType>();
		populateReflectionMap();
	}
	
	private void populateReflectionMap()
	{
		reflectionMap = new HashMap<scopeType, Method>();
		Class packageSelection;
		try {
			packageSelection = Class.forName("DataHandling.PackageExplorerSelection");
			Method selectionGetPackageFragmentRoot = packageSelection.getDeclaredMethod("getSelectedPackageFragmentRoot");
			Method selectionGetPackageFragment = packageSelection.getDeclaredMethod("getSelectedPackageFragment");
			Method selectionGetCompilationUnit = packageSelection.getDeclaredMethod("getSelectedCompilationUnit");
			Method selectionGetType = packageSelection.getDeclaredMethod("getSelectedType");
			Method selectionGetMethod = packageSelection.getDeclaredMethod("getSelectedMethod");
		
			reflectionMap.put(scopeType.PACKAGE_FRAGMENT_ROOT, selectionGetPackageFragmentRoot);
			reflectionMap.put(scopeType.PACKAGE_FRAGMENT, selectionGetPackageFragment);
			reflectionMap.put(scopeType.COMPILATION_UNIT, selectionGetCompilationUnit);
			reflectionMap.put(scopeType.TYPE, selectionGetType);
			reflectionMap.put(scopeType.METHOD, selectionGetMethod);
			reflectionMap.put(scopeType.NONE, null);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}
		
	protected<T extends IParent> T identifyScope() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException
	{
		scope = selectionInfo.getSelectedScope();
		
		Class packageSelection = Class.forName("PackageExplorerSelection");
		
		if (listOfValidScopes.contains(scope) && scope != scopeType.NONE)
		{
			return (T) reflectionMap.get(scope).invoke(selectionInfo);
		}
		else
		{
			scope = scopeType.NONE;
			return null;
		}
	}
	
	protected abstract <T> void identifySubjects(T scopeRegion, SystemObject systemObject);
	public abstract JFrame getDetectorFrame(Object[] dataForIdentification);
	public abstract boolean opportunitiesFound();
	
}