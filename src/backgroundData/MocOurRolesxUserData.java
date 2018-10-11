package backgroundData;

public class MocOurRolesxUserData {

	public String login;
	public String rol;
	
	
	public String toString() {
		return login+","+rol;
	}
	
	public String toSQL() { 
		return "'"+login+"','"+rol+"'";
	}
	
}
