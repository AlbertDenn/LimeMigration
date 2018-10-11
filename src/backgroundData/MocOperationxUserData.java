package backgroundData;

public class MocOperationxUserData {

	public String login;
	public String opid;
	
	
	public String toString() {
		return login+","+opid;
	}
	
	public String toSQL() { 
		return "'"+login+"',"+opid;
	}
	
}
