package backgroundData;

public class QuestionsData {

	public String msgid;
	public String question_number;
	public String text;
	public String prize;
	
	public String toString()
	{
		return msgid+","+question_number+","+text+","+prize;
	}
	
	public String toSQL()
	{
		return msgid+","+question_number+","+prize+",'"+text+"'";
	}
	
	
}
