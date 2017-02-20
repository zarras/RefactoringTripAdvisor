package MapRegions;

import RefactoringDetectors.ExtractMethodIdentification;
import RefactoringDetectors.MoveMethodIdentification;
import UI.PopupWindow;
import UI.MainWindow;
import UI.RefactoringInfoWindow;
import relationshipEdges.Relationship;

import java.awt.Dimension;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

/*	This class handles the creation of the Conditional Expression Simplification map.
 * 	It's responsible for the graph creation as well as the 3-slide information window
 * 	for each of its refactorings.
 */
public class ConditionalExpressionSimplification extends RefactoringRegion {
	
	public ConditionalExpressionSimplification(MainWindow p)	{
		super(p, 12, 4);
		refactoringLabel.setText("Conditional Expression Simplification");
	}
	
	public void graphNodePopUp(mxCell cell)
	{
		String pathPrefix = "/ConditionalExpressionSimplificationView/images/";
		switch(cell.getId())
		{
			case "v1" :
				setPopUpWindowData("Consolidate Duplicate Conditional Fragments", pathPrefix + "ConsolidateDuplicateFragmentsMotivation.png",
						pathPrefix + "ConsolidateDuplicateFragmentsExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);				
				break;
			case "v2" :
				setPopUpWindowData("Replace Nested Conditional with Guard Clauses", pathPrefix + "ReplaceNestedConditionalMotivation.png",
						pathPrefix + "ReplaceNestedConditionalExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;	
			case "v3" :
				setPopUpWindowData("Consolidate Conditional Expression", pathPrefix + "ConsolidateConditionalExpressionMotivation.png",
						pathPrefix + "ConsolidateConditionalExpressionExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v4" :
				setPopUpWindowData("Introduce Assertion", pathPrefix + "IntroduceAssertionMotivation.png",
						pathPrefix + "IntroduceAssertionExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v5" :
				setPopUpWindowData("Decompose Conditional", pathPrefix + "DecomposeConditionalMotivation.png",
						pathPrefix + "DecomposeConditionalExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v6" :
				dataForIdentification= new Object[3];
				dataForIdentification[0] = page;
				dataForIdentification[1] = display;
				dataForIdentification[2] = mainWindow;
				setPopUpWindowData("Extract Method", "/MethodCompositionView/images/ExtractMethodMotivation.png",
						"/MethodCompositionView/images/ExtractMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new ExtractMethodIdentification(), dataForIdentification);
				break;
			case "v7" :
				dataForIdentification = new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Move Method", "/FeatureMovementBetweenObjectsView/images/MoveMethodMotivation.png",
						"/FeatureMovementBetweenObjectsView/images/MoveMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new MoveMethodIdentification(), dataForIdentification);
				break;
			case "v8" :
				setPopUpWindowData("Replace Conditional with Polymorphism", pathPrefix + "ReplaceConditionalWithPolymorphismMotivation.png",
						pathPrefix + "ReplaceConditionalWithPolymorphismExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v9" :
				setPopUpWindowData("Replace Type Code with Subclasses", "/DataOrganizationView/images/ReplaceTypeCodeWithSubclassesMotivation.png",
						"/DataOrganizationView/images/ReplaceTypeCodeWithSubclassesExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v10" :
				setPopUpWindowData("Remove Control Flag", pathPrefix + "RemoveControlFlagMotivation.png",
						pathPrefix + "RemoveControlFlagExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v11" :
				setPopUpWindowData("Introduce Null Object", pathPrefix + "IntroduceNullObjectMotivation.png",
						pathPrefix + "IntroduceNullObjectExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v12" :
				setPopUpWindowData("Replace Type Code with State/Strategy", "/DataOrganizationView/images/ReplaceTypeCodeWithStateMotivation.png",
						"/DataOrganizationView/images/ReplaceTypeCodeWithStateExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			default:
				break;
		}
		RefactoringInfoWindow popUpWindow = new RefactoringInfoWindow(refactoringOptions);
		popUpWindow.setVisible(true);
	}
	
	public void edgePopUp(mxCell cell){
		
		String pathPrefix = "/ConditionalExpressionSimplificationView/images/";
		String imageFile = "/images/nothing.png";
		Dimension edgeDim = new Dimension(615, 465);
		switch(cell.getId())
		{
			case "e1" :
				imageFile = pathPrefix + "conditionalexpr-e1.png";
				break;
			case "e2" :
				imageFile = pathPrefix + "conditionalexpr-e2.png";
				break;	
			case "e3" :
				imageFile = pathPrefix + "conditionalexpr-e3.png";
				break;
			case "e4" :
				imageFile = pathPrefix + "conditionalexpr-e4.png";
				break;
			case "e5" :
				imageFile = pathPrefix + "conditionalexpr-e5.png";
				break;
			case "e6" :
			case "e7" :
				imageFile = "/DataOrganizationView/images/dataorganization-e12e13.png";
				break;
			case "e8" :
				imageFile = pathPrefix + "conditionalexpr-e8.png";
				break;
			case "e9" :
				imageFile = pathPrefix + "conditionalexpr-e9.png";
				break;
			case "e10" :
				imageFile = pathPrefix + "conditionalexpr-e10.png";
				break;
			default:
				break;
		}
		PopupWindow edgeWindow = new PopupWindow(edgeDim, imageFile, "Edge description window");
		edgeWindow.setVisible(true);	
	}
	
	public void addMainVertices(mxGraph g, Object p) {
		/* This function generates the main vertices of the "Conditional Expression Simplification" subgraph */
		vertices[0] = (mxCell) g.insertVertex(p, "v1", "Consolidate Duplicate\nConditional Fragments", 25, 42, 110, 55, "circle;fillColor=#D1FA8A");			
		vertices[1] = (mxCell) g.insertVertex(p, "v2", "Replace Nested\nConditional With\nGuard Clauses", 25, 217, 108, 58, "circle;fillColor=#D1FA8A");
		vertices[2] = (mxCell) g.insertVertex(p, "v3", "Consolidate\nConditional Expression", 25, 392, 108, 58, "circle;fillColor=#D1FA8A");
		vertices[3] = (mxCell) g.insertVertex(p, "v4", "Introduce Assertion", 25, 567, 100, 50, "circle;fillColor=#D1FA8A");
		vertices[4] = (mxCell) g.insertVertex(p, "v5", "Decompose\nConditional", 250, 217, 100, 50, "circle;fillColor=#D1FA8A");		
		vertices[7] = (mxCell) g.insertVertex(p, "v8", "Replace Conditional\nWith Polymorphism", 500, 392, 110, 55, "circle;fillColor=#D1FA8A");
		vertices[9] = (mxCell) g.insertVertex(p, "v10", "Remove\nControl Flag", 875, 42, 100, 50, "circle;fillColor=#D1FA8A");
		vertices[10] = (mxCell) g.insertVertex(p, "v11", "Introduce Null Object", 875, 392, 100, 50, "circle;fillColor=#D1FA8A");
	}
	
	public void addMainVerticesRelations() {
		/* This function generates the relations of the main vertices of the "Conditional Expression Simplification" subgraph */
		relationshipList.add(new Relationship(vertices[0], vertices[4], "succession", "e1", false));
		relationshipList.add(new Relationship(vertices[1], vertices[4], "succession", "e2", false));
		relationshipList.add(new Relationship(vertices[2], vertices[4], "succession", "e3", false));
	}
	
	public void addExternalVertices(mxGraph g, Object p) {
		/* This function generates the external vertices of the "Conditional Expression Simplification" subgraph */
		externalVertices[0] = vertices[5] = (mxCell) g.insertVertex(p, "v6", "Extract Method", 250, 392, 100, 50, "fillColor=#00FFFF;fontColor=black");
		externalVertices[1] = vertices[6] = (mxCell) g.insertVertex(p, "v7", "Move Method", 505, 42, 100, 50, "fillColor=#E2D2B0;fontColor=black");
		externalVertices[2] = vertices[8] = (mxCell) g.insertVertex(p, "v9", "Replace Typecode\nwith Subclasses", 505, 567, 100, 50, "fillColor=#FEB9DF;fontColor=black");
		externalVertices[3] = vertices[11] = (mxCell) g.insertVertex(p, "v12", "Replace Typecode\nwith\nState/Strategy", 875, 567, 100, 50, "fillColor=#FEB9DF;fontColor=black");
	}
	
	public void addExternalVerticesRelations() {
		/* This function generates the relations of the external vertices of the "Conditional Expression Simplification" subgraph */
		relationshipList.add(new Relationship(vertices[2], vertices[5], "succession", "e4", true));
		relationshipList.add(new Relationship(vertices[3], vertices[5], "succession", "e5", true));
		relationshipList.add(new Relationship(vertices[8], vertices[7], "succession", "e6", true));
		relationshipList.add(new Relationship(vertices[11], vertices[7], "succession", "e7", true));
		relationshipList.add(new Relationship(vertices[5], vertices[4], "isPartOf", "e8", true));
		relationshipList.add(new Relationship(vertices[5], vertices[7], "isPartOf", "e9", true));
		relationshipList.add(new Relationship(vertices[6], vertices[7], "isPartOf", "e10", true));
	}
}
