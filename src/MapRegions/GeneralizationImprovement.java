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

/*	This class handles the creation of the Generalization Improvement map.
 * 	It's responsible for the graph creation as well as the 3-slide information window
 * 	for each of its refactorings.
 */
public class GeneralizationImprovement extends RefactoringRegion {
	
	public GeneralizationImprovement(MainWindow p)
	{
		super(p, 21, 9);
		refactoringLabel.setText("Generalization Improvement");
	}

	public void graphNodePopUp(mxCell cell)
	{
		String pathPrefix = "/GeneralizationImprovementView/images/";
		switch(cell.getId())
		{
			case "v1" :
				setPopUpWindowData("Self Encapsulate Field", "/DataOrganizationView/images/SelfEncapsulateFieldMotivation.png",
						"/DataOrganizationView/images/SelfEncapsulateFieldExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v2" :
				setPopUpWindowData("Pull Up Field", pathPrefix + "PullUpFieldMotivation.png",
						pathPrefix + "PullUpFieldExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v3" :
				setPopUpWindowData("Collapse Hierarchy", pathPrefix + "CollapseHierarchyMotivation.png",
						pathPrefix + "CollapseHierarchyExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v4" :
				setPopUpWindowData("Push Down Field", pathPrefix + "PushDownFieldMotivation.png",
						pathPrefix + "PushDownFieldExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v5" :
				setPopUpWindowData("Replace Delegation with Inheritance", pathPrefix + "ReplaceDelegationWithInheritanceMotivation.png",
						pathPrefix + "ReplaceDelegationWithInheritanceExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v6" :
				setPopUpWindowData("Push Down Method", pathPrefix + "PushDownMethodMotivation.png",
						pathPrefix + "PushDownMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v7" :
				setPopUpWindowData("Replace Inheritance with Delegation", pathPrefix + "ReplaceInheritanceWithDelegationMotivation.png",
						pathPrefix + "ReplaceInheritanceWithDelegationExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v8" :
				setPopUpWindowData("Extract Interface", pathPrefix + "ExtractInterfaceMotivation.png",
						pathPrefix + "ExtractInterfaceExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v9" :
				dataForIdentification = new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Extract Class", "/FeatureMovementBetweenObjectsView/images/ExtractClassMotivation.png",
						"/FeatureMovementBetweenObjectsView/images/ExtractClassExample.png", "/images/CustomIdentificationPlusTool.png", 
						new ExtractClassIdentification(), dataForIdentification);
				break;
			case "v10" :
				setPopUpWindowData("Replace Type Code with State/Strategy", "/DataOrganizationView/images/ReplaceTypeCodeWithStateMotivation.png",
						"/DataOrganizationView/images/ReplaceTypeCodeWithStateExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v11" :
				setPopUpWindowData("Replace Type Code with Subclasses", "/DataOrganizationView/images/ReplaceTypeCodeWithSubclassesMotivation.png",
						"/DataOrganizationView/images/ReplaceTypeCodeWithSubclassesExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v12" :
				dataForIdentification = new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Move Method", "/FeatureMovementBetweenObjectsView/images/MoveMethodMotivation.png",
						"/FeatureMovementBetweenObjectsView/images/MoveMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new MoveMethodIdentification(), dataForIdentification);
				break;
			case "v13" :
				setPopUpWindowData("Replace Conditional with Polymorphism", "/ConditionalExpressionSimplificationView/images/ReplaceConditionalWithPolymorphismMotivation.png",
						"/ConditionalExpressionSimplificationView/images/ReplaceConditionalWithPolymorphismExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v14" :
				setPopUpWindowData("Replace Constructor with Factory Method", "/MethodCallImprovementView/images/ReplaceWithFactoryMethodMotivation.png",
						"/MethodCallImprovementView/images/ReplaceWithFactoryMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v15" :
				setPopUpWindowData("Extract Subclass", pathPrefix + "ExtractSubclassMotivation.png",
						pathPrefix + "ExtractSubclassExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v16" :
				setPopUpWindowData("Pull Up Constructor Body", pathPrefix + "PullUpConstructorBodyMotivation.png",
						pathPrefix + "PullUpConstructorBodyExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v17" :
				setPopUpWindowData("Rename Method", "/MethodCallImprovementView/images/RenameMethodMotivation.png",
						"/MethodCallImprovementView/images/RenameMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v18" :
				setPopUpWindowData("Extract Superclass", pathPrefix + "ExtractSuperclassMotivation.png",
						pathPrefix + "ExtractSuperclassExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v19" :
				dataForIdentification = new Object[3];
				dataForIdentification[0] = page;
				dataForIdentification[1] = display;
				dataForIdentification[2] = mainWindow;
				setPopUpWindowData("Extract Method", "/MethodCompositionView/images/ExtractMethodMotivation.png",
						"/MethodCompositionView/images/ExtractMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new ExtractMethodIdentification(), dataForIdentification);
				break;
			case "v20" :
				setPopUpWindowData("Pull Up Method", pathPrefix + "PullUpMethodMotivation.png",
						pathPrefix + "PullUpMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v21" :
				setPopUpWindowData("Form Template Method", pathPrefix + "FormTemplateMethodMotivation.png",
						pathPrefix + "FormTemplateMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			default:
				break;
		}
		RefactoringInfoWindow popUpWindow = new RefactoringInfoWindow(refactoringOptions);
		popUpWindow.setVisible(true);
	}
	
public void edgePopUp(mxCell cell){
		
		String pathPrefix = "/GeneralizationImprovementView/images/";
		String imageFile = "/images/nothing.png";
		Dimension edgeDim = new Dimension(615, 465);
		switch(cell.getId())
		{
			case "e1" :
			case "e2" :
				imageFile = pathPrefix + "generalizationimpr-e1e2.png";
				break;	
			case "e3" :
				imageFile = pathPrefix + "generalizationimpr-e3.png";
				break;
			case "e4" :
			case "e5" :
			case "e6" :
			case "e12" :
				imageFile = pathPrefix + "generalizationimpr-e4e5e6e12.png";
				break;
			case "e7" :
			case "e8" :
				imageFile = pathPrefix + "generalizationimpr-e7e8.png";
				break;
			case "e9" :
			case "e10" :
			case "e11" :
				imageFile = pathPrefix + "generalizationimpr-e9e10e11.png";
				break;
			case "e13" :
			case "e20" :
				imageFile = pathPrefix + "generalizationimpr-e13e20.png";
				break;
			case "e14" :
				imageFile = pathPrefix + "generalizationimpr-e14.png";
				break;
			case "e15" :
			case "e29" :
				imageFile = pathPrefix + "generalizationimpr-e15e29.png";
				break;
			case "e16" :
				imageFile = pathPrefix + "generalizationimpr-e16.png";
				break;
			case "e17" :
				imageFile = pathPrefix + "generalizationimpr-e17.png";
				break;	
			case "e18" :
				imageFile = pathPrefix + "generalizationimpr-e18.png";
				break;
			case "e19" :
			case "e24" :
				imageFile = pathPrefix + "generalizationimpr-e19e24.png";
				break;
			case "e21" :
			case "e22" :
				imageFile = pathPrefix + "generalizationimpr-e21e22.png";
				break;
			case "e23" :
				imageFile = pathPrefix + "generalizationimpr-e23.png";
				break;
			case "e25" :
				imageFile = pathPrefix + "generalizationimpr-e25.png";
				break;
			case "e26" :
				imageFile = pathPrefix + "generalizationimpr-e26.png";
				break;
			case "e27" :
				imageFile = pathPrefix + "generalizationimpr-e27.png";
				break;
			case "e28" :
				imageFile = pathPrefix + "generalizationimpr-e28.png";				
				break;
			case "e30" :
			case "e31" :
				imageFile = pathPrefix + "generalizationimpr-e30e31.png";
				break;
			case "e32" :
				imageFile = pathPrefix + "generalizationimpr-e32.png";
				break;
			default:
				break;
		}
		PopupWindow edgeWindow = new PopupWindow(edgeDim, imageFile, "Edge description window");
		edgeWindow.setVisible(true);	
}
	
	public void addMainVertices(mxGraph g, Object p) {
		/* This function generates the main vertices of the "Generalization Improvement" subgraph */
		vertices[1] = (mxCell) g.insertVertex(p, "v2", "Pull Up Field", 25, 135, 100, 50, "circle;fillColor=#DCD1EF");
		vertices[2] = (mxCell) g.insertVertex(p, "v3", "Collapse Hierarchy", 25, 255, 100, 50, "circle;fillColor=#DCD1EF");
		vertices[3] = (mxCell) g.insertVertex(p, "v4", "Push Down Field", 25, 480, 100, 50, "circle;fillColor=#DCD1EF");
		vertices[4] = (mxCell) g.insertVertex(p, "v5", "Replace Delegation\nwith Inheritance", 25, 600, 105, 55, "circle;fillColor=#DCD1EF");
		vertices[5] = (mxCell) g.insertVertex(p, "v6", "Push Down Method", 150, 360, 100, 50, "circle;fillColor=#DCD1EF");
		vertices[6] = (mxCell) g.insertVertex(p, "v7", "Replace Inheritance\nwith Delegation", 200, 600, 105,55, "circle;fillColor=#DCD1EF");
		vertices[7] = (mxCell) g.insertVertex(p, "v8", "Extract Interface", 350, 600, 100, 50, "circle;fillColor=#DCD1EF");
		vertices[14] = (mxCell) g.insertVertex(p, "v15", "Extract Subclass", 700, 480, 100, 50, "circle;fillColor=#DCD1EF");
		vertices[15] = (mxCell) g.insertVertex(p, "v16", "Pull Up\nConstructor Body", 875, 15, 100, 50, "circle;fillColor=#DCD1EF");		
		vertices[17] = (mxCell) g.insertVertex(p, "v18", "Extract Superclass", 350, 190, 100, 50, "circle;fillColor=#DCD1EF");
		vertices[19] = (mxCell) g.insertVertex(p, "v20", "Pull Up Method", 200, 15, 100, 50, "circle;fillColor=#DCD1EF");
		vertices[20] = (mxCell) g.insertVertex(p, "v21", "Form\nTemplate Method", 700, 135, 100, 50, "circle;fillColor=#DCD1EF");
	}
	
	public void addMainVerticesRelations() {
		/* This function generates the relations of the main vertices of the "Generalization Improvement" subgraph */
		relationshipList.add(new Relationship(vertices[4], vertices[6], "succession", "e1", false));
		relationshipList.add(new Relationship(vertices[6], vertices[4], "succession", "e2", false));
		relationshipList.add(new Relationship(vertices[17], vertices[6], "succession", "e3", false));
		relationshipList.add(new Relationship(vertices[1], vertices[2], "isPartOf", "e4", false));
		relationshipList.add(new Relationship(vertices[5], vertices[2], "isPartOf", "e5", false));
		relationshipList.add(new Relationship(vertices[3], vertices[2], "isPartOf", "e6", false));
		relationshipList.add(new Relationship(vertices[5], vertices[14], "isPartOf", "e7", false));
		relationshipList.add(new Relationship(vertices[3], vertices[14], "isPartOf", "e8", false));
		relationshipList.add(new Relationship(vertices[15], vertices[17], "isPartOf", "e9", false));
		relationshipList.add(new Relationship(vertices[1], vertices[17], "isPartOf", "e10", false));
		relationshipList.add(new Relationship(vertices[19], vertices[17], "isPartOf", "e11", false));
		relationshipList.add(new Relationship(vertices[19], vertices[2], "isPartOf", "e12", false));
		relationshipList.add(new Relationship(vertices[1], vertices[19], "isPartOf", "e13", false));
		relationshipList.add(new Relationship(vertices[19], vertices[20], "isPartOf", "e14", false));
		relationshipList.add(new Relationship(vertices[20], vertices[17], "isPartOf", "e15", false));
		relationshipList.add(new Relationship(vertices[17], vertices[7], "insteadOf", "e16", false));
	}
	
	public void addExternalVertices(mxGraph g, Object p) {
		/* This function generates the external vertices of the "Generalization Improvement" subgraph */
		externalVertices[0] = vertices[0] = (mxCell) g.insertVertex(p, "v1", "Self\nEncapsulate Field", 25, 15, 100, 50, "fillColor=#FEB9DF;fontColor=black");
		externalVertices[1] = vertices[8] = (mxCell) g.insertVertex(p, "v9", "Extract Class", 500, 600, 100, 50, "fillColor=#E2D2B0;fontColor=black");
		externalVertices[2] = vertices[9] = (mxCell) g.insertVertex(p, "v10", "Replace Typecode\nwith\nState/Strategy", 625, 600, 100, 50, "fillColor=#FEB9DF;fontColor=black");
		externalVertices[3] = vertices[10] = (mxCell) g.insertVertex(p, "v11", "Replace Typecode\nwith Subclasses", 750, 600, 100, 50, "fillColor=#FEB9DF;fontColor=black");
		externalVertices[4] = vertices[11] = (mxCell) g.insertVertex(p, "v12", "Move Method", 875, 600, 100, 50, "fillColor=#E2D2B0;fontColor=black");
		externalVertices[5] = vertices[12] = (mxCell) g.insertVertex(p, "v13", "Replace Conditional\nWith Polymorphism", 875, 480, 100, 50, "fillColor=#D1FA8A;fontColor=black");
		externalVertices[6] = vertices[13] = (mxCell) g.insertVertex(p, "v14", "Replace Constructor\nwith\nFactory Method", 875, 360, 100, 50, "fillColor=#93D1FF;fontColor=black");
		externalVertices[7] = vertices[16] = (mxCell) g.insertVertex(p, "v17", "Rename Method", 700, 280, 100, 50, "fillColor=#93D1FF;fontColor=black");
		externalVertices[8] = vertices[18] = (mxCell) g.insertVertex(p, "v19", "Extract Method", 500, 15, 100, 50, "fillColor=#00FFFF;fontColor=black");
	}
	
	public void addExternalVerticesRelations() {
		/* This function generates the relations of the external vertices of the "Generalization Improvement" subgraph */
		relationshipList.add(new Relationship(vertices[1], vertices[0], "succession", "e17", true));
		relationshipList.add(new Relationship(vertices[18], vertices[19], "succession", "e18", true));
		relationshipList.add(new Relationship(vertices[0], vertices[14], "isPartOf", "e19", true));
		relationshipList.add(new Relationship(vertices[0], vertices[19], "isPartOf", "e20", true));
		relationshipList.add(new Relationship(vertices[9], vertices[14], "isPartOf", "e21", true));
		relationshipList.add(new Relationship(vertices[10], vertices[14], "isPartOf", "e22", true));
		relationshipList.add(new Relationship(vertices[11], vertices[14], "isPartOf", "e23", true));
		relationshipList.add(new Relationship(vertices[12], vertices[14], "isPartOf", "e24", true));
		relationshipList.add(new Relationship(vertices[13], vertices[14], "isPartOf", "e25", true));
		relationshipList.add(new Relationship(vertices[16], vertices[14], "isPartOf", "e26", true));
		relationshipList.add(new Relationship(vertices[16], vertices[17], "isPartOf", "e27", true));
		relationshipList.add(new Relationship(vertices[16], vertices[20], "isPartOf", "e28", true));
		relationshipList.add(new Relationship(vertices[18], vertices[17], "isPartOf", "e29", true));
		relationshipList.add(new Relationship(vertices[8], vertices[14], "insteadOf", "e30", true));
		relationshipList.add(new Relationship(vertices[8], vertices[17], "insteadOf", "e31", true));
		relationshipList.add(new Relationship(vertices[15], vertices[13], "insteadOf", "e32", true));
	}
}