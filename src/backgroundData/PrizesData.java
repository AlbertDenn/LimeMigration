package backgroundData;

public class PrizesData {

	public String id;
	public String rafid;
	public String quantity;
	public String remaining;
	public String validfrom;
	public String validto;
	public String description;
	public String mt;
	public String wpid;
	public String dropbycycle;
	public String remainingbycycle;
	public String iscurrent;
	public String targetmargin;
	public String valueprize;
	public String everyn;
	public String type_prize;
	public String textafterwin;
	
	
	
	public String toString() {
		return id+","+rafid+","+quantity+","+remaining+","+validfrom+","+validto+","+description+","+mt+","+wpid+","+dropbycycle+","+
				remainingbycycle+","+iscurrent+","+targetmargin+","+valueprize+","+everyn+","+type_prize+","+textafterwin;
	}
	
	public String toSQL() {
		
		if(validfrom.equals("null"))
			validfrom=null;
		else
			validfrom="TIMESTAMP '"+validfrom+"'";
		
		if(validto.equals("null"))
			validto=null;
		else
			validto="TIMESTAMP '"+validto+"'";
		
		return id+",'"+description+"',"+dropbycycle+","+everyn+","+iscurrent+",'"+mt+"',"+null+","+quantity+","+remaining+","+remainingbycycle+","+
				targetmargin+","+validfrom+","+validto+","+valueprize+","+wpid+","+rafid;
	}
	
}
