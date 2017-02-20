package MapRegions;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

import DataHandling.GraphProperties;
import UI.PopupWindow;
import UI.MainWindow;
import relationshipEdges.Relationship;

/*	This class is responsible for the creation of the main smells region graph
 *  as well as handling the popup information and actions when a node is clicked.
 */

public class MainSmellsRegion {
	
	private mxCell[] regions;
	private GraphProperties regionGraph;
	private ArrayList<Relationship> relationshipList;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	public MainSmellsRegion(MainWindow window){
		createSmellsRegionGraph(window);
	}
	
	private void createSmellsRegionGraph(MainWindow window){
		
		regions = new mxCell[11];
		regionGraph = new GraphProperties("<html>" + "Click to display information on how to resolve the smell." + "</html>");
		relationshipList = new ArrayList<Relationship>();
		
		Object parent = regionGraph.getParent();
		mxGraph g = regionGraph.getMyGraph();
		
		g.getModel().beginUpdate();
		try
		{
			addSmellsRegionVertices(g, parent);
			addSmellsRegionVerticesRelations(g, parent);
		} finally {
			g.getModel().endUpdate();
		}
		
		mxIGraphLayout layout = new mxParallelEdgeLayout(g);
		layout.execute(parent);

		regionGraph.getMyGraphComponent().getGraphControl().addMouseListener(new MouseAdapter()	{
			public void mouseReleased(MouseEvent e)	{
				mxCell cell = (mxCell) regionGraph.getMyGraphComponent().getCellAt(e.getX(), e.getY());
					
				if((cell != null)&&(cell.isVertex()))
					if(e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3)
						smellsVertexPopUp(cell, window);
			}
		});
	}
	
	private void addSmellsRegionVertices(mxGraph g, Object parent){
		regions[0] = (mxCell) g.insertVertex(parent, "s0", "Useless Class", 625, 20, 120, 50, "circle;fillColor=#00FFFF;fontSize=14");
		regions[1] = (mxCell) g.insertVertex(parent, "s1", "Useless Method", 625, 120, 120, 50, "circle;fillColor=#00FFFF;fontSize=14");
		regions[2] = (mxCell) g.insertVertex(parent, "s2", "Useless Field", 750, 220, 120, 50, "circle;fillColor=#00FFFF;fontSize=14");
		regions[3] = (mxCell) g.insertVertex(parent, "s3", "Duplicate Code", 500, 220, 120, 50, "circle;fillColor=#FF0000;fontSize=14");
		regions[4] = (mxCell) g.insertVertex(parent, "s4", "Feature Envy", 50, 320, 120, 50, "circle;fillColor=#D1FA8A;fontSize=14");
		regions[5] = (mxCell) g.insertVertex(parent, "s5", "Simple Primitive Obsession", 220, 320, 200, 50, "circle;fillColor=#D1FA8A;fontSize=14");
		regions[6] = (mxCell) g.insertVertex(parent, "s6", "Complex Type Code", 470, 320, 160, 50, "circle;fillColor=#D1FA8A;fontSize=14");
		regions[7] = (mxCell) g.insertVertex(parent, "s7", "Simple Type Code", 680, 320, 140, 50, "circle;fillColor=#D1FA8A;fontSize=14");
		regions[8] = (mxCell) g.insertVertex(parent, "s8", "Long Method", 330, 420, 120, 50, "circle;fillColor=#FF0000;fontSize=14");
		regions[9] = (mxCell) g.insertVertex(parent, "s9", "Large Class", 600, 520, 120, 50, "circle;fillColor=#FF0000;fontSize=14");
		regions[10] = (mxCell) g.insertVertex(parent, "s10", "Long Parameter List", 600, 640, 150, 50, "circle;fillColor=#FF0000;fontSize=14");
	}
	
	private void addSmellsRegionVerticesRelations(mxGraph g, Object parent){
		relationshipList.add(new Relationship(regions[0], regions[1], null, "se1"));
		relationshipList.add(new Relationship(regions[1], regions[2], null, "se2"));
		relationshipList.add(new Relationship(regions[1], regions[3], null, "se3"));
		relationshipList.add(new Relationship(regions[3], regions[4], null, "se4"));
		relationshipList.add(new Relationship(regions[3], regions[5], null, "se5"));
		relationshipList.add(new Relationship(regions[3], regions[6], null, "se6"));
		relationshipList.add(new Relationship(regions[3], regions[7], null, "se7"));
		relationshipList.add(new Relationship(regions[4], regions[8], null, "se8"));
		relationshipList.add(new Relationship(regions[5], regions[8], null, "se9"));
		relationshipList.add(new Relationship(regions[6], regions[8], null, "se10"));
		relationshipList.add(new Relationship(regions[7], regions[9], null, "se11"));
		relationshipList.add(new Relationship(regions[8], regions[9], null, "se12"));
		relationshipList.add(new Relationship(regions[9], regions[10], null, "se13"));
		for(Relationship r  : relationshipList)
			g.insertEdge(parent, r.getID(), null, r.getSource(), r.getDestination());
	}
	
