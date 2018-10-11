package backgroundData;

public class Trivia_QuestionData {

		public String msgid;
		public String question_number;
		public String text;
		public String prize_on_correct;
		public String prize_on_incorrect;
		
		public String toString()
		{
			return msgid+","+question_number+","+text+","+prize_on_correct+","+prize_on_incorrect;
		}
		
		public String toSQL()
		{
			return msgid+","+question_number+","+prize_on_correct+","+prize_on_incorrect+",'"+text+"'";
		}
		
		
	}
