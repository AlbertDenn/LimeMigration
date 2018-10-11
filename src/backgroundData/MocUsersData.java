package backgroundData;

public class MocUsersData {

	public String login;
	public String password;
	public String mobilenumber;
	public String flastmodify;
	public String userlastmodify;
	public String iplastmodify;
	public String carrierid;
	public String applicationid;
	public String clientid;
	public String password_sha;
	public String email;
	
	
	public String toString() {
		return login+"|"+password+"|"+mobilenumber+"|"+flastmodify+"|"+userlastmodify+"|"+iplastmodify+"|"+carrierid+"|"+applicationid+"|"+clientid+"|"+password_sha;
	}
	
	public String toSQL() {
		
		email="";
		
		if(flastmodify.equals("null"))
			flastmodify=null;
		else
			flastmodify="'"+flastmodify+"'";
		
		return "'"+login+"',"+carrierid+",'"+email+"','"+iplastmodify+"',"+flastmodify+",'"+mobilenumber+"','"+password_sha+"','"+userlastmodify+"','"+applicationid+"',"+clientid;

	}
	
}
