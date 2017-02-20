package MapRegions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

import DataHandling.GraphProperties;
import UI.MainWindow;
import relationshipEdges.Relationship;

/*	This class is responsible for the creation of the main 6-node refactoring region graph.
 */

public class MainRefactoringRegion {
	
	private mxCell[] regions;
	private GraphProperties regionGraph;
	private ArrayList<Relationship> relationshipList;
	
	private MethodComposition mMethodComposition;
	private FeatureMovementBetweenObjects mFeatureMovement;
	private MethodCallImprovement mMethodCall;
	private ConditionalExpressionSimplification mCondExpression;
	private GeneralizationImprovement mGeneralImp;
	private DataOrganization mDataOrganization;
	
	
	public MainRefactoringRegion(MainWindow window){
		createMainRefactoringRegionGraph(window);
	}
	
	private void createMainRefactoringRegionGraph(MainWindow window)
	{
		regions = new mxCell[6];
		regionGraph = new GraphProperties("<html>" + "Left Click: Display Refactoring Succession Path" + "<br>" + "Right Click: Display Information about this Refactoring" + "</html>");
		relationshipList = new ArrayList<Relationship>();
		
		Object parent = regionGraph.getParent();
		mxGraph g = regionGraph.getMyGraph();
		
		g.getModel().beginUpdate();
		try	{
			addMainRefactoringRegionVertices(g, parent);
			addMainRefactoringRegionVerticesRelations(g, parent);
		} finally {
			g.getModel().endUpdate();
		}
		
		setGraphLayout(g, parent);
		regionGraph.getMyGraphComponent().getGraphControl().addMouseListener(new MouseAdapter()	{
			public void mouseReleased(MouseEvent e)	{
				mxCell cell = (mxCell) regionGraph.getMyGraphComponent().getCellAt(e.getX(), e.getY());
					
				if((cell != null)&&(cell.isVertex()))
					if(e.getButton() == MouseEvent.BUTTON3)
						switch(cell.getId()) {
							case "v0" :
								methodCompositionInit(window);
								break;
							case "v1" :
								featureMovementInit(window);
								break;
							case "v2" :
								methodCallImprovementInit(window);
								break;
							case "v3" :
								condExpressionInit(window);
								break;
							case "v4" :
								generalizationImprovementInit(window);
								break;
							case "v5" :
								dataOrganizationInit(window);
								break;
							default:
								break;
						}
			}
		});
	}
	
	private void addMainRefactoringRegionVertices(mxGraph g, Object parent){
		regions[0] = (mxCell) g.insertVertex(parent, "v0", "Method Composition", 420, 585, 150, 100, "circle;fillColor=#00FFFF;fontSize=12");
		regions[1] = (mxCell) g.insertVertex(parent, "v1", "Feature Movement\nBetween Objects", 670, 435, 150, 100, "circle;fillColor=#E2D2B0;fontSize=12");
		regions[2] = (mxCell) g.insertVertex(parent, "v2", "Method Call\nImprovement", 670, 165, 150, 100, "circle;fillColor=#93D1FF;fontSize=12");
		regions[3] = (mxCell) g.insertVertex(parent, "v3", "Conditional Expression\nSimplification", 420, 15, 150, 100, "circle;fillColor=#D1FA8A;fontSize=12");
		regions[4] = (mxCell) g.insertVertex(parent, "v4", "Generalization\nImprovement", 170, 165, 150, 100, "circle;fillColor=#DCD1EF;fontSize=12");
		regions[5] = (mxCell) g.insertVertex(parent, "v5", "Data Organization", 170, 435, 150, 100, "circle;fillColor=#FEB9DF;fontSize=12");
	}
	
