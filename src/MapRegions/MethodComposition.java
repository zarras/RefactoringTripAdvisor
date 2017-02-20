package MapRegions;

import RefactoringDetectors.ExtractMethodIdentification;
import RefactoringDetectors.InlineMethodIdentification;
import RefactoringDetectors.InlineTempIdentification;
import RefactoringDetectors.IntroduceExplainingVariableIdentification;
import RefactoringDetectors.MoveMethodIdentification;
import RefactoringDetectors.RemoveAssignmentsToParametersIndentification;
import RefactoringDetectors.ReplaceMethodWithMethodObjectIdentification;
import RefactoringDetectors.ReplaceTempWithQueryIdentification;
import RefactoringDetectors.SplitTemporaryVariableIdentification;
import UI.PopupWindow;
import UI.MainWindow;
import UI.RefactoringInfoWindow;
import relationshipEdges.Relationship;

import java.awt.Dimension;

import com.mxgraph.model.*;
import com.mxgraph.view.*;

/*	This class handles the creation of the Method Composition map.
 * 	It's responsible for the graph creation as well as the 3-slide information window
 * 	for each of its refactorings.
 */
public class MethodComposition extends RefactoringRegion {
	
	public MethodComposition(MainWindow p) {
		super(p,11,2);	
		refactoringLabel.setText("Method Composition");
	}

