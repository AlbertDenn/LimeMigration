package backgroundData;

public class MsgData {

	public 	String id;
	public String msgtypeid;
	public String description;
	public String timetolive;
	public String name;
	public String message;
	public String locked;
	public String createdonInsid;
	public String functionTag;
	public String wildcards;
	public String prefixid;
	public String pay_to;
	public String pmtid;
	public String keyphrasesbegin;
	public String header;
	public String footer;
	public String autowildcard;
	public String delivery_method;
	public String sms_fallback;
	
	public String toString()
	{
		return id+","+msgtypeid+","+description+","+timetolive+","+name+","+message+","+locked+","+createdonInsid+","+functionTag+","
	+wildcards+","+prefixid+","+pay_to+","+pmtid+","+keyphrasesbegin+","+header+","+footer+","+autowildcard+","+delivery_method+","+sms_fallback;
				
				
	}
	
	public String toSQL()
	{
		return id+","+autowildcard+","+delivery_method+",'"+description.trim().replace("'", "''")+"',"+footer+",'"+functionTag+"',"+header+","+keyphrasesbegin+","+locked+
				",'"+message+"','"+name.trim().replace("'", "''")+"','"+pay_to+"',"+pmtid+","+prefixid+","+"null"+","+sms_fallback+","+"null"+
				","+timetolive+","+wildcards+","+createdonInsid+","+msgtypeid;
				
	}	
}
