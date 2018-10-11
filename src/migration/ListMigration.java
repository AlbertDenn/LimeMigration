package migration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import org.springframework.core.io.PathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import Utils.ClientHttpFactory;
import Utils.Response;
import backgroundData.DistListData;

public class ListMigration {

	static String mda="http://localhost:9999/mda/";
	static String key="customer_phone_num";
	
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
	
	public static HashMap<String,String> distListMap(String file)
	{
		HashMap<String,String> mapDistList = new HashMap<>();
		
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/Lists/DistListMap"+file+".csv";
		
		String sCurrentLine;

		
				try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
					
					while ((sCurrentLine = br.readLine()) != null) {
						String[] shorts=sCurrentLine.split(",");
						mapDistList.put(shorts[0],shorts[1]);
						
					}
				
				} catch (IOException e) {
					e.printStackTrace();
				}

		return mapDistList;
		
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
	
	public static void migrateDistLists(Connection conn, ArrayList<DistListData> dlDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>instances=getInstanceMap(file);

		Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/Lists/DistListMap"+file+".csv"), "utf-8"));
		
	
			for(DistListData dlData : dlDatas)
		{
			String id = getNextVal(conn, "sq_distributionlist_id");
			dlData.insid=instances.get(dlData.insid);
			writer.write(dlData.id+","+id+":"+dlData.description+"\n");
			dlData.id=id;
				
			String sql="insert into distributionlist values ("+dlData.toSQL()+")";
			System.out.println(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		}
		writer.close();
		if(stmt!=null)
		stmt.close();
	
	}
	
	public static String getNewToken(String mda, String userJson){
		
		RestTemplate restTemplate = new RestTemplate(ClientHttpFactory.getClientHttpRequestFactory(50));
		HttpEntity<String> request = new HttpEntity<>(userJson);
		String token = null;

		String url = mda + "/login";
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.postForEntity(url, request, String.class);
		} catch (Exception e){
			return token;
		}

		if(response != null && !response.getHeaders().getValuesAsList("Authorization").isEmpty()){
			token = response.getHeaders().getValuesAsList("Authorization").get(0).split("Bearer")[1].trim();
		}

		return token;
	}

	public static String encodeB64URLSafe(String data) {
        return new String(Base64.getUrlEncoder().encode(data.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);
    }

	
	public static ResponseEntity<Response> getResponsePostingCSV(String mda, HttpMethod method,
			File csvFile,String workspaceId, String listName, String keyColumn, int mdaTimeout){

		RestTemplate restTemplate = new RestTemplate(ClientHttpFactory.getClientHttpRequestFactory(mdaTimeout));

		String token = getNewToken(mda, "{\"username\": \"root\",\"password\": \"tv0g4ng1\"}");

		System.out.println("Authorization token: " + token);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("Authorization", String.format("Bearer %s", token));

		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("csv_file", new PathResource(csvFile.getAbsolutePath()));
		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);

		System.out.println("listName:" + listName);
		System.out.println("keyColumn:" + keyColumn);

		String url = mda + "v1/lists/" + encodeB64URLSafe(listName) + "/" + workspaceId + "/"
				+ encodeB64URLSafe(keyColumn);
		System.out.println("mda call = " + url);

		return restTemplate.exchange(url, method, entity, Response.class);
	}
	
public static ArrayList<DistListData> getDistLists(String file){
		
		ArrayList<DistListData>dis = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/Lists/DistListData"+file+".csv";
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

    	    	  System.out.println(dd);
    	    	  dis.add(dd);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dis;
		
	}


	
	public static void migrate(Connection conn,String file,ArrayList<DistListData> dlDatas)
	{
		
		HashMap<String,String>instances=getInstanceMap(file);
		ArrayList<DistListData> distLists=getDistLists(file);
		File listFile;
		
		for(DistListData list: distLists)
		{
		
		try {	
		listFile = new File("/home/albert/Desktop/Migration/"+file+"/Lists/"+list.id+".csv");
		}catch(Exception e)
		{
			continue;
		}
		
		getResponsePostingCSV(mda, HttpMethod.POST,listFile,instances.get(list.insid), list.description, key, 50);
		
		}
		
		
	}
	
	
	 public static void main(String[] args) throws Exception {
		 
		 	String file="LIME";
		 	String listid="38280"	;
		 	String listname="testGiven";
		 	String insid="1";
			File listFile = new File("/home/albert/Desktop/Migration/"+file+"/Lists/"+listid+".csv");
			
			getResponsePostingCSV(mda, HttpMethod.POST,listFile,insid, listname, key, 50);
		  
	 }
	
	
	
}