	public void graphNodePopUp(mxCell cell)
	{
		String pathPrefix = "/MethodCompositionView/images/";
		switch(cell.getId())
		{
			case "v1" :
				setPopUpWindowData("Separate Query from Modifier", "/MethodCallImprovementView/images/SeparateQueryMotivation.png",
						"/MethodCallImprovementView/images/SeparateQueryExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v2" :
				dataForIdentification = new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Inline Temp", pathPrefix + "InlineTempMotivation.png",
						pathPrefix + "InlineTempExample.png", "/images/CustomIdentificationPlusTool.png", 
						new InlineTempIdentification(), dataForIdentification);
				break;
			case "v3" :
				dataForIdentification = new Object[3];
				dataForIdentification[0] = page;
				dataForIdentification[1] = display;
				dataForIdentification[2] = mainWindow;
				setPopUpWindowData("Introduce Explaining Variable", pathPrefix + "IntroduceExplainingMotivation.png",
						pathPrefix + "IntroduceExplainingExample.png", "/images/CustomIdentificationPlusTool.png", 
						new IntroduceExplainingVariableIdentification(), dataForIdentification);
				break;
			case "v4" :
				dataForIdentification = new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Split Temporary Variable", pathPrefix + "SplitVariableMotivation.png",
						pathPrefix + "SplitVariableExample.png", "/images/CustomIdentificationPlusTool.png", 
						new SplitTemporaryVariableIdentification(), dataForIdentification);
				break;
			case "v5" :
				dataForIdentification= new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Inline Method", pathPrefix + "InlineMethodMotivation.png",
						pathPrefix + "InlineMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new InlineMethodIdentification(), dataForIdentification);
				break;
			case "v6" :
				dataForIdentification = new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Replace Temp with Query", pathPrefix + "ReplaceTempMotivation.png",
						pathPrefix + "ReplaceTempExample.png", "/images/CustomIdentificationPlusTool.png", 
						new ReplaceTempWithQueryIdentification(), dataForIdentification);
				break;
			case "v7" :
				dataForIdentification = new Object[3];
				dataForIdentification[0] = page;
				dataForIdentification[1] = display;
				dataForIdentification[2] = mainWindow;
				setPopUpWindowData("Replace Method with Method Object", pathPrefix + "ReplaceMethodMotivation.png",
						pathPrefix + "ReplaceMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new ReplaceMethodWithMethodObjectIdentification(), dataForIdentification);
				break;
			case "v8" :
				dataForIdentification = new Object[3];
				dataForIdentification[0] = page;
				dataForIdentification[1] = display;
				dataForIdentification[2] = mainWindow;
				setPopUpWindowData("Extract Method", pathPrefix + "ExtractMethodMotivation.png",
						pathPrefix + "ExtractMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new ExtractMethodIdentification(), dataForIdentification);
				break;
			case "v9" :
				dataForIdentification= new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Remove Assignments to Parameters", pathPrefix + "RemoveAssignmentsMotivation.png",
						pathPrefix + "RemoveAssignmentsExample.png", "/images/CustomIdentificationPlusTool.png", 
						new RemoveAssignmentsToParametersIndentification(), dataForIdentification);
				break;
			case "v10" :
				setPopUpWindowData("Substitute Algorithm", pathPrefix + "SubstituteAlgorithmMotivation.png",
						pathPrefix + "SubstituteAlgorithmExample.png", "", 
						null, null);
				break;
			case "v11" :
				dataForIdentification = new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Move Method", "/FeatureMovementBetweenObjectsView/images/MoveMethodMotivation.png",
						"/FeatureMovementBetweenObjectsView/images/MoveMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new MoveMethodIdentification(), dataForIdentification);
				break;
			default:
				break;
		}
		RefactoringInfoWindow popUpWindow = new RefactoringInfoWindow(refactoringOptions);
		popUpWindow.setVisible(true);
	}

	public void edgePopUp(mxCell cell){
		
		String pathPrefix = "/MethodCompositionView/images/";
		String imageFile = "/images/nothing.png";
		Dimension edgeDim = new Dimension(615, 465);
		switch(cell.getId())
		{
			case "e1" :
				imageFile = pathPrefix + "methodcomp-e1.png";
				break;
			case "e2" :
				imageFile = pathPrefix + "methodcomp-e2.png";
				break;	
			case "e3" :
				imageFile = pathPrefix + "methodcomp-e3.png";
				break;
			case "e4" :
				imageFile = pathPrefix + "methodcomp-e4.png";
				break;
			case "e5" :
				imageFile = pathPrefix + "methodcomp-e5.png";
				break;
			case "e6" :
				imageFile = pathPrefix + "methodcomp-e6.png";
				break;
			case "e7" :
				imageFile = pathPrefix + "methodcomp-e7.png";
				break;
			case "e8" :
				imageFile = pathPrefix + "methodcomp-e8.png";
				break;
			case "e9" :
				imageFile = pathPrefix + "methodcomp-e9.png";
				break;
			case "e10" :
				imageFile = pathPrefix + "methodcomp-e10.png";
				break;
			case "e11" :
				imageFile = pathPrefix + "methodcomp-e11.png";
				break;
			case "e12" :
				imageFile = pathPrefix + "methodcomp-e12.png";
				break;
			case "e13" :
				imageFile = pathPrefix + "methodcomp-e13.png";
				break;
			default:
				break;
		}
		PopupWindow edgeWindow = new PopupWindow(edgeDim, imageFile, "Edge description window");
		edgeWindow.setVisible(true);	
	}

	public void addMainVertices(mxGraph g, Object p) {
		/* This function generates the main vertices of the "Method Composition" subgraph */
		vertices[1] = (mxCell) g.insertVertex(p, "v2", "Inline Temp", 75, 612-50, 100, 50, "circle;fillColor=#00FFFF");
		vertices[2] = (mxCell) g.insertVertex(p, "v3", "Introduce\nExplaining Variable", 75, 437-50, 100, 50, "circle;fillColor=#00FFFF");
		vertices[3] = (mxCell) g.insertVertex(p, "v4", "Split\nTemporary Variable", 75, 262-50, 100, 50, "circle;fillColor=#00FFFF");
		vertices[4] = (mxCell) g.insertVertex(p, "v5", "Inline Method", 325, 87-50, 100, 50, "circle;fillColor=#00FFFF");
		vertices[5] = (mxCell) g.insertVertex(p, "v6", "Replace Temp\nwith Query", 325, 350-50, 100, 50, "circle;fillColor=#00FFFF");
		vertices[6] = (mxCell) g.insertVertex(p, "v7", "Replace Method\nwith Method Object", 575, 87-50, 100, 50, "circle;fillColor=#00FFFF");
		vertices[7] = (mxCell) g.insertVertex(p, "v8", "Extract Method", 575, 437-50, 100, 50, "circle;fillColor=#00FFFF");
		vertices[8] = (mxCell) g.insertVertex(p, "v9", "Remove\nAssignments to\nParameters", 825, 262-50, 100, 50, "circle;fillColor=#00FFFF");
		vertices[9] = (mxCell) g.insertVertex(p, "v10", "Substitute Algorithm", 825, 612-50, 100, 50, "circle;fillColor=#00FFFF");
	}
	
	public void addMainVerticesRelations() {
		/* This function generates the relations of the main vertices of the "Method Composition" subgraph */
		relationshipList.add(new Relationship(vertices[1], vertices[7], "succession", "e1", false));
		relationshipList.add(new Relationship(vertices[2], vertices[5], "succession", "e2", false));
		relationshipList.add(new Relationship(vertices[3], vertices[5], "succession", "e3", false));
		relationshipList.add(new Relationship(vertices[4], vertices[6], "succession", "e4", false));
		relationshipList.add(new Relationship(vertices[4], vertices[7], "succession", "e5", false));
		relationshipList.add(new Relationship(vertices[5], vertices[7], "succession", "e6", false));
		relationshipList.add(new Relationship(vertices[7], vertices[8], "succession", "e7", false));
		relationshipList.add(new Relationship(vertices[7], vertices[9], "succession", "e8", false));
		relationshipList.add(new Relationship(vertices[1], vertices[5], "isPartOf", "e9", false));
		relationshipList.add(new Relationship(vertices[2], vertices[7], "insteadOf", "e10", false));
		relationshipList.add(new Relationship(vertices[6], vertices[7], "insteadOf", "e11", false));
	}
	
	public void addExternalVertices(mxGraph g, Object p) {
		/* This function generates the external vertices of the "Method Composition" subgraph */
		externalVertices[0] = vertices[0] = (mxCell) g.insertVertex(p, "v1", "Separate Query\nfrom Modifier", 75, 87-50, 100, 50, "fillColor=#93D1FF;fontColor=black");
		externalVertices[1] = vertices[10] = (mxCell) g.insertVertex(p, "v11", "Move Method", 825, 437-50, 100, 50, "fillColor=#E2D2B0;fontColor=black");
	}
	
	public void addExternalVerticesRelations() {
		/* This function generates the relations of the external vertices of the "Method Composition" subgraph */
		relationshipList.add(new Relationship(vertices[0], vertices[5], "succession", "e12", true));
		relationshipList.add(new Relationship(vertices[7], vertices[10], "succession", "e13", true));
	}
}