	private void smellsVertexPopUp(mxCell cell, MainWindow window){
		
		String pathPrefix = "/SmellRegionView/images/";
		String image, title;
		JButton solution1, solution2, solution3, solution4;
		Dimension smellDim = new Dimension(1100, 760);
		
		switch(cell.getId()) {
			case "s0" :
				image = pathPrefix + "smellvertex-0.png";
				title = "Useless Class";
				break;
			case "s1" :
				image = pathPrefix + "smellvertex-1.png";
				title = "Useless Method";
				break;
			case "s2" :
				image = pathPrefix + "smellvertex-2.png";
				title = "Useless Field";
				break;
			case "s3" :
				image = pathPrefix + "smellvertex-3.png";
				title = "Duplicate Code";
				solution1 = addDataOrganizationListener("<html>Replace Magic Number<br />with Symbolic Constant</html>", window);
				buttons.add(solution1);
				solution2 = addMethodCompListener("Extract Method", window);
				buttons.add(solution2);
				solution3 = addGeneralizationImprovementListener("<html>Pull Up Field<br />Pull Up Method<br />Form Template Method</html>", window);
				buttons.add(solution3);
				solution4 = addFeatureMovementListener("Extract Class", window);
				buttons.add(solution4);
				break;
			case "s4" :
				image = pathPrefix + "smellvertex-4.png";
				title = "Feature Envy";
				solution1 = addFeatureMovementListener("Move Method", window);
				buttons.add(solution1);
				break;
			case "s5" :
				image = pathPrefix + "smellvertex-5.png";
				title = "Simple Primitive Obsession";
				solution1 = addDataOrganizationListener("<html>Replace Data Value<br />with Object</html>", window);
				buttons.add(solution1);
				break;
			case "s6" :
				image = pathPrefix + "smellvertex-6.png";
				title = "Complex Type Code";
				solution1 = addDataOrganizationListener("<html>Replace Type Code<br />with Subclasses</html>", window);
				buttons.add(solution1);
				solution2 = addDataOrganizationListener("<html>Replace Type Code<br />with State/Strategy</html>", window);
				buttons.add(solution2);
				break;
			case "s7" :
				image = pathPrefix + "smellvertex-7.png";
				title = "Simple Type Code";
				solution1 = addDataOrganizationListener("<html>Replace Type Code<br />with Class</html>", window);
				buttons.add(solution1);
				break;
			case "s8" :
				image = pathPrefix + "smellvertex-8.png";
				title = "Long Method";
				solution1 = addMethodCompListener("Extract Method", window);
				buttons.add(solution1);
				solution2 = addMethodCompListener("Substitute Algorithm", window);
				buttons.add(solution2);
				solution3 = addCondExpressionListener("Decompose Conditional", window);
				buttons.add(solution3);
				solution4 = addMethodCompListener("<html>Replace Temp<br />with Query</html>", window);
				buttons.add(solution4);
				break;
			case "s9" :
				image = pathPrefix + "smellvertex-9.png";
				title = "Large Class";
				solution1 = addFeatureMovementListener("Extract Class", window);
				buttons.add(solution1);
				solution2 = addGeneralizationImprovementListener("Extract Subclass", window);
				buttons.add(solution2);
				solution3 = addGeneralizationImprovementListener("Extract Interface", window);
				buttons.add(solution3);
				break;
			case "s10" :
				image = pathPrefix + "smellvertex-10.png";
				title = "Long Parameter List";
				solution1 = addMethodCallImprovementListener("<html>Replace Parameter<br />with Method</html>", window);
				buttons.add(solution1);
				solution2 = addMethodCallImprovementListener("Preserve Whole Object", window);
				buttons.add(solution2);
				solution3 = addMethodCallImprovementListener("<html>Introduce Parameter<br />Object</html>", window);
				buttons.add(solution3);
				break;
			default:
				image = "/images/nothing.jpg";
				title = "Should not be visible";
				break;
		}
		PopupWindow edgeWindow = new PopupWindow(smellDim, buttons, image, title);
		edgeWindow.setVisible(true);
		buttons.clear();
	}

	private JButton addMethodCompListener(String title, MainWindow window){
		JButton newButton = new JButton(title);
		newButton.addActionListener(e -> window.getMainRefactoringRegion().methodCompositionInit(window));
		return newButton;
	}
	
	private JButton addFeatureMovementListener(String title, MainWindow window){
		JButton newButton = new JButton(title);
		newButton.addActionListener(e -> window.getMainRefactoringRegion().featureMovementInit(window));
		return newButton;
	}
	
	private JButton addDataOrganizationListener(String title, MainWindow window){
		JButton newButton = new JButton(title);
		newButton.addActionListener(e -> window.getMainRefactoringRegion().dataOrganizationInit(window));
		return newButton;
	}
	
	private JButton addMethodCallImprovementListener(String title, MainWindow window){
		JButton newButton = new JButton(title);
		newButton.addActionListener(e -> window.getMainRefactoringRegion().methodCallImprovementInit(window));
		return newButton;
	}
	
	private JButton addCondExpressionListener(String title, MainWindow window){
		JButton newButton = new JButton(title);
		newButton.addActionListener(e -> window.getMainRefactoringRegion().condExpressionInit(window));
		return newButton;
	}
	
	private JButton addGeneralizationImprovementListener(String title, MainWindow window){
		JButton newButton = new JButton(title);
		newButton.addActionListener(e -> window.getMainRefactoringRegion().generalizationImprovementInit(window));
		return newButton;
	}
	
	public GraphProperties getRegionGraph(){
		return regionGraph;
	}
}
