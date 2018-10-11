package backgroundData;

public class WordsData {

	public String id;
	public String text;
	public String msgid;
	public String complexity;
	
	
	public String toString() {
		return id+","+text+","+msgid+","+complexity;
	}
	
	public String toSQL() {
		return id+","+complexity+",'"+text+"',"+msgid;
	}
	
}
