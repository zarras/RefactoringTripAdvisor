package relationshipEdges;

import com.mxgraph.model.mxCell;

public class Relationship {

	private mxCell source;
	private mxCell destination;
	private String type;
	private String id;
	private boolean external;
	
	public Relationship(mxCell source, mxCell destination, String type, String id, boolean external)  {
		this.source = source;
		this.destination = destination;
		this.type = type;
		this.id = id;
		this.external = external;
	}
	
	public Relationship(mxCell source, mxCell destination, String type, String id)  {
		this.source = source;
		this.destination = destination;
		this.type = type;
		this.id = id;
		this.external = false;
	}
	
	public mxCell getSource(){
		return source;
	}
	public mxCell getDestination(){
		return destination;
	}
	public String getType(){
		return type;
	}
	public String getID(){
		return id;
	}
	public boolean isExternal(){
		return external;
	}
}
