package backgroundData;

public class MocOurRolesData {

	public String rol;
	public String description;
	public String level_op;
	public String messageid;
	
	
	public String toString() {
		return rol+"|"+description+"|"+level_op+"|"+messageid;
	}
	
	public String toSQL() {
		return "'"+rol+"','"+description+"',"+level_op+","+messageid;

	}
	
	
}
