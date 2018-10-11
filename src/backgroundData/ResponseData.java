package backgroundData;

public class ResponseData {

	public String ans_index;
	public String responseset_id;
	public String msgid;
	public String response;
	
	
	public String toString() {
		return ans_index+","+msgid+","+responseset_id+","+response;
	}
	
	public String toSQL() {
		
		return ans_index+","+msgid+","+responseset_id.trim()+",'"+response+"'";
	}
	
}
