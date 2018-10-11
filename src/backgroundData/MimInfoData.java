package backgroundData;

public class MimInfoData {

	public String mimId;
	public String key;
	public String value;
	
	public String toString() {
		return mimId+","+key+","+value;
	}
	
	public String toSQL() {
		return "'"+key+"',"+mimId+",'"+value+"'";
	}
	
	
}
