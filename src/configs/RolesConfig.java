package configs;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import backgroundData.MocInstancexUserData;
import backgroundData.MocOperationxUserData;
import backgroundData.MocOurRolesData;
import backgroundData.MocOurRolesxOperationData;
import backgroundData.MocOurRolesxUserData;
import backgroundData.MocRolesxUserData;
import backgroundData.MocUsersData;
import backgroundData.Moc_ApplicationData;

public class RolesConfig {

	public static String encode(String toencode)
	{
		String encoded=Base64.getEncoder().encodeToString(toencode.getBytes());
		return encoded;
	}
	
	public static String decode(String todecode)
	{
		 return new String(Base64.getDecoder().decode(todecode.getBytes()));
	}
	
	public static void getMocInstancexUser(Connection conn, String file) throws SQLException, IOException
	{
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/MocInstancexUser"+file+".csv"), "utf-8"));
        String sql="";
        
      
          //QUERY
         	sql="select login,insid from moc_instancexuser";
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);
   			MocInstancexUserData mData = new MocInstancexUserData();

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){

      	    	  mData.login=rs.getString("login")!=null?rs.getString("login"):"null";
      	    	  mData.insid=rs.getString("insid")!=null?rs.getString("insid"):"null";
      	    	  
         
      	    	 //Display values
      	         System.out.println("Moc_instancexuser:"+mData);
      	         
      	         //Write values
      	         writer.write(mData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	               	     
        }
      	      rs.close();


        writer.close();

	}
	
	public static void getMocApplication(Connection conn, String file) throws SQLException, IOException
	{
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/MocApplication"+file+".csv"), "utf-8"));
        String sql="";
        
      
          //QUERY
         	sql="select id,description from moc_application";
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);
      	   Moc_ApplicationData mData = new Moc_ApplicationData();

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){

      	    	  mData.id=rs.getString("id")!=null?rs.getString("id"):"null";
      	    	  mData.description=rs.getString("description")!=null?rs.getString("description"):"null";
      	    	  
         
      	    	 //Display values
      	         System.out.println("Moc_application:"+mData);
      	         
      	         //Write values
      	         writer.write(mData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	               	     
        }
      	      rs.close();


        writer.close();

	}
	
	public static void getMocOurRolesxUser(Connection conn, String file) throws SQLException, IOException
	{
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/MocOurRolesxUser"+file+".csv"), "utf-8"));
        String sql="";
        
      
          //QUERY
         	sql="select login,rol from moc_our_rolesxuser";
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);
      	   MocOurRolesxUserData mData = new MocOurRolesxUserData();

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){

      	    	  mData.login=rs.getString("login")!=null?rs.getString("login"):"null";
      	    	  mData.rol=rs.getString("rol")!=null?rs.getString("rol"):"null";
      	    	  
         
      	    	 //Display values
      	         System.out.println("MocOurRolesxUser:"+mData);
      	         
      	         //Write values
      	         writer.write(mData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	               	     
        }
      	      rs.close();


        writer.close();

	}
	
	public static void getMocUsers(Connection conn, String file) throws SQLException, IOException
	{
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/MocUsers"+file+".csv"), "utf-8"));
        String sql="";
        
      
          //QUERY
         	sql="select login,password,mobilenumber,flastmodify,userlastmodify,iplastmodify,carrierid,applicationid,clientid,password_sha from moc_user";
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);
   			MocUsersData mData = new MocUsersData();

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){

      	    	  mData.login=rs.getString("login")!=null?rs.getString("login"):"null";
      	    	  mData.password=rs.getString("password")!=null?rs.getString("password"):"null";
      	    	  mData.mobilenumber=rs.getString("mobilenumber")!=null?rs.getString("mobilenumber"):"null";
      	    	  mData.flastmodify=rs.getString("flastmodify")!=null?rs.getString("flastmodify"):"null";
      	    	  mData.userlastmodify=rs.getString("userlastmodify")!=null?rs.getString("userlastmodify"):"null";
      	    	  mData.iplastmodify=rs.getString("iplastmodify")!=null?rs.getString("iplastmodify"):"null";
      	    	  mData.carrierid=rs.getString("carrierid")!=null?rs.getString("carrierid"):"null";
      	    	  mData.applicationid=rs.getString("applicationid")!=null?rs.getString("applicationid"):"null";
      	    	  mData.clientid=rs.getString("clientid")!=null?rs.getString("clientid"):"null";
      	    	  mData.password_sha=rs.getString("password_sha")!=null?rs.getString("password_sha"):"null";

      	    	 //Display values
      	         System.out.println("MocUsers:"+mData);
      	         
      	         //Write values
      	         writer.write(mData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	     
        }
      	      rs.close();


        writer.close();

	}
	
	public static void getMocOurRoles(Connection conn, String file) throws SQLException, IOException
	{
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/MocOurRoles"+file+".csv"), "utf-8"));
        String sql="";
        
      
          //QUERY
         	sql="select rol,description,level_op,messageid from moc_our_roles";
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);
   			MocOurRolesData mData = new MocOurRolesData();

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){

      	    	  mData.rol=rs.getString("rol")!=null?rs.getString("rol"):"null";
      	    	  mData.description=rs.getString("description")!=null?rs.getString("description"):"null";
      	    	  mData.level_op=rs.getString("level_op")!=null?rs.getString("level_op"):"null";
      	    	  mData.messageid=rs.getString("messageid")!=null?rs.getString("messageid"):"null";
      
      	    	 //Display values
      	         System.out.println("MocOurRoles:"+mData);
      	         
      	         //Write values
      	         writer.write(mData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	 
      	     
        }
      	      rs.close();


        writer.close();

	}
	
	public static void getMocRolesxUser(Connection conn, String file) throws SQLException, IOException
	{
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/MocRolesxUser"+file+".csv"), "utf-8"));
        String sql="";
        
      
          //QUERY
         	sql="select login,rolename from moc_rolexuser";
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);
      	   MocRolesxUserData mData = new MocRolesxUserData();

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){

      	    	  mData.login=rs.getString("login")!=null?rs.getString("login"):"null";
      	    	  mData.rolename=rs.getString("rolename")!=null?rs.getString("rolename"):"null";
      	    	  
         
      	    	 //Display values
      	         System.out.println("MocRolesxUser:"+mData);
      	         
      	         //Write values
      	         writer.write(mData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	               	     
        }
      	      rs.close();


        writer.close();

	}
	
	public static void getMocOperationxUser(Connection conn, String file) throws SQLException, IOException
	{
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/MocOperationxUser"+file+".csv"), "utf-8"));
        String sql="";
        
      
          //QUERY
         	sql="select login,opid from moc_operationxuser";
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);
      	   MocOperationxUserData mData = new MocOperationxUserData();

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){

      	    	  mData.login=rs.getString("login")!=null?rs.getString("login"):"null";
      	    	  mData.opid=rs.getString("opid")!=null?rs.getString("opid"):"null";
      	    	  
         
      	    	 //Display values
      	         System.out.println("MocRolesxUser:"+mData);
      	         
      	         //Write values
      	         writer.write(mData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	               	     
        }
      	      rs.close();


        writer.close();

	}
	
	public static void getMocOurRolesxOperation(Connection conn, String file) throws SQLException, IOException
	{
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/MocOurRolesxOperation"+file+".csv"), "utf-8"));
        String sql="";
        
      
          //QUERY
         	sql="select rol,opid from moc_our_rolesxoperation";
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);
      	   MocOurRolesxOperationData mData = new MocOurRolesxOperationData();

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){

      	    	  mData.rol=rs.getString("rol")!=null?rs.getString("rol"):"null";
      	    	  mData.opid=rs.getString("opid")!=null?rs.getString("opid"):"null";
      	    	  
         
      	    	 //Display values
      	         System.out.println("MocOurRolesxOperation:"+mData);
      	         
      	         //Write values
      	         writer.write(mData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	               	     
        }
      	      rs.close();


        writer.close();

	}
	
	
	
	public static void main(String[] args) throws SQLException, IOException {
		
		   // JDBC driver name and database URL
	     String DB_URL = "jdbc:oracle:thin:@//oracle-prod2.ogangi.com:1521/PROD2";

	   //  Database credentials CAW

 
	     String PASS = "ogangico";
	   
	     Connection conn=null;

			
//		     String USER = "caw_l12";
		     String USER = "lime";
			 
		     String file="LIME";
			
			
			try {
				conn = DriverManager.
				        getConnection(DB_URL,USER,PASS);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			getMocUsers(conn,file);
			getMocInstancexUser(conn,file);
			getMocOurRoles(conn,file);
			getMocOurRolesxUser(conn,file);
			getMocApplication(conn,file);
			getMocRolesxUser(conn,file);
			getMocOurRolesxOperation(conn,file);
			getMocOperationxUser(conn,file);
			
			
	}
	
}