	private void addMainRefactoringRegionVerticesRelations(mxGraph g, Object parent){
		//Method Composition
		relationshipList.add(new Relationship(regions[0], regions[1], "succession", "e1", false));
		relationshipList.add(new Relationship(regions[0], regions[2], "succession", "e2", false));
		relationshipList.add(new Relationship(regions[4], regions[0], "succession", "e3", false));
		relationshipList.add(new Relationship(regions[2], regions[0], "succession", "e4", false));
		relationshipList.add(new Relationship(regions[4], regions[0], "isPartOf", "e5", false));
		relationshipList.add(new Relationship(regions[5], regions[0], "isPartOf", "e6", false));
		
		//Feature Movement
		relationshipList.add(new Relationship(regions[5], regions[1], "succession", "e7", false));
		relationshipList.add(new Relationship(regions[2], regions[1], "succession", "e8", false));
		relationshipList.add(new Relationship(regions[5], regions[1], "isPartOf", "e9", false));
		relationshipList.add(new Relationship(regions[4], regions[1], "isPartOf", "e10", false));
		relationshipList.add(new Relationship(regions[3], regions[1], "isPartOf", "e11", false));
		relationshipList.add(new Relationship(regions[4], regions[1], "insteadOf", "e12", false));
		relationshipList.add(new Relationship(regions[2], regions[1], "insteadOf", "e13", false));
		
		//Method Call Improvement
		relationshipList.add(new Relationship(regions[5], regions[2], "isPartOf", "e14", false));
		relationshipList.add(new Relationship(regions[4], regions[2], "insteadOf", "e15", false));
		relationshipList.add(new Relationship(regions[3], regions[2], "insteadOf", "e16", false));
		
		//Conditional Expression Simplification
		relationshipList.add(new Relationship(regions[5], regions[3], "succession", "e17", false));
		relationshipList.add(new Relationship(regions[4], regions[3], "isPartOf", "e18", false));
		
		//Generalization Improvement
		relationshipList.add(new Relationship(regions[5], regions[4], "isPartOf", "e19", false));
		
		for(Relationship r  : relationshipList)
			g.insertEdge(parent, r.getID(), null, r.getSource(), r.getDestination(), r.getType());
	}
	
	private void setGraphLayout(mxGraph g, Object parent){
		mxParallelEdgeLayout layout = new mxParallelEdgeLayout(g);
		layout.execute(parent);
	}
	
	public void methodCompositionInit(MainWindow window){
		mMethodComposition = new MethodComposition(window);
		window.getTripAdvisorFrame().setContentPane(mMethodComposition.getGraphPanel());
		window.getTripAdvisorFrame().revalidate();
	}
	public void featureMovementInit(MainWindow window){
		mFeatureMovement = new FeatureMovementBetweenObjects(window);
		window.getTripAdvisorFrame().setContentPane(mFeatureMovement.getGraphPanel());
		window.getTripAdvisorFrame().revalidate();
	}
	
	public void methodCallImprovementInit(MainWindow window){
		mMethodCall = new MethodCallImprovement(window);
		window.getTripAdvisorFrame().setContentPane(mMethodCall.getGraphPanel());
		window.getTripAdvisorFrame().revalidate();
	}
	
	public void condExpressionInit(MainWindow window){
		mCondExpression = new ConditionalExpressionSimplification(window);
		window.getTripAdvisorFrame().setContentPane(mCondExpression.getGraphPanel());
		window.getTripAdvisorFrame().revalidate();
	}
	
	public void generalizationImprovementInit(MainWindow window){
		mGeneralImp = new GeneralizationImprovement(window);
		window.getTripAdvisorFrame().setContentPane(mGeneralImp.getGraphPanel());
		window.getTripAdvisorFrame().revalidate();
	}
	
	public void dataOrganizationInit(MainWindow window){
		mDataOrganization = new DataOrganization(window);
		window.getTripAdvisorFrame().setContentPane(mDataOrganization.getGraphPanel());
		window.getTripAdvisorFrame().revalidate();
	}
	
	public GraphProperties getRegionGraph(){
		return regionGraph;
	}
}
