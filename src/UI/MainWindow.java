package UI;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jface.viewers.IStructuredSelection;

import DataHandling.PackageExplorerSelection;
import MapRegions.MainRefactoringRegion;
import MapRegions.MainSmellsRegion;

/*	This class handles the creation of the main window of Refactoring Trip Advisor.
 * 	The RTA application starts by clicking on the corresponding option under Eclipse's
 * 	"Refactor" menu. That is the reason why this class needs to implement the IWorkbenchWindowActionDelegate
 * 	interface. This class creates the starting region graph as well as all the RTA menus and submenus.
 * 	It also contains the Package Explorer listener and updates a PackageExplorerSelection object
 * 	every time the user changes his selection. 
 */
public class MainWindow implements IWorkbenchWindowActionDelegate{

	private JFrame tripAdvisorFrame;
	private JPanel contentPane;
	private JButton regionsButton, smellsButton;
	
	private MainRefactoringRegion refactoringRegion;
	private MainSmellsRegion smellsRegion;
	
	private IWorkbenchPage page;
	private PackageExplorerSelection selectionInfo = new PackageExplorerSelection();
	/**
	 * @wbp.parser.entryPoint
	 */
	public void run(IAction action) {
		initialize();
	}
	
	public void initialize() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");			
		} catch (Exception e) {
			e.printStackTrace();
		}	

		page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		//The two window sizes for the welcome screen and the graphs
		Dimension welcomeScrDimension = new Dimension(500, 420);
		Dimension graphsDimension = new Dimension(1020, 760);
		
		//Initial frame settings
		tripAdvisorFrame = new JFrame();
		tripAdvisorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tripAdvisorFrame.setResizable(false);
		tripAdvisorFrame.setSize(welcomeScrDimension);
		tripAdvisorFrame.setTitle("Refactoring Trip Advisor");
		tripAdvisorFrame.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/repair.png")));
		tripAdvisorFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
				dispose();
            }
		});
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);

		//Refactoring Regions button initialization
		regionsButton = new JButton("Go to Refactoring Regions");
		regionsButton.setToolTipText("Click to visit the refactoring regions");
		regionsButton.setBounds(80, 290, 160, 50);
		regionsButton.addActionListener(e -> {
			refactoringRegion = new MainRefactoringRegion(this);
			tripAdvisorFrame.setResizable(true);
			tripAdvisorFrame.setSize(graphsDimension);
			tripAdvisorFrame.setContentPane(refactoringRegion.getRegionGraph().getMyGraphComponent());
			tripAdvisorFrame.revalidate();
		});
		contentPane.add(regionsButton);
		
		//Smells Region button initialization
		smellsButton = new JButton("Go to Smells Overview");
		smellsButton.setToolTipText("Click to visit the basic smells graph.");
		smellsButton.setBounds(260, 290, 160, 50);
		smellsButton.addActionListener(e -> {
			refactoringRegion = new MainRefactoringRegion(this);
			smellsRegion = new MainSmellsRegion(this);
			tripAdvisorFrame.setResizable(true);
			tripAdvisorFrame.setSize(graphsDimension);
			tripAdvisorFrame.setContentPane(smellsRegion.getRegionGraph().getMyGraphComponent());
			tripAdvisorFrame.revalidate();
		});
		contentPane.add(smellsButton);
				
		ImageIcon screenIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/welcomeScreen.png")));
		JLabel picLabel = new JLabel(screenIcon);
		picLabel.setBounds(0, 0, 500, 420);
		contentPane.add(picLabel);
		tripAdvisorFrame.setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		tripAdvisorFrame.setJMenuBar(menuBar);
		
		JMenu mnTool = new JMenu("Tool");
		menuBar.add(mnTool);
		
		JMenuItem mntmTest = new JMenuItem("Close");
		mntmTest.addActionListener(e -> tripAdvisorFrame.dispose());
		mnTool.add(mntmTest);
		
		JMenu mnNewMenu = new JMenu("Navigate");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmHome = new JMenuItem("Home");
		mntmHome.addActionListener(e -> {
			tripAdvisorFrame.setResizable(false);
			tripAdvisorFrame.setSize(welcomeScrDimension);
			tripAdvisorFrame.setContentPane(contentPane);
			tripAdvisorFrame.revalidate();
		});
		mnNewMenu.add(mntmHome);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAboutRefactoring = new JMenuItem("About Refactoring");
		mntmAboutRefactoring.addActionListener(e ->	new HelpContentsWindow("About Refactoring", "/images/AboutRefactoring.png").setVisible(true));
		mnHelp.add(mntmAboutRefactoring);
		
		JMenuItem mntmHowToUse = new JMenuItem("How to use");
		mntmHowToUse.addActionListener(e -> new HelpContentsWindow("How to use Refactoring Trip Advisor", "/images/HowToUse.png").setVisible(true));
		mnHelp.add(mntmHowToUse);
		
		JMenuItem mntmRefactoringDetectors = new JMenuItem("Refactoring Detectors");
		mntmRefactoringDetectors.addActionListener(e ->	new HelpContentsWindow("Refactoring Detectors", "/images/RefactoringDetectors.png").setVisible(true));
		mnHelp.add(mntmRefactoringDetectors);
		
		JMenuItem mntmSmellsOverview = new JMenuItem("Smells Overview");
		mntmSmellsOverview.addActionListener(e -> new HelpContentsWindow("Smells Overview", "/images/SmellsOverview.png").setVisible(true));
		mnHelp.add(mntmSmellsOverview);
			
		tripAdvisorFrame.setVisible(true);
	}
	
	/* This method handles the package (or file) selection requested by the 
	 * Refactoring Trip Advisor when the identification button for a refactoring
	 * is clicked.
	 */
	public void selectionChanged(IAction action, ISelection selection) {

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			Object element = structuredSelection.getFirstElement();
			IJavaProject javaProject = null;
			if(element instanceof IJavaProject) {
				javaProject = (IJavaProject)element;

				selectionInfo.setSelections(null, null, null, null, null);
			}
			else if(element instanceof IPackageFragmentRoot) {
				IPackageFragmentRoot packageFragmentRoot = (IPackageFragmentRoot)element;
				javaProject = packageFragmentRoot.getJavaProject();
				
				selectionInfo.setSelections(packageFragmentRoot, null, null, null, null);
			}
			else if(element instanceof IPackageFragment) {
				IPackageFragment packageFragment = (IPackageFragment)element;
				javaProject = packageFragment.getJavaProject();
				
				selectionInfo.setSelections(null, packageFragment, null, null, null);
			}
			else if(element instanceof ICompilationUnit) {
				ICompilationUnit compilationUnit = (ICompilationUnit)element;
				javaProject = compilationUnit.getJavaProject();
				
				selectionInfo.setSelections(null, null, compilationUnit, null, null);
			}
			else if(element instanceof IType) {
				IType type = (IType)element;
				javaProject = type.getJavaProject();
				
				selectionInfo.setSelections(null, null, null, type, null);
			}
			else if(element instanceof IMethod) {
				IMethod method = (IMethod)element;
				javaProject = method.getJavaProject();
				
				selectionInfo.setSelections(null, null, null, null, method);
			}
			if(javaProject != null && !javaProject.equals(selectionInfo.getSelectedProject())) {
				selectionInfo.setSelectedProject(javaProject);
			}
		}		
	}
	
	public void dispose() {
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void init(IWorkbenchWindow window) {		
	}
	
	public JFrame getTripAdvisorFrame(){
		return tripAdvisorFrame;
	}
	
	public PackageExplorerSelection getSelectionInfo() {
		return selectionInfo;
	}
	
	public IWorkbenchPage getIWorkbenchPage(){
		return page;
	}
	
	public MainRefactoringRegion getMainRefactoringRegion(){
		return refactoringRegion;
	}
}