package backgroundData;

import java.sql.Timestamp;

public class DistListData {

	public String id;
	public String createdby;
	public String default_mo;
	public String default_msgid;
	public String description;
	public String key;
	public String pmtid;
	public String insid;
	public String lastupdate;
	
	public String toString() {
		return id+","+createdby+","+default_mo+","+default_msgid+","+description+","+key+","+pmtid+","+insid+","+lastupdate;
	}
	
	public String toSQL() {
		
		lastupdate="TIMESTAMP '"+new Timestamp(System.currentTimeMillis())+"'" ;
		key=null;
			
		return id+",'"+createdby+"','"+default_mo+"',"+default_msgid+",'"+description.replace("'", "''")+"',"+key+","+lastupdate+",'"+pmtid+"',"+insid;
	}
	
	
}
