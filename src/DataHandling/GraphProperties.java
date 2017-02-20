package DataHandling;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import relationshipEdges.Relationship;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

/*	This class handles all the graph properties (node,edge styles)  
 * 	for the refactoring maps of Refactoring Trip Advisor.
 * 	It also handles the path coloring option Refactoring Trip Advisor provides.
 */

public class GraphProperties {

	private mxGraph graph;
	private final mxGraphComponent graphComponent;
	
	public GraphProperties(String tooltip) {
		graph = new mxGraph() {
			public String getToolTipForCell(Object cell) {
				return tooltip;
			}
		};
		initGraph();
		
		graphComponent = new mxGraphComponent(graph);
		graphComponent.setToolTips(true);
		graphComponent.getViewport().setBackground(Color.WHITE);
	}
	
	private void initGraph() {
		
		graph.getModel().beginUpdate();
		try	{	
			mxStylesheet stylesheet = graph.getStylesheet();
			
			Hashtable<String, Object> style = new Hashtable<String, Object>();
			style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
			style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
			stylesheet.putCellStyle("circle", style);
			
			style = new Hashtable<String, Object>();
			style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
			style.put(mxConstants.STYLE_FONTCOLOR, "#000000");
			stylesheet.putCellStyle("rect", style);
			
			graph.setCellsBendable(false);
			graph.setCellsDeletable(true);
			graph.setCellsEditable(false);
			graph.setCellsMovable(true);
			graph.setCellsResizable(false);
			graph.setCellsSelectable(true);
			graph.setVertexLabelsMovable(false);
			graph.setEdgeLabelsMovable(false);
						
			
			Map<String, Object> edges = new Hashtable<String, Object>();			
			edges.put(mxConstants.STYLE_STROKECOLOR, "black");
			edges.put(mxConstants.STYLE_STROKEWIDTH, 2);
			stylesheet.putCellStyle("succession", edges);
			
			edges = new Hashtable<String, Object>();
			edges.put(mxConstants.STYLE_DASHED, true);
			edges.put(mxConstants.STYLE_STROKECOLOR, "black");
			edges.put(mxConstants.STYLE_STROKEWIDTH, 2);
			stylesheet.putCellStyle("isPartOf", edges);	
			
			edges = new Hashtable<String, Object>();
			edges.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CURVE);
			edges.put(mxConstants.STYLE_STROKECOLOR, "black");
			edges.put(mxConstants.STYLE_STROKEWIDTH, 2);
			stylesheet.putCellStyle("curved", edges);	
			
			edges = new Hashtable<String, Object>();
			edges.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_CLASSIC);
			edges.put(mxConstants.STYLE_DASHED, true);
			edges.put(mxConstants.STYLE_STROKECOLOR, "black");
			edges.put(mxConstants.STYLE_STROKEWIDTH, 2);
			stylesheet.putCellStyle("bi", edges);
			
			edges = new Hashtable<String, Object>();
			edges.put(mxConstants.STYLE_STROKECOLOR, "white");
			stylesheet.putCellStyle("biInv", edges);
			
			edges = new Hashtable<String, Object>();
			edges.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_CLASSIC);
			edges.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
			edges.put(mxConstants.STYLE_DASHED, true);
			edges.put(mxConstants.STYLE_STROKECOLOR, "black");
			edges.put(mxConstants.STYLE_STROKEWIDTH, 2);
			stylesheet.putCellStyle("insteadOf", edges);
			
		} finally {
			graph.getModel().endUpdate();
		}
	}
	
	public void initColors()
	{
		Object[] nodes = graph.getChildVertices(graph.getDefaultParent());
		mxCell cell;
		
		for (int i = 0; i < nodes.length; i++) {
			cell = (mxCell)nodes[i];
			Map<String, Object> styleMap = graph.getCellStyle(cell);
			String fillColor = (String) styleMap.get("fillColor");
			graph.getView().getState(cell).getStyle().put(mxConstants.STYLE_FILLCOLOR, fillColor);
		}
		graph.repaint();
	}
	
	public void colorizeNeighborhood(mxCell cell, ArrayList<Relationship> relationshipList)	{
		
		initColors();
		graph.getView().getState(cell).getStyle().put(mxConstants.STYLE_FILLCOLOR, "yellow");
		
		for(Relationship r : relationshipList)
			if(r.getSource().equals(cell)) {
				switch(r.getType()) {
					case "isPartOf":
						graph.getView().getState(r.getDestination()).getStyle().put(mxConstants.STYLE_FILLCOLOR, "ffcc80");//orange
						break;
					case "insteadOf":
						graph.getView().getState(r.getDestination()).getStyle().put(mxConstants.STYLE_FILLCOLOR, "#0099FF");//blue
						break;
					case "succession":
						graph.getView().getState(r.getDestination()).getStyle().put(mxConstants.STYLE_FILLCOLOR, "#00FF00");//green
						break;
					default:
						break;
				}
			}

		for(Relationship r : relationshipList)
			if(r.getDestination().equals(cell)) 
				switch(r.getType()) {
					case "isPartOf":
						graph.getView().getState(r.getSource()).getStyle().put(mxConstants.STYLE_FILLCOLOR, "ffcc80");//orange
						break;
					case "insteadOf":
						graph.getView().getState(r.getSource()).getStyle().put(mxConstants.STYLE_FILLCOLOR, "#0099FF");//blue
						break;
					case "succession":
						graph.getView().getState(r.getSource()).getStyle().put(mxConstants.STYLE_FILLCOLOR, "#FF0000");//red
						break;
					default:
						break;
			}	
		graph.repaint();
	}
		
	public Object getParent() {
		return graph.getDefaultParent();
	}
	
	public mxGraphComponent getMyGraphComponent() {
		return graphComponent;
	}
	
	public mxGraph getMyGraph() {
		return graph;
	}
}
