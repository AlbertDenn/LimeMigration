package backgroundData;

public class Moc_ApplicationData {

	public String id;
	public String description;
	
	
	public String toString() {
		return id+","+description;
	}
	
	public String toSQL() {
	
		return "'"+id+"','"+description+"'";

	}
	
}
