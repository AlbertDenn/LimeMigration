package backgroundData;

public class DeliveryData {

	public String id;
	public String msgid;
	public String opensat;
	public String closesat;
	
	
	public String toString() {
		return id+","+msgid+","+opensat+","+closesat;
	}
	
	public String toSQL() {
		
		if(closesat.equals("null"))
			closesat=null;
		else
			closesat="TIMESTAMP '"+closesat+"'";
		
		if(opensat.equals("null"))
			opensat=null;
		else
			opensat="TIMESTAMP '"+opensat+"'";
		
		return id+","+closesat+","+opensat+","+msgid;
	}
	
}
