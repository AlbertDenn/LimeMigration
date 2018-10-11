package configs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import backgroundData.DistListData;
import backgroundData.MsgData;

public class ListConfig {

	static String key="customer_phone_num";
	
	
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
public static ArrayList<DistListData> getDistLists(String file){
		
		ArrayList<DistListData>dis = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/DistListData"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				DistListData dd = new DistListData();
				
				  dd.id=shorts[0];
    	    	  dd.createdby=shorts[1];
    	    	  dd.default_mo=shorts[2];
    	    	  dd.default_msgid=shorts[3];
    	    	  dd.description=shorts[4];
    	    	  dd.key=shorts[5];
    	    	  dd.pmtid=shorts[6];
    	    	  dd.insid=shorts[7];
    	    	  dd.lastupdate=shorts[8];

    	    	  dis.add(dd);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dis;
		
	}

public static void getDistributionLists(Connection conn, String file) throws SQLException,IOException
{
	DistListData dlData = new DistListData();
	
    Statement stmt = null;
    ResultSet rs= null;
    
    Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/DistListData"+file+".csv",true));
    String sql="";
    
    ArrayList<String>instances = getInstances(file);
    
//    for(String instance: instances)
//    {
      //QUERY
//     	sql="select id,createdby,default_mo,default_msgid,description,pmtid,insid from distributionlist where insid="+instance;
//  		sql="select distinct distributionlist.id,distributionlist.createdby,distributionlist.default_mo,distributionlist.default_msgid,distributionlist.description,distributionlist.pmtid,distributionlist.insid from distributionlist, broadcastxdlist,broadcasts where broadcasts.id=broadcastxdlist.BROADCASTID and broadcasts.begindate>'1-SEP-18' and broadcastxdlist.dlistid=distributionlist.id and broadcasts.insid="+instance;
  			sql="select distinct distributionlist.id,distributionlist.createdby,distributionlist.default_mo,distributionlist.default_msgid,distributionlist.description,distributionlist.pmtid,distributionlist.insid from distributionlist, broadcastxdlist,broadcasts where broadcasts.id=broadcastxdlist.BROADCASTID and broadcasts.begindate>'1-SEP-18' and broadcastxdlist.dlistid=distributionlist.id";
    	//EXECUTE QUERY
  	     stmt = conn.createStatement();
  	     
  	     rs = stmt.executeQuery(sql);

  	   //EXTRACT DATA FROM RESULT SET
  	      while(rs.next()){
  	        
  	    	  dlData.id=rs.getString("id")!=null?rs.getString("id"):"null";
  	    	  dlData.createdby=rs.getString("createdby")!=null?rs.getString("createdby"):"null";
  	    	  dlData.default_mo=rs.getString("default_mo")!=null?rs.getString("default_mo"):"null";
  	    	  dlData.description=rs.getString("description")!=null?rs.getString("description"):"null";
  	    	  dlData.pmtid=rs.getString("pmtid")!=null?rs.getString("pmtid"):"null";
  	    	  dlData.insid=rs.getString("insid")!=null?rs.getString("insid"):"null";

  	    	  //Display values
  	         System.out.println("DistListData:"+dlData);
  	         
  	         //Write values
  	         writer.write(dlData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
  	         
  	      }
  	      rs.close();
//    }

    writer.close();
    	
}

public static ArrayList<DistListData> getRemainingLists(String file){
	
	ArrayList<DistListData>dis = new ArrayList<>();
	
	String FILENAME = "/home/albert/Desktop/Migration/"+file+"/RemainingListData"+file+".csv";
	String sCurrentLine="";
	
	try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
		
		while ((sCurrentLine = br.readLine()) != null) {
			String[] shorts=sCurrentLine.split(",");
			DistListData dd = new DistListData();
			
			  dd.id=shorts[0];
	    	  dd.createdby=shorts[1];
	    	  dd.default_mo=shorts[2];
	    	  dd.default_msgid=shorts[3];
	    	  dd.description=shorts[4];
	    	  dd.key=shorts[5];
	    	  dd.pmtid=shorts[6];
	    	  dd.insid=shorts[7];
	    	  dd.lastupdate=shorts[8];

	    	  dis.add(dd);
			
		}
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return dis;
	
}

	public static String excentList(String file) {
		
		ArrayList<DistListData> distList= getDistLists(file);
		
		StringBuilder sb = new StringBuilder();
		String thislist="id!=";
		String initialsql="select id,createdby,default_mo,default_msgid,description,pmtid,insid from distributionlist where ";
		sb.append(initialsql);
		
		
		for(int i =0;i<distList.size();i++)
		{
			sb.append(thislist+distList.get(i).id);
			if(i!=distList.size()-1)
			sb.append(" AND ");
		}
			
		System.out.println(sb.toString());
		
		return sb.toString();
	}
	
	
	public static void getRemainingLists(Connection conn, String file) throws IOException, SQLException
	{
		DistListData dlData = new DistListData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/RemainingListsData"+file+".csv",true));
        String sql="";
        
            //QUERY
         	sql=excentList(file);
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	  dlData.id=rs.getString("id")!=null?rs.getString("id"):"null";
      	    	  dlData.createdby=rs.getString("createdby")!=null?rs.getString("createdby"):"null";
      	    	  dlData.default_mo=rs.getString("default_mo")!=null?rs.getString("default_mo"):"null";
      	    	  dlData.description=rs.getString("description")!=null?rs.getString("description"):"null";
      	    	  dlData.pmtid=rs.getString("pmtid")!=null?rs.getString("pmtid"):"null";
      	    	  dlData.insid=rs.getString("insid")!=null?rs.getString("insid"):"null";

      	    	  //Display values
      	         System.out.println("DistListData:"+dlData);
      	         
      	         //Write values
      	         writer.write(dlData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        

        writer.close();
	}
	
	public static void createSinglesCSV(Connection conn, String file) throws IOException, SQLException
	{
			Statement stmt = null;
	        ResultSet rs= null;
//	        ArrayList<DistListData> distLists=getDistLists(file);
	        ArrayList<DistListData> distLists=getRemainingLists(file);

	        
	        for(DistListData list : distLists)
	        {
	        	
	        File existFile = new File("/home/albert/Desktop/Migration/"+file+"/Lists/"+list.id+".csv");
	        if(existFile.exists())
	        	continue;
	        
	        
	        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/Lists/"+list.id+".csv",true));
	        
	        String sql="select mobilenumber from userxlistv2 where listid="+list.id;
	        System.out.println(sql);
	        stmt = conn.createStatement();
     	    rs = stmt.executeQuery(sql);
	        writer.write(key+",customer_optin_status\n");    
     	   while(rs.next()){
     	   System.out.println(rs.getString(1));
     	   writer.write(rs.getString(1)+",1\n");  
     	   }
     	   rs.close();
     	   	writer.close();
	        
	        }
	      
	        
	}
	public static ArrayList<String> getInstancesFromLists(String file)
	{
		ArrayList<DistListData> dls=getDistLists(file);
		
		HashSet<String> instancesFromMsgs = new HashSet<>();
		
		for(DistListData dl : dls) {
			instancesFromMsgs.add(dl.insid);
		}
		return new ArrayList<String>(instancesFromMsgs);
	}
	
	public static void printInstances(String file)
	{
		ArrayList<String> instances=getInstancesFromLists(file);
		
		for(String instance:instances)
			System.out.println(instance);
		
	}
	
	
	public static void main(String[] args) throws SQLException, IOException {
		
		   // JDBC driver name and database URL
	     String DB_URL = "jdbc:oracle:thin:@//oracle-prod2.ogangi.com:1521/PROD2";
	     String PASS = "ogangico";
	     String USER="";
	     
	     Connection conn=null;

	     
	     String file="CAW"; /////////////////////// STRING FILE ///////////////////////////////

			
	     if(file.equals("CAW"))
		     USER = "caw_l12";
	     else
		     USER = "CAW";

	     try {
				conn = DriverManager.
				        getConnection(DB_URL,USER,PASS);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	     printInstances(file);
	     
	     
	     
//	     getRemainingLists(conn,file); //CAW
//	     getDistributionLists(conn,file); //LIME
//	     createSinglesCSV(conn,file);

	
	
	}


	
	
}