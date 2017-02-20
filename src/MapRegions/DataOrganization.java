package MapRegions;

import RefactoringDetectors.ExtractMethodIdentification;
import RefactoringDetectors.InlineMethodIdentification;
import RefactoringDetectors.MoveMethodIdentification;
import UI.PopupWindow;
import UI.MainWindow;
import UI.RefactoringInfoWindow;
import relationshipEdges.Relationship;

import java.awt.Dimension;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

/*	This class handles the creation of the Data Organization map.
 * 	It's responsible for the graph creation as well as the 3-slide information window
 * 	for each of its refactorings.
 */
public class DataOrganization extends RefactoringRegion {
		
	public DataOrganization(MainWindow p) {
		super(p, 24, 8);
		refactoringLabel.setText("Data Organization");
	}
	
	public void graphNodePopUp(mxCell cell)
	{
		String pathPrefix = "/DataOrganizationView/images/";
		switch(cell.getId())
		{
			case "v1" :
				setPopUpWindowData("Substitute Algorithm", "/MethodCompositionView/images/SubstituteAlgorithmMotivation.png",
						"/MethodCompositionView/images/SubstituteAlgorithmExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v2" :
				setPopUpWindowData("Change Bidirectional Assosiation to Unidirectional", pathPrefix + "BidirectionalToUndirectionalMotivation.png",
						pathPrefix + "UndirectionalToBidirectionalExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v3" :
				setPopUpWindowData("Change Unidirectional Assosiation to Bidirectional", pathPrefix + "UndirectionalToBidirectionalMotivation.png",
						"/MethodCompositionView/images/SubstituteAlgorithmExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v4" :
				setPopUpWindowData("Replace Data Value with Object", pathPrefix + "ReplaceDataValueWithObjectMotivation.png",
						pathPrefix + "ReplaceDataValueWithObjectExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v5" :
				setPopUpWindowData("Change Value to Reference", pathPrefix + "ChangeValueToReferenceMotivation.png",
						pathPrefix + "ChangeValueToReferenceExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v6" :
				setPopUpWindowData("Self Encapsulate Field", pathPrefix + "SelfEncapsulateFieldMotivation.png",
						pathPrefix + "SelfEncapsulateFieldExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v7" :
				setPopUpWindowData("Duplicate Observed Data", pathPrefix + "DuplicateObservedDataMotivation.png",
						pathPrefix + "DuplicateObservedDataExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v8" :
				dataForIdentification = new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Inline Method", "/MethodCompositionView/images/InlineMethodMotivation.png",
						"/MethodCompositionView/images/InlineMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new InlineMethodIdentification(), dataForIdentification);
				break;
			case "v9" :
				setPopUpWindowData("Replace Subclass with Fields", pathPrefix + "ReplaceSubclassWithFieldsMotivation.png",
						pathPrefix + "ReplaceSubclassWithFieldsExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v10" :
				setPopUpWindowData("Replace Constructor with Factory Method", "/MethodCallImprovementView/images/ReplaceWithFactoryMethodMotivation.png",
						"/MethodCallImprovementView/images/ReplaceWithFactoryMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v11" :
				setPopUpWindowData("Replace Record with Data Class", pathPrefix + "ReplaceRecordWithDataClassMotivation.png",
						pathPrefix + "ReplaceRecordWithDataClassExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v12" :
				setPopUpWindowData("Replace Array with Object", pathPrefix + "ReplaceArrayWithObjectMotivation.png",
						pathPrefix + "ReplaceArrayWithObjectExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v13" :
				dataForIdentification = new Object[1];
				dataForIdentification[0] = mainWindow;
				setPopUpWindowData("Move Method", "/FeatureMovementBetweenObjectsView/images/MoveMethodMotivation.png",
						"/FeatureMovementBetweenObjectsView/images/MoveMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new MoveMethodIdentification(), dataForIdentification);
				break;
			case "v14" :
				setPopUpWindowData("Encapsulate Collection", pathPrefix + "EncapsulateCollectionMotivation.png",
						pathPrefix + "EncapsulateCollectionExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v15" :
				dataForIdentification= new Object[3];
				dataForIdentification[0] = page;
				dataForIdentification[1] = display;
				dataForIdentification[2] = mainWindow;
				setPopUpWindowData("Extract Method", "/MethodCompositionView/images/ExtractMethodMotivation.png",
						"/MethodCompositionView/images/ExtractMethodExample.png", "/images/CustomIdentificationPlusTool.png", 
						new ExtractMethodIdentification(), dataForIdentification);
				break;
			case "v16" :
				setPopUpWindowData("Encapsulate Field", pathPrefix + "EncapsulateFieldMotivation.png",
						pathPrefix + "EncapsulateFieldExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v17" :
				setPopUpWindowData("Replace Type Code with Subclasses", pathPrefix + "ReplaceTypeCodeWithSubclassesMotivation.png",
						pathPrefix + "ReplaceTypeCodeWithSubclassesExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v18" :
				setPopUpWindowData("Replace Type Code with Class", pathPrefix + "ReplaceTypeCodeWithClassMotivation.png",
						pathPrefix + "ReplaceTypeCodeWithClassExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v19" :
				setPopUpWindowData("Rename Method", "/MethodCallImprovementView/images/RenameMethodMotivation.png",
						"/MethodCallImprovementView/images/RenameMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v20" :
				setPopUpWindowData("Replace Conditional With Polymorphism", "/ConditionalExpressionSimplificationView/images/ReplaceConditionalWithPolymorphismMotivation.png",
						"/ConditionalExpressionSimplificationView/images/ReplaceConditionalWithPolymorphismExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v21" :
				setPopUpWindowData("Replace Type Code with State/Strategy", pathPrefix + "ReplaceTypeCodeWithStateMotivation.png",
						pathPrefix + "ReplaceTypeCodeWithStateExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v22" :
				setPopUpWindowData("Replace Magic Number", pathPrefix + "ReplaceMagicNumberMotivation.png",
						pathPrefix + "ReplaceMagicNumberExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v23" :
				setPopUpWindowData("Remove Setting Method", "/MethodCallImprovementView/images/RemoveSettingMethodMotivation.png",
						"/MethodCallImprovementView/images/RemoveSettingMethodExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			case "v24" :
				setPopUpWindowData("Change Reference to Value", pathPrefix + "ChangeReferenceToValueMotivation.png",
						pathPrefix + "ChangeReferenceToValueExample.png", "/images/NoIdentificationNoTool.png", 
						null, null);
				break;
			default :
				break;				
		}
		RefactoringInfoWindow popUpWindow = new RefactoringInfoWindow(refactoringOptions);
		popUpWindow.setVisible(true);
	}
	
public void edgePopUp(mxCell cell){
		
		String pathPrefix = "/DataOrganizationView/images/";
		String imageFile = "/images/nothing.png";
		Dimension edgeDim = new Dimension(615, 465);
		switch(cell.getId())
		{
			case "e1" :
			case "e2" :
				imageFile = pathPrefix + "dataorganization-e1e2.png";
				break;	
			case "e3" :
				imageFile = pathPrefix + "dataorganization-e3.png";
				break;
			case "e4" :
				imageFile = pathPrefix + "dataorganization-e4.png";
				break;
			case "e5" :
				imageFile = pathPrefix + "dataorganization-e5.png";
				break;
			case "e6" :
				imageFile = pathPrefix + "dataorganization-e6.png";
				break;
			case "e7" :
			case "e8" :
				imageFile = pathPrefix + "dataorganization-e7e8.png";
				break;
			case "e9" :
				imageFile = pathPrefix + "dataorganization-e9.png";
				break;
			case "e10" :
				imageFile = pathPrefix + "dataorganization-e10.png";
				break;
			case "e11" :
				imageFile = pathPrefix + "dataorganization-e11.png";
				break;
			case "e12" :
			case "e13" :
				imageFile = pathPrefix + "dataorganization-e12e13.png";				
				break;
			case "e14" :
				imageFile = pathPrefix + "dataorganization-e14.png";
				break;
			case "e15" :
				imageFile = pathPrefix + "dataorganization-e15.png";
				break;
			case "e16" :
				imageFile = "/MethodCallImprovementView/images/methodcallimpr-e11.png";
				break;
			case "e17" :
				imageFile = pathPrefix + "dataorganization-e17.png";
				break;
			case "e18" :
			case "e19" :
				imageFile = pathPrefix + "dataorganization-e18e19.png";
				break;
			case "e20" :
				imageFile = pathPrefix + "dataorganization-e20.png";
				break;
			case "e21" :
				imageFile = pathPrefix + "dataorganization-e21.png";
				break;
			case "e22" :
				imageFile = pathPrefix + "dataorganization-e22.png";
				break;
			default:
				break;
		}
		PopupWindow edgeWindow = new PopupWindow(edgeDim, imageFile, "Edge description window");
		edgeWindow.setVisible(true);	
	}

			
	public void addMainVertices(mxGraph g, Object p) {
		/* This function generates the main vertices of the "Data Organization" subgraph */
		vertices[1] = (mxCell) g.insertVertex(p, "v2", "Change\nBidirectional Association\nto Unidirectional", 25, 150, 120, 55, "circle;fillColor=#FEB9DF");
		vertices[2] = (mxCell) g.insertVertex(p, "v3", "Change\nUnidirectional Association\nto Bidirectional", 25, 305, 123, 55, "circle;fillColor=#FEB9DF");
		vertices[3] = (mxCell) g.insertVertex(p, "v4", "Replace\nData Value\nwith Object", 25, 450, 100, 50, "circle;fillColor=#FEB9DF");
		vertices[4] = (mxCell) g.insertVertex(p, "v5", "Change Value\nto Reference", 25, 600, 100, 50, "circle;fillColor=#FEB9DF");
		vertices[5] = (mxCell) g.insertVertex(p, "v6", "Self\nEncapsulate Field", 200, 15, 100, 50, "circle;fillColor=#FEB9DF");
		vertices[6] = (mxCell) g.insertVertex(p, "v7", "Duplicate\nObserved Data", 200, 150, 100,50, "circle;fillColor=#FEB9DF");
		vertices[8] = (mxCell) g.insertVertex(p, "v9", "Replace Subclass\nwith Fields", 200, 450, 100, 50, "circle;fillColor=#FEB9DF");
		vertices[10] = (mxCell) g.insertVertex(p, "v11", "Replace Record\nwith Data Class", 875, 15, 100, 50, "circle;fillColor=#FEB9DF");
		vertices[11] = (mxCell) g.insertVertex(p, "v12", "Replace Array\nwith Object", 875, 150, 100, 50, "circle;fillColor=#FEB9DF");
		vertices[13] = (mxCell) g.insertVertex(p, "v14", "Encapsulate\nCollection", 875, 450, 100, 50, "circle;fillColor=#FEB9DF");
		vertices[15] = (mxCell) g.insertVertex(p, "v16", "Encapsulate Field", 700, 15, 100, 50, "circle;fillColor=#FEB9DF");
		vertices[16] = (mxCell) g.insertVertex(p, "v17", "Replace Type Code\nwith Subclasses", 700, 305, 105, 55, "circle;fillColor=#FEB9DF");
		vertices[17] = (mxCell) g.insertVertex(p, "v18", "Replace Type Code\nwith Class", 700, 450, 105, 55, "circle;fillColor=#FEB9DF");
		vertices[20] = (mxCell) g.insertVertex(p, "v21", "Replace\nType Code with\nState/Strategy", 525, 450, 100, 50, "circle;fillColor=#FEB9DF");
		vertices[21] = (mxCell) g.insertVertex(p, "v22", "Replace Magic\nNumber with\nSymbolic Constant", 525, 600, 110, 55, "circle;fillColor=#FEB9DF");
		vertices[23] = (mxCell) g.insertVertex(p, "v24", "Change Reference\nto Value", 525, 150, 100, 50, "circle;fillColor=#FEB9DF");
	}
	
	public void addMainVerticesRelations() {
		/* This function generates the relations of the main vertices of the "Data Organization" subgraph */
		relationshipList.add(new Relationship(vertices[1], vertices[2], "succession", "e1", false));
		relationshipList.add(new Relationship(vertices[2], vertices[1], "succession", "e2", false));
		relationshipList.add(new Relationship(vertices[3], vertices[4], "succession", "e3", false));
		relationshipList.add(new Relationship(vertices[5], vertices[1], "isPartOf", "e4", false));
		relationshipList.add(new Relationship(vertices[5], vertices[6], "isPartOf", "e5", false));
		relationshipList.add(new Relationship(vertices[10], vertices[11], "insteadOf", "e6", false));
		relationshipList.add(new Relationship(vertices[17], vertices[16], "insteadOf", "e7", false));
		relationshipList.add(new Relationship(vertices[17], vertices[20], "insteadOf", "e8", false));
		relationshipList.add(new Relationship(vertices[17], vertices[21], "insteadOf", "e9", false));
	}
	
	public void addExternalVertices(mxGraph g, Object p) {
		/* This function generates the external vertices of the "Data Organization" subgraph */
		externalVertices[0] = vertices[0] = (mxCell) g.insertVertex(p, "v1", "Substitute Algorithm", 35, 15, 100, 50, "fillColor=#00FFFF;fontColor=black");	
		externalVertices[1] = vertices[7] = (mxCell) g.insertVertex(p, "v8", "Inline Method", 200, 305, 100, 50, "fillColor=#00FFFF;fontColor=black");
		externalVertices[2] = vertices[9] = (mxCell) g.insertVertex(p, "v10", "Replace\nConstructor with\nFactory Method", 200, 600, 100, 50, "fillColor=#93D1FF;fontColor=black");
		externalVertices[3] = vertices[12] = (mxCell) g.insertVertex(p, "v13", "Move Method", 875, 305, 100, 50, "fillColor=#E2D2B0;fontColor=black");
		externalVertices[4] = vertices[14] = (mxCell) g.insertVertex(p, "v15", "Extract Method", 875, 600, 100, 50, "fillColor=#00FFFF;fontColor=black");
		externalVertices[5] = vertices[18] = (mxCell) g.insertVertex(p, "v19", "Rename Method", 700, 600, 100, 50, "fillColor=#93D1FF;fontColor=black");
		externalVertices[6] = vertices[19] = (mxCell) g.insertVertex(p, "v20", "Replace Conditional\nWith Polymorphism", 525, 305, 100, 50, "fillColor=#D1FA8A;fontColor=black");
		externalVertices[7] = vertices[22] = (mxCell) g.insertVertex(p, "v23", "Remove\nSetting Method", 525, 15, 100, 50, "fillColor=#93D1FF;fontColor=black");
	}
	
	public void addExternalVerticesRelations() {
		/* This function generates the relations of the external vertices of the "Data Organization" subgraph */
		relationshipList.add(new Relationship(vertices[11], vertices[12], "succession", "e10", true));
		relationshipList.add(new Relationship(vertices[15], vertices[12], "succession", "e11", true));
		relationshipList.add(new Relationship(vertices[16], vertices[19], "succession", "e12", true));
		relationshipList.add(new Relationship(vertices[20], vertices[19], "succession", "e13", true));
		relationshipList.add(new Relationship(vertices[0], vertices[1], "isPartOf", "e14", true));
		relationshipList.add(new Relationship(vertices[7], vertices[8], "isPartOf", "e15", true));
		relationshipList.add(new Relationship(vertices[9], vertices[4], "isPartOf", "e16", true));
		relationshipList.add(new Relationship(vertices[9], vertices[8], "isPartOf", "e17", true));
		relationshipList.add(new Relationship(vertices[12], vertices[13], "isPartOf", "e18", true));
		relationshipList.add(new Relationship(vertices[14], vertices[13], "isPartOf", "e19", true));
		relationshipList.add(new Relationship(vertices[18], vertices[13], "isPartOf", "e20", true));
		relationshipList.add(new Relationship(vertices[18], vertices[17], "isPartOf", "e21", true));
		relationshipList.add(new Relationship(vertices[22], vertices[23], "isPartOf", "e22", true));
	}
}