package backgroundData;

public class MocRolesxUserData {

	public String login;
	public String rolename;
	
	
	public String toString() {
		return login+","+rolename;
	}
	
	public String toSQL() { 
		return "'"+login+"','"+rolename+"'";
	}
	
	
	
}
