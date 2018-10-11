package backgroundData;

public class MocInstancexUserData {

	public String login;
	public String insid;
	
	
	public String toString() {
		return login+","+insid;
	}
	
	public String toSQL() { 
		return insid+",'"+login+"'";
	}
	
}
