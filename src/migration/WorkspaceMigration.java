package migration;
import backgroundData.WorkspaceData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import org.apache.commons.httpclientv2.HttpClient;
import org.apache.commons.httpclientv2.HttpException;
import org.apache.commons.httpclientv2.methods.GetMethod;


public class WorkspaceMigration {
   
		static boolean secondRound=false;
	
    	public static String encode(String toencode)
       {
		String encoded=Base64.getEncoder().encodeToString(toencode.getBytes());
		return encoded;
	   }
	
		public static String decode(String todecode)
	    {
		 return new String(Base64.getDecoder().decode(todecode.getBytes()));
	    }
	   
	   public static void reloadWorkspace(String baseURL,String id) throws HttpException, IOException
	   {
		   String finalUrl=baseURL+"rest/internal/reloadWorkspaces/"+id+"/0";
		   HttpClient client = new HttpClient();
		   GetMethod method = new GetMethod(finalUrl);
		   client.executeMethod(method);
		   
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
	   
	   public static HashMap<String, ArrayList<String>> getShortcodes(String file) {
			
			HashMap<String,ArrayList<String>> mapShortcodes = new HashMap<>();
			
			String FILENAME = "/home/albert/Desktop/Migration/"+file+"/Shortcodes"+file+".csv";

					try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

						String sCurrentLine;

						while ((sCurrentLine = br.readLine()) != null) {
							String[] shorts=sCurrentLine.split(",");
						
							if(mapShortcodes.get(shorts[0])==null)
							mapShortcodes.put(shorts[0], new ArrayList<String>());
							
							mapShortcodes.get(shorts[0]).add(shorts[1]);
													
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				
			return mapShortcodes;
					
		}
	   
	   public static HashMap<String, HashMap<String,String>> getParameters(String file) {
			
			HashMap<String,HashMap<String,String>> mapParameters = new HashMap<>();
			
			String FILENAME = "/home/albert/Desktop/Migration/"+file+"/InstanceConf"+file+".csv";

					try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

						String sCurrentLine;

						while ((sCurrentLine = br.readLine()) != null) {
							String[] shorts=sCurrentLine.split(",");
						
							if(mapParameters.get(shorts[0])==null)
							mapParameters.put(shorts[0], new HashMap<String,String>());
							
							mapParameters.get(shorts[0]).put(shorts[1],shorts[2]!=null?shorts[2]:"");
													
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				
			return mapParameters;
					
		}
	   
	   public static ArrayList<String> getInstances(String file) {
			
			ArrayList<String> listInstances = new ArrayList<>();
			
			String FILENAME = "/home/albert/Desktop/Migration/"+file+"/InstancesDescription"+file+".csv";

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
	   
	   public static void migrate(String file,Connection conn, ArrayList<String> instanceAndDescription,HashMap<String,ArrayList<String>>shortcodes,HashMap<String,HashMap<String,String>>parameters) throws Exception
	   {		

		   ArrayList<String>instances=new ArrayList<>();

		  
		   
		   Writer writer = new BufferedWriter(new OutputStreamWriter(
	                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/InstanceMap"+file+".csv"), "utf-8"));
		   
		   try {
		   for(String instanceanddes:instanceAndDescription)
		   {
			  
			   String[] id= instanceanddes.split(":");
			   String instance=id[0];
			   String description=id[1];
			   
			   if(secondRound)
			   {
			   if(instances.contains(instance))
				   continue;
			   }
			   
			   System.out.println("MIGRATING:"+instance+"-"+description);
			   System.out.print("Shortcodes:"+shortcodes.get(instance));
			   WorkspaceData ws = new WorkspaceData();
			   ws.setId(-1);
			   ws.setDescription(description);
			   
			   
			  for(String key : parameters.get(instance).keySet())
			  {
				  ws.overrideParameter(key, parameters.get(instance).get(key));
			  }
			   
			   ws.setShortcodes(shortcodes.get(instance));
			   WorkspaceData.save("acortez", conn, ws, "default", "default");
			   
			   
			   System.out.println("New WorkspaceID:"+ws.getId());
			   writer.write(instance+","+ws.getId()+"\n");
			   
			   reloadWorkspace("http://staging2.ogangi.com:9002/development/",String.valueOf(ws.getId()));
			  
		   }
		   }catch(Exception e)
		   { }
		   finally {
		   writer.close();
		   }
	   }
	   
	   public static void deleteAll(Connection conn,ArrayList<String>instances,String file) throws NumberFormatException, SQLException
	   {
		   CallableStatement cs = null;
			
		   HashMap<String,String>map=getInstanceMap(file);
		   String insid;
		   
			for(String instance : instances)
			{
				String split[]=instance.split(":");
				
				cs = conn.prepareCall("{call delete_instance(?)}");
				insid=map.get(split[0]);				
				System.out.println("DELETING... "+instance+" "+insid);
				cs.setInt(1, Integer.valueOf(insid));
				cs.addBatch();
			}
				cs.executeBatch();
				cs.close();
	   }
	   
	   
	   public static void main(String[] args) throws Exception {


			 // JDBC driver name and database URL
//			   String DB_URL = "jdbc:postgresql://staging2.ogangi.com:5432/development";

			   //  Database credentials
//			   String USER = "development";
//			   String PASS = "ogangico";
			   

		   
		   //FREAKING PROD!!!   ssh -L 9999:localhost:5432 flow.in.messangi.me

				String DB_URL="jdbc:postgresql://localhost:9999/mimapp";
				String USER="mimapp";
				String PASS="OTAxMGFkOG";

//   		   String DB_URL="jdbc:postgresql://localhost:5432/mimapp";
//		   String USER="mimapp";
//		   String PASS="ogangico";
		   
		   
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
//			HashMap<String,ArrayList<String>> shortcodes = getShortcodes(file);
//			HashMap<String,HashMap<String,String>> parameters=getParameters(file);
			ArrayList<String>instances = getInstances(file);
//			
//			migrate(file,conn,instances,shortcodes,parameters);
			
			
			deleteAll(conn,instances,file);

			}
			
				
			
		}
	
}
