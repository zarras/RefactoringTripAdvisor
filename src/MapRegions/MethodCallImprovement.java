package MapRegions;

import RefactoringDetectors.ExtractMethodIdentification;
import RefactoringDetectors.IntroduceParameterObjectIdentification;
import RefactoringDetectors.MoveMethodIdentification;
import UI.PopupWindow;
import UI.MainWindow;
import UI.RefactoringInfoWindow;
import relationshipEdges.Relationship;

import java.awt.Dimension;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

/*	This class handles the creation of the Method Call Improvement map.
 * 	It's responsible for the graph creation as well as the 3-slide information window
 * 	for each of its refactorings.
 */
public class MethodCallImprovement extends RefactoringRegion {
		
	public MethodCallImprovement(MainWindow p)
	{
		super(p,19,4);	
		refactoringLabel.setText("Method Call Improvement");
	}
	
	public void graphNodePopUp(mxCell cell)
	{
		String pathPrefix = "/MethodCallImprovementView/images/";
		switch(cell.getId())
		{
			case "v1" :
				setPopUpWindowData("Replace Parameter with Method", pathPrefix + "ReplaceParameterWithMethodMotivation.png",
						pathPrefix + "ReplaceParameterWithMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v2" :
				setPopUpWindowData("Add Parameter", pathPrefix + "AddParameterMotivation.png",
						pathPrefix + "AddParameterExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v3" :
				setPopUpWindowData("Parameterize Method", pathPrefix + "ParameterizeMethodMotivation.png",
						pathPrefix + "ParameterizeMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v4" :
				setPopUpWindowData("Separate Query from Modifier", pathPrefix + "SeparateQueryMotivation.png",
						pathPrefix + "SeparateQueryExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v5" :
				setPopUpWindowData("Remove Parameter", pathPrefix + "RemoveParameterMotivation.png",
						pathPrefix + "RemoveParameterExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v6" :
				dataForIdentification= new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Introduce Parameter Object", pathPrefix + "IntroduceParameterObjectMotivation.png",
						pathPrefix + "IntroduceParameterObjectExample.png", "/images/CustomIdentificationPlusTool.png", 
						new IntroduceParameterObjectIdentification(), dataForIdentification);
				break;
			case "v7" :
				dataForIdentification= new Object[3];
				dataForIdentification[0] = page;
				dataForIdentification[1] = display;
				dataForIdentification[2] = mainWindow;
				setPopUpWindowData("Extract Method", "/MethodCompositionView/images/ExtractMethodMotivation.png",
						"/MethodCompositionView/images/ExtractMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new ExtractMethodIdentification(), dataForIdentification);
				break;
			case "v8" :
				setPopUpWindowData("Remove Setting Method", pathPrefix + "RemoveSettingMethodMotivation.png",
						pathPrefix + "RemoveSettingMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v9" :
				setPopUpWindowData("Rename Method", pathPrefix + "RenameMethodMotivation.png",
						pathPrefix + "RenameMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v10" :
				setPopUpWindowData("Preserve Whole Object", pathPrefix + "PreserveWholeObjectMotivation.png",
						pathPrefix + "PreserveWholeObjectExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v11" :
				dataForIdentification = new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Move Method", "/FeatureMovementBetweenObjectsView/images/MoveMethodMotivation.png",
						"/FeatureMovementBetweenObjectsView/images/MoveMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new MoveMethodIdentification(), dataForIdentification);
				break;
			case "v12" :
				setPopUpWindowData("Hide Method", pathPrefix + "HideMethodMotivation.png",
						pathPrefix + "HideMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v13" :
				setPopUpWindowData("Change Value to Reference", "/DataOrganizationView/images/ChangeValueToReferenceMotivation.png",
						"/DataOrganizationView/images/ChangeValueToReferenceExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v14" :
				setPopUpWindowData("Replace Constructor with Factory Method", pathPrefix + "ReplaceWithFactoryMethodMotivation.png",
						pathPrefix + "ReplaceWithFactoryMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v15" :
				setPopUpWindowData("Replace Parameter with Explicit Methods", pathPrefix + "ReplaceWithExplicitMethodsMotivation.png",
						pathPrefix + "ReplaceWithExplicitMethodsExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v16" :
				setPopUpWindowData("Replace Conditional with Polymorphism", "/ConditionalExpressionSimplificationView/images/ReplaceConditionalWithPolymorphismMotivation.png",
						"/ConditionalExpressionSimplificationView/images/ReplaceConditionalWithPolymorphismExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v17" :
				setPopUpWindowData("Replace Exception with Test", pathPrefix + "ReplaceExceptionWithTestMotivation.png",
						pathPrefix + "ReplaceExceptionWithTestExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v18" :
				setPopUpWindowData("Replace Error Code with Exception", pathPrefix + "ReplaceErrorCodeWithExceptionMotivation.png",
						pathPrefix + "ReplaceErrorCodeWithExceptionExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v19" :
				setPopUpWindowData("Encapsulate Downcast", pathPrefix + "EncapsulateDowncastMotivation.png",
						pathPrefix + "EncapsulateDowncastExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			default:
				break;
		}
		RefactoringInfoWindow popUpWindow = new RefactoringInfoWindow(refactoringOptions);
		popUpWindow.setVisible(true);
	}
	
public void edgePopUp(mxCell cell){
		
		String pathPrefix = "/MethodCallImprovementView/images/";
		String imageFile = "/images/nothing.png";
		Dimension edgeDim = new Dimension(615, 465);
		switch(cell.getId())
		{
			case "e1" :
				imageFile = pathPrefix + "methodcallimpr-e1.png";
				break;
			case "e2" :
				imageFile = pathPrefix + "methodcallimpr-e2.png";
				break;	
			case "e3" :
				imageFile = pathPrefix + "methodcallimpr-e3.png";
				break;
			case "e4" :
				imageFile = pathPrefix + "methodcallimpr-e4.png";
				break;
			case "e5" :
				imageFile = pathPrefix + "methodcallimpr-e5.png";
				break;
			case "e6" :
				imageFile = pathPrefix + "methodcallimpr-e6.png";
				break;
			case "e7" :
				imageFile = pathPrefix + "methodcallimpr-e7.png";
				break;
			case "e8" :
			case "e9" :
				imageFile = pathPrefix + "methodcallimpr-e8e9.png";
				break;
			case "e10" :
				imageFile = pathPrefix + "methodcallimpr-e10.png";
				break;
			case "e11" :
				imageFile = pathPrefix + "methodcallimpr-e11.png";
				break;
			case "e12" :
				imageFile = pathPrefix + "methodcallimpr-e12.png";
				break;
			case "e13" :
				imageFile = pathPrefix + "methodcallimpr-e13.png";
				break;
			default:
				break;
		}
		PopupWindow edgeWindow = new PopupWindow(edgeDim, imageFile, "Edge description window");
		edgeWindow.setVisible(true);	
	}

		
	public void addMainVertices(mxGraph g, Object p) {
		/* This function generates the main vertices of the "Method Call Improvement" subgraph */
		vertices[0] = (mxCell) g.insertVertex(p, "v1", "Replace Parameter\nwith Method", 25, 65, 100, 50, "circle;fillColor=#93D1FF");			
		vertices[1] = (mxCell) g.insertVertex(p, "v2", "Add Parameter", 25, 240, 100, 50, "circle;fillColor=#93D1FF");
		vertices[2] = (mxCell) g.insertVertex(p, "v3", "Parameterize\nMethod", 25, 415, 100, 50, "circle;fillColor=#93D1FF");
		vertices[3] = (mxCell) g.insertVertex(p, "v4", "Separate Query\nfrom Modifier", 25, 590, 100, 50, "circle;fillColor=#93D1FF");
		vertices[4] = (mxCell) g.insertVertex(p, "v5", "Remove Parameter", 205, 65, 100, 50, "circle;fillColor=#93D1FF");
		vertices[5] = (mxCell) g.insertVertex(p, "v6", "Introduce Parameter\nObject", 200, 240, 110, 55, "circle;fillColor=#93D1FF");
		vertices[7] = (mxCell) g.insertVertex(p, "v8", "Remove Setting\nMethod", 375, 65, 100, 50, "circle;fillColor=#93D1FF");
		vertices[8] = (mxCell) g.insertVertex(p, "v9", "Rename Method", 375, 240, 100, 50, "circle;fillColor=#93D1FF");
		vertices[9] = (mxCell) g.insertVertex(p, "v10", "Preserve Whole\nObject", 375, 415, 100, 50, "circle;fillColor=#93D1FF");
		vertices[11] = (mxCell) g.insertVertex(p, "v12", "Hide Method", 625, 65, 100, 50, "circle;fillColor=#93D1FF");
		vertices[13] = (mxCell) g.insertVertex(p, "v14", "Replace\nConstructor with\nFactory Method", 625, 590, 110, 60, "circle;fillColor=#93D1FF");
		vertices[14] = (mxCell) g.insertVertex(p, "v15", "Replace\nParameter with\nExplicit Method", 875, 65, 100, 50, "circle;fillColor=#93D1FF");
		vertices[16] = (mxCell) g.insertVertex(p, "v17", "Replace Exception\nwith Test", 875, 415, 100, 50, "circle;fillColor=#93D1FF");
		vertices[17] = (mxCell) g.insertVertex(p, "v18", "Replace ErrorCode\nwith Exception", 875, 590, 100, 50, "circle;fillColor=#93D1FF");
		vertices[18] = (mxCell) g.insertVertex(p, "v19", "Encapsulate\nDowncast", 625, 240, 100, 50, "circle;fillColor=#93D1FF");
	}
	
	public void addMainVerticesRelations() {
		/* This function generates the relations of the main vertices of the "Method Call Improvement" subgraph */
		relationshipList.add(new Relationship(vertices[5], vertices[9], "succession", "e1", false));
		relationshipList.add(new Relationship(vertices[16], vertices[17], "succession", "e2", false));
		relationshipList.add(new Relationship(vertices[17], vertices[16], "succession", "e3", false));
		relationshipList.add(new Relationship(vertices[4], vertices[0], "isPartOf", "e4", false));
		relationshipList.add(new Relationship(vertices[1], vertices[2], "isPartOf", "e5", false));
		relationshipList.add(new Relationship(vertices[4], vertices[5], "isPartOf", "e6", false));
		relationshipList.add(new Relationship(vertices[1], vertices[5], "isPartOf", "e7", false));
	}
	
	public void addExternalVertices(mxGraph g, Object p) {
		/* This function generates the external vertices of the "Method Call Improvement" subgraph */
		externalVertices[0] = vertices[6] = (mxCell) g.insertVertex(p, "v7", "Extract Method", 200, 590, 100, 50, "fillColor=#00FFFF;fontColor=black");
		externalVertices[1] = vertices[10] = (mxCell) g.insertVertex(p, "v11", "Move Method", 375, 590, 100, 50, "fillColor=#E2D2B0;fontColor=black");
		externalVertices[2] = vertices[12] = (mxCell) g.insertVertex(p, "v13", "Change Value\nto Reference", 630, 415, 100, 50, "fillColor=#FEB9DF;fontColor=black");
		externalVertices[3] = vertices[15] = (mxCell) g.insertVertex(p, "v16", "Replace Conditional\nwith Polymorphism", 875, 240, 100, 50, "fillColor=#D1FA8A;fontColor=black");
	}
	
	public void addExternalVerticesRelations() {
		/* This function generates the relations of the external vertices of the "Method Call Improvement" subgraph */
		relationshipList.add(new Relationship(vertices[5], vertices[6], "succession", "e8", true));
		relationshipList.add(new Relationship(vertices[5], vertices[10], "succession", "e9", true));
		relationshipList.add(new Relationship(vertices[6], vertices[3], "isPartOf", "e10", true));
		relationshipList.add(new Relationship(vertices[13], vertices[12], "isPartOf", "e11", true));
		relationshipList.add(new Relationship(vertices[9], vertices[10], "insteadOf", "e12", true));
		relationshipList.add(new Relationship(vertices[14], vertices[15], "insteadOf", "e13", true));
	}
}
