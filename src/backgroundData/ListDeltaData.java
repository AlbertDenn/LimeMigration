package backgroundData;

public class ListDeltaData {

	public String id;
	public String msgid;
	public String listid;
	public String activate;
	
	
	public String toString() {
		return id+","+msgid+","+listid+","+activate;
				
	}
	
	public String toSQL() {
		return id+",'"+activate+"',"+listid+","+msgid;
				
	}
	
}
