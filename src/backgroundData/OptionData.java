package backgroundData;

public class OptionData {

	public String msgid;
	public String option_number;
	public String question_number;
	public String text;
	public String keyphrase;
	public String correct;
	public String to_question_number;
	
	public String toString()
	{		
		return msgid+","+option_number+","+question_number+","+text+","+keyphrase+","+correct+","+to_question_number;
	}
	
	
	public String toSQL()
	{
		return msgid+","+option_number+","+question_number+","+correct+","+null+",'"+keyphrase+"',"+null+",'"+text+"',"+to_question_number;
	}
	
}
