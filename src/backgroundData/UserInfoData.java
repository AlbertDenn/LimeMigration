package backgroundData;

public class UserInfoData {

	public String key;
	public String mobilenumber;
	public String itemid;
	public String listid;
	public String value;
	
	public String toString() {
		return key+","+mobilenumber+","+itemid+","+listid+","+value;
	}
	
	public String toSQL() {
		return "'"+key+"','"+mobilenumber+"',"+itemid+","+listid+",'"+value+"'";
	}
	
}
