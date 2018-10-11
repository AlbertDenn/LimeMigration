package backgroundData;

public class MocOurRolesxOperationData {

	public String rol;
	public String opid;
	
	
	public String toString() {
		return rol+","+opid;
	}
	
	public String toSQL() { 
		return opid+",'"+rol+"'";
	}
	
	
}
