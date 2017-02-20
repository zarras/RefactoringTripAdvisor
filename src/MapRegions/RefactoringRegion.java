package MapRegions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import relationshipEdges.Relationship;
import DataHandling.GraphProperties;
import DataHandling.RefactoringOptions;
import RefactoringDetectors.RefactoringDetector;
import UI.MainWindow;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

/*	This superclass handles the initialization of each different 
 * 	refactoring region of the map. It implements the coloring listener
 * 	as well as the show/hide button listener for the external refactorings.
 * 	Each region subclass must implement the methods that handle the 
 * 	graph creation and the 3-slide information window popup.
 */
public abstract class RefactoringRegion {

	protected mxCell[] vertices;
	protected mxCell[] externalVertices;
	protected ArrayList<Relationship> relationshipList;
	protected GraphProperties graph;
	protected JPanel graphPanel;

	protected Display display;
	protected MainWindow mainWindow;
	protected IWorkbenchPage page;
	
	protected Object[] dataForIdentification;
	protected RefactoringOptions refactoringOptions;
	
	protected JLabel refactoringLabel;
	
	public RefactoringRegion(MainWindow window, int verticesNo, int extVerticesNo)
	{
		display = PlatformUI.getWorkbench().getDisplay();
		
		relationshipList = new ArrayList<Relationship>();
		
		graphPanel = new JPanel();
		graphPanel.setSize(new Dimension(1020, 700));
		graphPanel.setBackground(Color.WHITE);
		graphPanel.setLayout(new BorderLayout(0, 0));
		
		graph = new GraphProperties("<html>" + "Left Click: Display Refactoring Succession Path" + "<br>" + "Right Click: Display Information about this Refactoring" + "</html>");
		
		vertices = new mxCell[verticesNo];
		if(extVerticesNo != 0)
			externalVertices = new mxCell[extVerticesNo];	
		
		mainWindow = window;
		page = window.getIWorkbenchPage();
		refactoringOptions = new RefactoringOptions(mainWindow,null,null);
		
		createGraph(graph.getParent());
		
		mxIGraphLayout layout = new mxParallelEdgeLayout(graph.getMyGraph());
		layout.execute(graph.getParent());
		
		final mxGraphComponent graphComponent = graph.getMyGraphComponent();
		graphComponent.getGraphControl().addMouseListener(
				new MouseAdapter() 
				{
					public void mouseReleased(MouseEvent e)
					{
						mxCell cell = (mxCell) graphComponent.getCellAt(e.getX(), e.getY());
						if(cell != null){
							if (cell.isVertex()) {
								if(e.getButton() == MouseEvent.BUTTON1) {//left click
									graph.colorizeNeighborhood(cell, relationshipList);
								}
								else if(e.getButton() == MouseEvent.BUTTON3) {//right click
									graph.colorizeNeighborhood(cell, relationshipList);
									graphNodePopUp(cell);
								}	
							} else if(cell.isEdge())
								if(e.getButton() == MouseEvent.BUTTON1) {//left click
									edgePopUp(cell);
								}
						}
					}			
				} 
		);		
		graphPanel.add(graphComponent, BorderLayout.CENTER);
				
		refactoringLabel = new JLabel("");
		refactoringLabel.setFont(new Font("Arial", Font.BOLD, 15));
		refactoringLabel.setOpaque(true);
		refactoringLabel.setBackground(Color.LIGHT_GRAY);
		graphPanel.add(refactoringLabel, BorderLayout.SOUTH);
		
		JRadioButton externalToggleButton = new JRadioButton("Show/Hide refactorings from external regions");
		externalToggleButton.setSelected(true);
		updateGraph(true);
		externalToggleButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED)
					updateGraph(true);
				else if(event.getStateChange() == ItemEvent.DESELECTED)
					updateGraph(false);	
			}
		});
		externalToggleButton.setToolTipText("Click to show/hide relations with refactorings from other regions.");
		externalToggleButton.setFocusPainted(false);
		externalToggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		graphPanel.add(externalToggleButton, BorderLayout.NORTH);
	}
	
	public JPanel getGraphPanel() {
		return graphPanel;
	}
	
	public void setPopUpWindowData(String title, String motivationPath, String examplePath, String idPath, RefactoringDetector detector, Object[] detectorData) {
		/* This function sets the data required for the popup window when you right click a node in the graph */
		
		refactoringOptions.setRefactoringTitle(title);
		refactoringOptions.setMotivationPath(motivationPath);
		refactoringOptions.setExamplePath(examplePath);
		refactoringOptions.setIdentificationPath(idPath);
		refactoringOptions.setDetector(detector);
		refactoringOptions.setDetectorData(detectorData);
	}
	
	public void createGraph(Object p) {
		mxGraph g = graph.getMyGraph();
		
		g.getModel().beginUpdate();
		/* -------  BEGIN UPDATE -------*/
		try {
			addMainVertices(g, p);
			addMainVerticesRelations();
			for(Relationship r  : relationshipList)
				g.insertEdge(p, r.getID(), null, r.getSource(), r.getDestination(), r.getType());
		} finally {	
		/* -------  END UPDATE --------*/
			g.getModel().endUpdate();
		}	
	}
	
	public void updateGraph(boolean showExternal)
	{
		mxGraph g = graph.getMyGraph();
		Object p = graph.getParent();
		
		g.getModel().beginUpdate();
		/* -------  BEGIN UPDATE -------*/
		try {
			if(showExternal) {
				addExternalVertices(g, p);		
				addExternalVerticesRelations();
				for(Relationship r  : relationshipList)
					if(r.isExternal())
						g.insertEdge(p, r.getID(), null, r.getSource(), r.getDestination(), r.getType());
			} else {
				g.removeCells(externalVertices, true);
			
				for(Relationship r  : relationshipList)
					if(r.isExternal())
						relationshipList.remove(r);
				g.refresh();
			}
		/* -------  END UPDATE --------*/
		} 
		finally {
			g.getModel().endUpdate();
		}
		graph.initColors();
	}
	
	public abstract void graphNodePopUp(mxCell cell);
	public abstract void edgePopUp(mxCell cell);
	public abstract void addMainVertices(mxGraph g, Object p);
	public abstract void addMainVerticesRelations();
	public abstract void addExternalVertices(mxGraph g, Object p);
	public abstract void addExternalVerticesRelations();
}