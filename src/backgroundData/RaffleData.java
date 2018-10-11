package backgroundData;

public class RaffleData {

	public String id;
	public String description;
	public String rtypeid;
	public String checkcodes;
	public String defaultprizeid;
	public String prizesraffleid;
	public String duration;
	public String codesraffleid;
	public String alreadywinnerrestriction;
	public String cycle;
	public String quantitymos;
	public String msgid;
	public String onecycleday;
	public String currentday;
	public String revenuetext;
	public String revenuesharepercent;
	public String mtafterlose;


	public String toString()
	{
		return id+","+description+","+rtypeid+","+checkcodes+","+defaultprizeid+","+prizesraffleid+","+duration+","+codesraffleid+","
	+alreadywinnerrestriction+","+cycle+","+quantitymos+","+msgid+","+onecycleday+","+currentday+","+revenuetext
	+","+revenuesharepercent+","+mtafterlose;
	}
	
	public String toSQL()
	{
		if(currentday.equals("null"))
			currentday=null;
		else
			currentday="TIMESTAMP '"+currentday+"'";
		
		
		
		return id+","+alreadywinnerrestriction+","+checkcodes+","+codesraffleid+","+currentday+","+cycle+","+defaultprizeid+",'"+description+"',"
	+duration+","+onecycleday+","+null+","+prizesraffleid+","+quantitymos+","+revenuesharepercent+","+revenuetext+","+msgid
	+","+rtypeid;
	}
	
	
}
