package MapRegions;

import RefactoringDetectors.ExtractClassIdentification;
import RefactoringDetectors.ExtractMethodIdentification;
import RefactoringDetectors.MoveMethodIdentification;
import UI.PopupWindow;
import UI.MainWindow;
import UI.RefactoringInfoWindow;
import relationshipEdges.Relationship;

import java.awt.Dimension;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

/*	This class handles the creation of the Feature Movement Between Objects map.
 * 	It's responsible for the graph creation as well as the 3-slide information window
 * 	for each of its refactorings.
 */
public class FeatureMovementBetweenObjects extends RefactoringRegion {
		
	public FeatureMovementBetweenObjects(MainWindow p) {
		super(p,12,4);
		refactoringLabel.setText("Feature Movement Between Objects");
	}

	public void graphNodePopUp(mxCell cell)
	{
		String pathPrefix = "/FeatureMovementBetweenObjectsView/images/";
		switch(cell.getId())
		{
			case "v1" :
				setPopUpWindowData("Encapsulate Field", "/DataOrganizationView/images/EncapsulateFieldMotivation.png",
						"/DataOrganizationView/images/EncapsulateFieldExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v2" :
				setPopUpWindowData("Self Encapsulate Field", "/DataOrganizationView/images/SelfEncapsulateFieldMotivation.png",
						"/DataOrganizationView/images/SelfEncapsulateFieldExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v3" :
				setPopUpWindowData("Extract Interface", "/GeneralizationImprovementView/images/ExtractInterfaceMotivation.png",
						"/GeneralizationImprovementView/images/ExtractInterfaceExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v4" :
				setPopUpWindowData("Move Field", pathPrefix + "MoveFieldMotivation.png",
						pathPrefix + "MoveFieldExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v5" :
				dataForIdentification = new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Extract Class", pathPrefix + "ExtractClassMotivation.png",
						pathPrefix + "ExtractClassExample.png", "/images/CustomIdentificationPlusTool.png", 
						new ExtractClassIdentification(), dataForIdentification);
				break;
			case "v6" :
				setPopUpWindowData("Inline Class", pathPrefix + "InlineClassMotivation.png",
						pathPrefix + "InlineClassExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v7" :
				dataForIdentification = new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Move Method", pathPrefix + "MoveMethodMotivation.png",
						pathPrefix + "MoveMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new MoveMethodIdentification(), dataForIdentification);
				break;
			case "v8" :
				setPopUpWindowData("Hide Delegate", pathPrefix + "HideDelegateMotivation.png",
						pathPrefix + "HideDelegateExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v9" :
				setPopUpWindowData("Remove Middle Man", pathPrefix + "RemoveMiddleManMotivation.png",
						pathPrefix + "RemoveMiddleManExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v10" :
				setPopUpWindowData("Introduce Foreign Method", pathPrefix + "IntroduceForeignMethodMotivation.png",
						pathPrefix + "IntroduceForeignMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v11" :
				setPopUpWindowData("Introduce Local Extension", pathPrefix + "IntroduceLocalExtensionMotivation.png",
						pathPrefix + "IntroduceLocalExtensionExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v12" :
				dataForIdentification= new Object[3];
				dataForIdentification[0] = page;
				dataForIdentification[1] = display;
				dataForIdentification[2] = mainWindow;
				setPopUpWindowData("Extract Method", "/MethodCompositionView/images/ExtractMethodMotivation.png",
						"/MethodCompositionView/images/ExtractMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new ExtractMethodIdentification(), dataForIdentification);
				break;
			default:
				break;
		}
		RefactoringInfoWindow popUpWindow = new RefactoringInfoWindow(refactoringOptions);
		popUpWindow.setVisible(true);
	}
	
public void edgePopUp(mxCell cell){
		
		String pathPrefix = "/FeatureMovementBetweenObjectsView/images/";
		String imageFile = "/images/nothing.png";
		Dimension edgeDim = new Dimension(615, 465);
		switch(cell.getId())
		{
			case "e1" :
				imageFile = pathPrefix + "featuremovement-e1.png";
				break;
			case "e2" :
				imageFile = pathPrefix + "featuremovement-e2.png";
				break;	
			case "e3" :
			case "e4" :
				imageFile = pathPrefix + "featuremovement-e3e4.png";
				break;
			case "e5" :
			case "e6" :
				imageFile = pathPrefix + "featuremovement-e5e6.png";
				break;
			case "e7" :
			case "e9" :
				imageFile = pathPrefix + "featuremovement-e7e9.png";
				break;
			case "e8" :
			case "e10" :
				imageFile = pathPrefix + "featuremovement-e8e10.png";
				break;
			case "e11" :
			case "e12" :
				imageFile = pathPrefix + "featuremovement-e11e12.png";
				break;
			case "e13" :
				imageFile = pathPrefix + "featuremovement-e13.png";
				break;
			case "e14" :
				imageFile = "/MethodCompositionView/images/methodcomp-e13.png";
				break;
		}
		PopupWindow edgeWindow = new PopupWindow(edgeDim, imageFile, "Edge description window");
		edgeWindow.setVisible(true);
}

	
	public void addMainVertices(mxGraph g, Object p) {
		/* This function generates the main vertices of the "Feature Movement Between Objects" subgraph */
		vertices[3] = (mxCell) g.insertVertex(p, "v4", "Move Field", 250, 350-50, 100, 50, "circle;fillColor=#E2D2B0");
		vertices[4] = (mxCell) g.insertVertex(p, "v5", "Extract Class", 375, 87-50, 100, 50, "circle;fillColor=#E2D2B0");
		vertices[5] = (mxCell) g.insertVertex(p, "v6", "Inline Class", 375, 612-50, 100, 50, "circle;fillColor=#E2D2B0");
		vertices[6] = (mxCell) g.insertVertex(p, "v7", "Move Method", 500, 350-50, 100, 50, "circle;fillColor=#E2D2B0");
		vertices[7] = (mxCell) g.insertVertex(p, "v8", "Hide Delegate", 625, 262-50, 100, 50, "circle;fillColor=#E2D2B0");
		vertices[8] = (mxCell) g.insertVertex(p, "v9", "Remove Middle Man", 875, 262-50, 100, 50, "circle;fillColor=#E2D2B0");
		vertices[9] = (mxCell) g.insertVertex(p, "v10", "Introduce Foreign\nMethod", 625, 525-50, 100, 50, "circle;fillColor=#E2D2B0");
		vertices[10] = (mxCell) g.insertVertex(p, "v11", "Introduce Local\nExtension", 875, 525-50, 100, 50, "circle;fillColor=#E2D2B0");
	}
	
	public void addMainVerticesRelations() {
		/* This function generates the relations of the main vertices of the "Feature Movement Between Objects" subgraph */
		relationshipList.add(new Relationship(vertices[3], vertices[6], "succession", "e1", false));
		relationshipList.add(new Relationship(vertices[7], vertices[4], "succession", "e2", false));
		relationshipList.add(new Relationship(vertices[7], vertices[8], "succession", "e3", false));
		relationshipList.add(new Relationship(vertices[8], vertices[7], "succession", "e4", false));
		relationshipList.add(new Relationship(vertices[9], vertices[10], "succession", "e5", false));
		relationshipList.add(new Relationship(vertices[10], vertices[9], "succession", "e6", false));
		relationshipList.add(new Relationship(vertices[3], vertices[4], "isPartOf", "e7", false));
		relationshipList.add(new Relationship(vertices[3], vertices[5], "isPartOf", "e8", false));
		relationshipList.add(new Relationship(vertices[6], vertices[4], "isPartOf", "e9", false));
		relationshipList.add(new Relationship(vertices[6], vertices[5], "isPartOf", "e10", false));
	}
	
	public void addExternalVertices(mxGraph g, Object p) {
		/* This function generates the external vertices of the "Feature Movement Between Objects" subgraph */
		externalVertices[0] = vertices[0] = (mxCell) g.insertVertex(p, "v1", "Encapsulate Field", 35, 262-50, 100, 50, "fillColor=#FEB9DF;fontColor=black");	
		externalVertices[1] = vertices[1] = (mxCell) g.insertVertex(p, "v2", "Self Encapsulate\nField", 35, 437-50, 100, 50, "fillColor=#FEB9DF;fontColor=black");
		externalVertices[2] = vertices[2] = (mxCell) g.insertVertex(p, "v3", "Extract Interface", 35, 612-50, 100, 50, "fillColor=#DCD1EF;fontColor=black");
		externalVertices[3] = vertices[11] = (mxCell) g.insertVertex(p, "v12", "Extract Method", 750, 394-50, 100, 50, "fillColor=#00FFFF;fontColor=black");
	}
	
	public void addExternalVerticesRelations() {
		/* This function generates the relations of the external vertices of the "Feature Movement Between Objects" subgraph */
		relationshipList.add(new Relationship(vertices[0], vertices[3], "succession", "e11", true));
		relationshipList.add(new Relationship(vertices[1], vertices[3], "succession", "e12", true));
		relationshipList.add(new Relationship(vertices[2], vertices[5], "succession", "e13", true));
		relationshipList.add(new Relationship(vertices[11], vertices[6], "succession", "e14", true));
	}
}