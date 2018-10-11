package configs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;

public class WorkspaceConfigs {

	public static String encode(String toencode)
	{
		String encoded=Base64.getEncoder().encodeToString(toencode.getBytes());
		return encoded;
	}
	
	public static String decode(String todecode)
	{
		 return new String(Base64.getDecoder().decode(todecode.getBytes()));
	}
	   
	public static void getDBInstances(Connection conn,String file) throws SQLException, IOException
	{
		HashSet<String>set = new HashSet<>();
		
		Statement stmt = null;
        ResultSet rs= null;
        ResultSet rs2= null;

        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/Instances"+file+".csv"), "utf-8"));
        String sql="";
        String sql2="";
		
     	sql="select distinct insid from responselog where rspdate>'01-AUG-18'";
     	sql2="select distinct insid from broadcasts where begindate>'01-AUG-18'";
  		
  		//EXECUTE QUERY
  	     stmt = conn.createStatement();
  	     rs = stmt.executeQuery(sql);

  	   //EXTRACT DATA FROM RESULT SET
  	      while(rs.next()){
  	    	  set.add(rs.getString(1));
  	      }
  	      
  	     rs2 = stmt.executeQuery(sql2);
  	      while(rs2.next()){
	    	  set.add(rs2.getString(1));
	      }
 
  	      for(String s : set)
  	      {
  	  	      System.out.println(s);
  	    	  writer.write(s+"\n");
  	      }
		
//  	      rs.close();
  	      rs2.close();
  	      writer.close();
  	      
	}
	   
	   
	public static void getInfo(Connection conn, String file) throws SQLException, IOException
	{
        Statement stmt = null;
        ResultSet rs= null;
        ArrayList<String>instances = getInstances(file);

        
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/InstanceConf"+file+".csv"), "utf-8"));
        String sql="";
        
        for(String instance: instances)
        {
          //QUERY
         	sql="select instanceid, key, value from instance_conf where instanceid="+instance;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	 int instanceId  = rs.getInt("instanceid");
      	         String key = rs.getString("key");
      	         String value = rs.getString("value");

      	         if(key.equals("messangi_client_id"))
      	        	 key="access_public_key";
      	         
      	         if(key.equals("messangi_public_key"))
    	        	 key="access_private_key";
      	         
      	         
      	         //Display values
      	         System.out.print("INSTANCE ID: " + instanceId);
      	         System.out.print(", KEY: " + key);
      	         System.out.println(", VALUE: " + value);
      	         
      	         //Write values
      	         writer.write(instanceId+","+key+","+value+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
	}
	
	public static void getDBShortcodes(Connection conn, String file) throws SQLException, IOException
	{
        Statement stmt = null;
        ResultSet rs= null;
        
        ArrayList<String>instances = getInstances(file);

        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/Shortcodes"+file+".csv"), "utf-8"));
        String sql="";
        
        for(String instance: instances)
        {
          //QUERY
         	sql="select insid,shortcode from instancexsc where insid="+instance;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	 int instanceId  = rs.getInt("insid");
      	         String shortCode = rs.getString("shortcode");


      	         //Display values
      	         System.out.print("INSTANCE ID: " + instanceId);
      	         System.out.print(", Shortcode: " + shortCode+"\n");
      	         
      	         //Write values
      	         writer.write(instanceId+","+shortCode+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
	}
	
	public static void getValidMsgs(Connection conn, String file) throws SQLException, IOException
	{
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/validMsgs"+file+".csv"), "utf-8"));
        String sql="";
        
        ArrayList<String>instances = getInstances(file);

        
        for(String instance: instances)
        {
          //QUERY
         	sql="select id from msg where msgtypeid !=145 and createdoninsid="+instance;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	 String msgId = rs.getString("id");
      	         
      	    	 //Display values
      	         System.out.println("MSGID: " + msgId);
      	         
      	         //Write values
      	         writer.write(msgId+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
	}
	   
	public static ArrayList<String> getInstances(String file) {
		
		ArrayList<String> listInstances = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/Instances"+file+".csv";

				try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

					String sCurrentLine;

					while ((sCurrentLine = br.readLine()) != null) {
						System.out.println(sCurrentLine);
						listInstances.add(sCurrentLine);
						
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			
		return listInstances;
			
	}
	
	public static void getInstancesDes(Connection conn,String file) throws SQLException, IOException {
		
		Statement stmt = null;
        ResultSet rs= null;
        
        ArrayList<String>instances = getInstances(file);
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/InstancesDescription"+file+".csv"), "utf-8"));
        String sql="";
        
        for(String instance: instances)
        {
          //QUERY
         	sql="select id,description from instance where id="+instance;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	 String msgId = rs.getString("id");
      	    	 String description= rs.getString("description");
      	         
      	    	 //Display values
      	         System.out.println("INSTANCE:" + msgId+"   ShortCode:"+description);
      	         
      	         
      	         //Write values
      	         writer.write(msgId+":"+description+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
		
		
	}
	
//	public static void getAll(Connection conn, String file) throws SQLException, IOException
//	{
//		getDBInstances(conn,file);
//		getInstancesDes(conn,file);
//		getDBShortcodes(conn,file);
//		getInfo(conn,file);
//
//	}
	
	
	public static void main(String[] args) throws SQLException, IOException {

		   // JDBC driver name and database URL
		     String DB_URL = "jdbc:oracle:thin:@//oracle-prod2.ogangi.com:1521/PROD2";

		     String USER="";
		     String PASS="";
		
		Connection conn=null;

		String file="CAW";
		
	     if(file.equals("CAW")) {
			   //  Database credentials CAW
			     USER = "caw_l12";
			     PASS = "ogangico";
			     }else {
			   //  Database credentials LIME
			     USER = "lime";
			     PASS = "ogangico";
			     }
		
	     System.out.println(DB_URL+" "+USER+" "+PASS);
	     
		try {
			conn = DriverManager.
			        getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
//		getDBInstances(conn,file);
		getInstancesDes(conn,file);
		getDBShortcodes(conn,file);
		getInfo(conn,file);
		
		//ORDER IS:
		// getDBInstances
		// getInstancesDes
		// getDBShortcodes
		// getInfo
		
		
		
		
			
		
	}

}
