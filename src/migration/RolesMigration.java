package migration;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;


import backgroundData.MocInstancexUserData;
import backgroundData.MocOperationxUserData;
import backgroundData.MocOurRolesxUserData;
import backgroundData.MocRolesxUserData;
import backgroundData.MocUsersData;
import backgroundData.Moc_ApplicationData;
import backgroundData.MocOurRolesData;
import backgroundData.MocOurRolesxOperationData;

public class RolesMigration {
	
	public static String encode(String toencode)
	{
		String encoded=Base64.getEncoder().encodeToString(toencode.getBytes());
		return encoded;
	}
	
	public static String decode(String todecode)
	{
		 return new String(Base64.getDecoder().decode(todecode.getBytes()));
	}
	
	public static String getNextVal(Connection conn,String sequence) throws SQLException
	{
		Statement stmt = null;
        ResultSet rs= null;
		String sql ="select nextval('"+sequence+"')";
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		String val="";
		
		while (rs.next())
		{
			val=rs.getString(1);
		}
		
		return val;
		
	}
	
	
	public static HashMap<String,String>instancesMap(String file)
	{
		HashMap<String,String> mapInstances = new HashMap<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/InstanceMap"+file+".csv";

				try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

					String sCurrentLine;

					while ((sCurrentLine = br.readLine()) != null) {
						String[] shorts=sCurrentLine.split(",");
						mapInstances.put(shorts[0], shorts[1]);
												
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			
		return mapInstances;
		
	}

	public static HashMap<String,String> getInstanceMap(String file)
	{
		HashMap<String,String> mapInstances = new HashMap<>();
		
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/InstanceMap"+file+".csv";
		
		String sCurrentLine;

		
				try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
					
					while ((sCurrentLine = br.readLine()) != null) {
						String[] shorts=sCurrentLine.split(",");
						mapInstances.put(shorts[0],shorts[1]);
						
					}
				
				} catch (IOException e) {
					e.printStackTrace();
				}

		return mapInstances;
		
	}
	
public static ArrayList<MocInstancexUserData> getMocInstancexUser(String file){
		
		ArrayList<MocInstancexUserData>mdatas = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/MocInstancexUser"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				MocInstancexUserData mdata = new MocInstancexUserData();
				  mdata.login=shorts[0];
    	    	  mdata.insid=shorts[1];
   				
    	    	  mdatas.add(mdata);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mdatas;
		
	}

public static ArrayList<MocOperationxUserData> getMocOperationxUser(String file){
	
	ArrayList<MocOperationxUserData>mdatas = new ArrayList<>();
	
	String FILENAME = "/home/albert/Desktop/Migration/"+file+"/MocOperationxUser"+file+".csv";
	String sCurrentLine="";
	
	try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
		
		while ((sCurrentLine = br.readLine()) != null) {
			String[] shorts=sCurrentLine.split(",");
			MocOperationxUserData mdata = new MocOperationxUserData();
			  mdata.login=shorts[0];
	    	  mdata.opid=shorts[1];
				
	    	  mdatas.add(mdata);
			
		}
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return mdatas;
	
}

public static ArrayList<MocOurRolesxUserData> getMocOurRolesxUser(String file){
	
	ArrayList<MocOurRolesxUserData>mdatas = new ArrayList<>();
	
	String FILENAME = "/home/albert/Desktop/Migration/"+file+"/MocOurRolesxUser"+file+".csv";
	String sCurrentLine="";
	
	try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
		
		while ((sCurrentLine = br.readLine()) != null) {
			String[] shorts=sCurrentLine.split(",");
			MocOurRolesxUserData mdata = new MocOurRolesxUserData();
			  mdata.login=shorts[0];
	    	  mdata.rol=shorts[1];
	    	  		
	    	  mdatas.add(mdata);
			
		}
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return mdatas;
	
}

public static ArrayList<MocRolesxUserData> getMocRolesxUser(String file){
	
	ArrayList<MocRolesxUserData>mdatas = new ArrayList<>();
	
	String FILENAME = "/home/albert/Desktop/Migration/"+file+"/MocRolesxUser"+file+".csv";
	String sCurrentLine="";
	
	try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
		
		while ((sCurrentLine = br.readLine()) != null) {
			String[] shorts=sCurrentLine.split(",");
			MocRolesxUserData mdata = new MocRolesxUserData();
			  mdata.login=shorts[0];
	    	  mdata.rolename=shorts[1];
	    	  		
	    	  mdatas.add(mdata);
			
		}
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return mdatas;
	
}

public static ArrayList<MocOurRolesxOperationData> getMocOurRolesxOperation(String file){
	
	ArrayList<MocOurRolesxOperationData>mdatas = new ArrayList<>();
	
	String FILENAME = "/home/albert/Desktop/Migration/"+file+"/MocOurRolesxOperation"+file+".csv";
	String sCurrentLine="";
	
	try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
		
		while ((sCurrentLine = br.readLine()) != null) {
			String[] shorts=sCurrentLine.split(",");
			MocOurRolesxOperationData mdata = new MocOurRolesxOperationData();
			  mdata.opid=shorts[1];
	    	  mdata.rol=shorts[0];
	    	  		
	    	  mdatas.add(mdata);
			
		}
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return mdatas;
	
}

public static ArrayList<Moc_ApplicationData> getMocApplication(String file){
	
	ArrayList<Moc_ApplicationData>mdatas = new ArrayList<>();
	
	String FILENAME = "/home/albert/Desktop/Migration/"+file+"/MocApplication"+file+".csv";
	String sCurrentLine="";
	
	try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
		
		while ((sCurrentLine = br.readLine()) != null) {
			String[] shorts=sCurrentLine.split(",");
			Moc_ApplicationData mdata = new Moc_ApplicationData();
			  mdata.id=shorts[0];
	    	  mdata.description=shorts[1];
	    	  		
	    	  mdatas.add(mdata);
			
		}
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return mdatas;
	
}

public static ArrayList<MocOurRolesData> getMocOurRoles(String file){
	
	ArrayList<MocOurRolesData>mdatas = new ArrayList<>();
	
	String FILENAME = "/home/albert/Desktop/Migration/"+file+"/MocOurRoles"+file+".csv";
	String sCurrentLine="";
	
	try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
		
		while ((sCurrentLine = br.readLine()) != null) {
			String[] shorts=sCurrentLine.split("\\|");
			MocOurRolesData mdata = new MocOurRolesData();
			  mdata.rol=shorts[0];
	    	  mdata.description=shorts[1];
	    	  mdata.level_op=shorts[2];
	    	  mdata.messageid=shorts[3];

	    	  		
	    	  mdatas.add(mdata);
			
		}
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return mdatas;
	
}

public static ArrayList<MocUsersData> getMocUsers(String file){
	
	ArrayList<MocUsersData>mdatas = new ArrayList<>();
	
	String FILENAME = "/home/albert/Desktop/Migration/"+file+"/MocUsers"+file+".csv";
	String sCurrentLine="";
	
	try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
		
		while ((sCurrentLine = br.readLine()) != null) {
			String[] shorts=sCurrentLine.split("\\|");
			MocUsersData mdata = new MocUsersData();
			  mdata.login=shorts[0];
	    	  mdata.password=shorts[1];
	    	  mdata.mobilenumber=shorts[2];
	    	  mdata.flastmodify=shorts[3];
	    	  mdata.userlastmodify=shorts[4];
	    	  mdata.iplastmodify=shorts[5];
	    	  mdata.carrierid=shorts[6];
	    	  mdata.applicationid=shorts[7];
	    	  mdata.clientid=shorts[8];
	    	  mdata.password_sha=shorts[9];
	    	  		
	    	  
	    	  if(mdata.applicationid.equals("null"))
	    		  mdata.applicationid="dummy";
	    	  
	    	  mdatas.add(mdata);
			
		}
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return mdatas;
	
}

public static void migrateMocApplication(Connection conn, ArrayList<Moc_ApplicationData> mocUsers,String file) throws SQLException, IOException
{
	PreparedStatement stmt = null;
	
	for(Moc_ApplicationData mdata : mocUsers)
	{
		String sql="insert into moc_application values ("+mdata.toSQL()+")";
		System.out.println(sql);
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	try {
		stmt.close();
		}catch(Exception e)
		{
			//close
		}	
	

}

@SuppressWarnings("null")
public static void migrateMocUsers(Connection conn, ArrayList<MocUsersData> mocUsers,String file) throws SQLException, IOException
{
	PreparedStatement stmt = null;
	try {
	stmt.getConnection().prepareStatement("insert into moc_client values (1,'Ogangi')");
	stmt.execute();
	}catch(Exception e)
	{
		//ignore
	}
	
	for(MocUsersData mdata : mocUsers)
	{
		if(mdata.login.equals("acortez") ||mdata.login.equals("admin") || mdata.login.equals("superadmin") || mdata.login.equals("standard") || mdata.login.equals("premium") || mdata.login.equals("maker") || mdata.login.equals("checker"))
			continue;
		
		String sql="insert into moc_user values ("+mdata.toSQL()+")";
		System.out.println(sql);
		
		try {
		stmt = conn.prepareStatement(sql);
		stmt.execute();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	try {
		stmt.close();
		}catch(Exception e)
		{
			//close
		}	
	

}

public static void migrateMocOurRolesxUser(Connection conn, ArrayList<MocOurRolesxUserData> mocRolesxUser,String file) throws SQLException, IOException
{
	PreparedStatement stmt = null;
	
	for(MocOurRolesxUserData mdata : mocRolesxUser)
	{
		String sql="insert into moc_our_rolesxuser values ("+mdata.toSQL()+")";
		System.out.println(sql);
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	try {
		stmt.close();
		}catch(Exception e)
		{
			//close
		}}

public static void migrateMocOurRoles(Connection conn, ArrayList<MocOurRolesData> mocRoles,String file) throws SQLException, IOException
{
	PreparedStatement stmt = null;
	
	for(MocOurRolesData mdata : mocRoles)
	{
		String sql="insert into moc_our_roles values ("+mdata.toSQL()+")";
		System.out.println(sql);
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	try {
		stmt.close();
		}catch(Exception e)
		{
			//close
		}	
}

public static void migrateMocInstancexUser(Connection conn,ArrayList<MocInstancexUserData> mUsers,String file) throws SQLException
{
	PreparedStatement stmt = null;
	
	HashMap<String,String>mapInstances=instancesMap(file);
	
	for(MocInstancexUserData mdata : mUsers)
	{
		System.out.println("INSTANCE ID="+mdata.insid);
		mdata.insid=mapInstances.get(mdata.insid);
		if(mdata.insid==null)
			continue;
		
		
		String sql="insert into moc_instancexuser values ("+mdata.toSQL()+")";
		System.out.println(sql);
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	try {
	stmt.close();
	}catch(Exception e)
	{
		//close
	}
}

public static void migrateMocRolesxUser(Connection conn, ArrayList<MocRolesxUserData> mocRolesxUser,String file) throws SQLException, IOException
{
	PreparedStatement stmt = null;
	
	for(MocRolesxUserData mdata : mocRolesxUser)
	{
		String sql="insert into moc_rolexuser values ("+mdata.toSQL()+")";
		System.out.println(sql);
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	try {
		stmt.close();
		}catch(Exception e)
		{
			//close
		}}

public static void migrateMocOperationxUser(Connection conn, ArrayList<MocOperationxUserData> mocOperationxUser,String file) throws SQLException, IOException
{
	PreparedStatement stmt = null;
	
	for(MocOperationxUserData mdata : mocOperationxUser)
	{
		String sql="insert into moc_operationxuser values ("+mdata.toSQL()+")";
		System.out.println(sql);
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	try {
		stmt.close();
		}catch(Exception e)
		{
			//close
		}}

public static void migrateMocOurRolesxOperation(Connection conn, ArrayList<MocOurRolesxOperationData> mocOurRolesxOperation,String file) throws SQLException, IOException
{
	PreparedStatement stmt = null;
	
	for(MocOurRolesxOperationData mdata : mocOurRolesxOperation)
	{
		String sql="insert into moc_our_rolesxoperation values ("+mdata.toSQL()+")";
		System.out.println(sql);
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
	}
	try {
		stmt.close();
		}catch(Exception e)
		{
			//close
		}}

///////////////////////////////////////////////////////DELETERS//////////////////////////////////////////////////

public static void deleteMocOurRoles(Connection conn, ArrayList<MocOurRolesData> mocRoles,String file) throws SQLException, IOException
{
	PreparedStatement stmt = null;
	
	for(MocOurRolesData mdata : mocRoles)
	{
		if(mdata.rol.equals("root"))
				continue;
		
		String sql="delete from moc_our_roles where rol='"+mdata.rol+"'";
		System.out.println(sql);
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	try {
		stmt.close();
		}catch(Exception e)
		{
			//close
		}	
}

public static void deleteMocApplication(Connection conn, ArrayList<Moc_ApplicationData> mocUsers,String file) throws SQLException, IOException
{
	PreparedStatement stmt = null;
	
	for(Moc_ApplicationData mdata : mocUsers)
	{
		String sql="delete from moc_application where id='"+mdata.id+"'";
		System.out.println(sql);
		
		stmt = conn.prepareStatement(sql);
		stmt.execute();
		
	}
	try {
		stmt.close();
		}catch(Exception e)
		{
			//close
		}	
	

}

public static void deleteMocUsers(Connection conn, ArrayList<MocUsersData> mocUsers,String file) throws SQLException, IOException
{
	PreparedStatement stmt = null;
//	try {
//	stmt.getConnection().prepareStatement("insert into moc_client values (1,'Ogangi')");
//	stmt.execute();
//	}catch(Exception e)
//	{
//		//ignore
//	}
		String sql="delete from moc_user where login !='acortez' and login !='admin' and login!='superadmin' and login!='standard' and login!='premium' and login!='maker' and login!='checker'";
		System.out.println(sql);
		
		try {
		stmt = conn.prepareStatement(sql);
		stmt.execute();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try {
			stmt.close();
			}catch(Exception e)
			{
				//close
			}	
	

}

public static void deleteMocInstancexUser(Connection conn,ArrayList<MocInstancexUserData> mUsers,String file) throws SQLException
{
	PreparedStatement stmt = null;
	
	for(MocInstancexUserData mdata : mUsers)
	{
						
		String sql="delete from moc_instancexuser where login='"+mdata.login+"'";
		System.out.println(sql);
		
		stmt = conn.prepareStatement(sql);
		stmt.execute();
		
	}
	try {
		stmt.close();
		}catch(Exception e)
		{
			//close
		}}

public static void deleteMocOurRolesxUser(Connection conn, ArrayList<MocOurRolesxUserData> mocRolesxUser,String file) throws SQLException, IOException
{
	PreparedStatement stmt = null;
	
	for(MocOurRolesxUserData mdata : mocRolesxUser)
	{
		
		
		String sql="delete from moc_our_rolesxuser where login='"+mdata.login+"'";
		System.out.println(sql);
		
		stmt = conn.prepareStatement(sql);
		stmt.execute();
		
	}
	try {
		stmt.close();
		}catch(Exception e)
		{
			//close
		}}

public static void deleteMocRolesxUser(Connection conn, ArrayList<MocRolesxUserData> mocRolesxUserData,String file) throws SQLException, IOException
{
	PreparedStatement stmt = null;
	
	for(MocRolesxUserData mdata : mocRolesxUserData)
	{
		
		
		String sql="delete from moc_rolexuser where login='"+mdata.login+"'";
		System.out.println(sql);
		
		stmt = conn.prepareStatement(sql);
		stmt.execute();
		
	}
	try {
		stmt.close();
		}catch(Exception e)
		{
			//close
		}}

public static void main(String[] args) throws Exception {


	 // JDBC driver name and database URL
//	   String DB_URL = "jdbc:postgresql://staging2.ogangi.com:5432/development";

	   //  Database credentials
//	   String USER = "development";
//	   String PASS = "ogangico";
	
	
	   //FREAKING PROD!!!   ssh -L 9999:localhost:5432 flow.in.messangi.me

			String DB_URL="jdbc:postgresql://localhost:9999/mimapp";
			String USER="mimapp";
			String PASS="OTAxMGFkOG";
	   
//  String DB_URL="jdbc:postgresql://localhost:5432/mimapp";
//  String USER="mimapp";
//  String PASS="ogangico";
  
  
	   String file="LIME";
	   
	Connection conn=null;
	
	try {
		conn = DriverManager.
		        getConnection(DB_URL,USER,PASS);
	} catch (SQLException e) {
		System.out.println("COULD NOT CONNECT TO DB");
	}
	
	if(conn!=null)
	{

	ArrayList<MocUsersData> mocUsersData = getMocUsers(file);
	ArrayList<MocInstancexUserData> mocInstancexUser = getMocInstancexUser(file);
	ArrayList<MocOurRolesxUserData> mocOurRolesxUser = getMocOurRolesxUser(file);
	ArrayList<MocOurRolesData> mocOurRoles = getMocOurRoles(file);
	ArrayList<Moc_ApplicationData> mocApplicationData=getMocApplication(file);
	ArrayList<MocRolesxUserData> mocRolesxUser = getMocRolesxUser(file);
	ArrayList<MocOperationxUserData>mocOperationxUser=getMocOperationxUser(file);
	ArrayList<MocOurRolesxOperationData>mocOurRolesxOperation=getMocOurRolesxOperation(file);

	
	//	deleteMocInstancexUser(conn,mocInstancexUser,file);
//	deleteMocUsers(conn,mocUsersData,file);
//	deleteMocApplication(conn,mocApplicationData,file);
//	deleteMocOurRoles(conn,mocOurRoles,file);
//	deleteMocOurRolesxUser(conn,mocOurRolesxUser,file);
//	deleteMocRolesxUser(conn,mocRolesxUser,file);
	
	migrateMocApplication(conn,mocApplicationData,file);
	migrateMocUsers(conn,mocUsersData,file);
	migrateMocInstancexUser(conn,mocInstancexUser,file);
	migrateMocOurRoles(conn,mocOurRoles,file);
	migrateMocOurRolesxUser(conn,mocOurRolesxUser,file);
	migrateMocRolesxUser(conn,mocRolesxUser,file);
	migrateMocOperationxUser(conn,mocOperationxUser,file);
	migrateMocOurRolesxOperation(conn,mocOurRolesxOperation,file);

	}
	
}
}
	