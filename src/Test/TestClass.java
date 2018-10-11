package Test;

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
import java.util.Collections;
import java.util.HashSet;

import backgroundData.DistListData;
import backgroundData.MsgData;

public class TestClass {

	public static String decode(String todecode)
	{
		 return new String(Base64.getDecoder().decode(todecode.getBytes()));
	}
	
public static ArrayList<MsgData> getMsgs(String file){
		
		ArrayList<MsgData>msgs = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/validMsgs"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");

				if(shorts.length<18)
					continue;
				else
				{
					
				  MsgData msg = new MsgData();

				  msg.id=shorts[0];
    	    	  msg.msgtypeid=shorts[1];
    	    	  msg.description=decode(shorts[2]).replaceAll("'", "''");
    	    	  msg.timetolive=shorts[3];
    	    	  msg.name=decode(shorts[4]).replaceAll("'", "''");
    	    	  msg.message=decode(shorts[5]).replaceAll("'", "''");
    	    	  msg.locked=shorts[6];
    	    	  msg.createdonInsid=shorts[7];
    	    	  msg.functionTag=shorts[8];
    	    	  msg.wildcards=shorts[9];
    	    	  msg.prefixid=shorts[10];
    	    	  msg.pay_to=shorts[11];
    	    	  msg.pmtid=shorts[12];
    	    	  msg.keyphrasesbegin=shorts[13];
    	    	  msg.header=shorts[14];
    	    	  msg.footer=shorts[15];
    	    	  msg.autowildcard=shorts[16];
    	    	  msg.delivery_method=shorts[17];
    	    	  msg.sms_fallback=shorts[18];
				
     	    	  msgs.add(msg);
				
			}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return msgs;
		
	}
	
	public static void main(String[] args) throws SQLException, IOException {
//
//		String DB_URL="jdbc:postgresql://localhost:9999/mimapp";
//	
//		String USER="mimapp";
//		String PASS="OTAxMGFkOG";
//		
//		
//		Connection conn=null;
//
//		try {
//			conn = DriverManager.
//			        getConnection(DB_URL,USER,PASS);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		String file="TEST";
//		
//		getDBInstances(conn,file);
////		getInstancesDes(conn,file);
////		getDBShortcodes(conn,file);
////		getInfo(conn,file);
//		
//		//ORDER IS:
//		// getDBInstances
//		// getInstancesDes
//		// getDBShortcodes
//		// getInfo
		
	
		ArrayList<MsgData> msgs=getMsgs("CAW");
		
		
		for(MsgData msg : msgs)
		{
			
			System.out.println(msg.id+" "+msg.name+" "+msg.message);
			
		}

		
		
		}
		
	}
