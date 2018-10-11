package backgroundData;

public class KeyphraseData {

	public String id;
	public String ans_index;
	public String keyphrase;
	public String answerset_id;
	public String msgid;
	
	public String toString() {
		return id+","+ans_index+","+keyphrase+","+answerset_id+","+msgid;
	}
	
	public String toSQL() {
		return id+","+ans_index+",'"+keyphrase+"','"+answerset_id+"',"+msgid;
	}
	
}
