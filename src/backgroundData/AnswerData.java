package backgroundData;

public class AnswerData {

	public String ans_index;
	public String answerset_id;
	public String msgid;
	public String label;
	
	
	public String toString() {
		return ans_index+","+answerset_id+","+msgid+","+label;
	}
	
	public String toSQL() {
		
		
		return ans_index+","+answerset_id.trim()+","+msgid+",'"+label+"'";
	}
	
	
}
