package configs;
import backgroundData.AnswerData;
import backgroundData.DeliveryData;
import backgroundData.DistListData;
import backgroundData.KeyphraseData;
import backgroundData.ListDeltaData;
import backgroundData.MimInfoData;
import backgroundData.MsgData;
import backgroundData.OptionData;
import backgroundData.PrizesData;
import backgroundData.QuestionsData;
import backgroundData.RaffleData;
import backgroundData.ResponseData;
import backgroundData.Trivia_QuestionData;
import backgroundData.UserInfoData;
import backgroundData.WordsData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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

public class CampaignConfig {

	public static String encode(String toencode)
	{
		String encoded=Base64.getEncoder().encodeToString(toencode.getBytes());
		return encoded;
	}
	
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

	public static ArrayList<String> getMsg(String file) {
	
		ArrayList<String> validMsgs = new ArrayList<>();
	
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/validMsgs"+file+".csv";

			try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					String[]id=sCurrentLine.split(",");
					
					try {
						Integer.parseInt(id[0]);
						validMsgs.add(id[0]);
						System.out.println(id[0]);

					}catch(Exception ignore)
					{
						continue;
					}
					
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			return validMsgs;
		
	}
	
	
	public static ArrayList<String> getInstancesFromMSG(String file)
	{
		ArrayList<MsgData> msgs=getMsgs(file);
		
		HashSet<String> instancesFromMsgs = new HashSet<>();
		
		for(MsgData msg : msgs) {
			instancesFromMsgs.add(msg.createdonInsid);
			System.out.println(msg);
		}
		return new ArrayList<String>(instancesFromMsgs);
	}
	
	public static void getDeliveryData(Connection conn, String file) throws SQLException,IOException
	{
		DeliveryData dData = new DeliveryData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/deliveryData"+file+".csv"), "utf-8"));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select id,msgid,opensat,closesat from delivery where msgid="+msg;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	  dData.id=rs.getString("id")!=null?rs.getString("id"):"null";
      	    	  dData.msgid=rs.getString("msgid")!=null?rs.getString("msgid"):"null";
      	    	  dData.opensat=rs.getString("opensat")!=null?rs.getString("opensat"):"null";
      	    	  dData.closesat=rs.getString("closesat")!=null?rs.getString("closesat"):"null";
      	    	  
      	    	  //Display values
      	         System.out.println("DeliveryData:"+dData);
      	         
      	         //Write values
      	         writer.write(dData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
        	
	}
	
	public static void getQuestionsData(Connection conn, String file) throws SQLException,IOException
	{
		QuestionsData qData = new QuestionsData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/questionData"+file+".csv"), "utf-8"));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select msgid,question_number,text,prize from question where msgid="+msg;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	  qData.msgid=rs.getString("msgid")!=null?rs.getString("msgid"):"null";
      	    	  qData.question_number=rs.getString("question_number")!=null?rs.getString("question_number"):"null";
      	    	  qData.text=rs.getString("text")!=null?encode(rs.getString("text")):"null";
      	    	  qData.prize=rs.getString("prize")!=null?rs.getString("prize"):"null";
      	    	  
      	    	  //Display values
      	         System.out.println("QuestionData:"+qData);
      	         
      	         //Write values
      	         writer.write(qData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
       	
	}
	
	public static void getOptionsData(Connection conn, String file) throws SQLException,IOException
	{
		OptionData oData = new OptionData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/optionsData"+file+".csv"), "utf-8"));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select msgid,option_number,question_number,text,keyphrase,correct,to_question_number from options where msgid="+msg;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	  oData.msgid=rs.getString("msgid")!=null?rs.getString("msgid"):"null";
      	    	  oData.option_number=rs.getString("option_number")!=null?rs.getString("option_number"):"null";
      	    	  oData.question_number=rs.getString("question_number")!=null?rs.getString("question_number"):"null";
      	    	  oData.keyphrase=rs.getString("keyphrase")!=null?encode(rs.getString("keyphrase")):"null";
      	    	  oData.correct=rs.getString("correct")!=null?rs.getString("correct"):"null";
      	    	  oData.to_question_number=rs.getString("to_question_number")!=null?rs.getString("to_question_number"):"null";
      	    	  oData.text=rs.getString("text")!=null?encode(rs.getString("text")):"null";

      	    	  
      	    	  //Display values
      	         System.out.println("OptionData:"+oData);
      	         
      	         //Write values
      	         writer.write(oData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
        	
	}
	
	
	
	public static void getValidMsgs(Connection conn, String file) throws SQLException, IOException
	{
		MsgData msg = new MsgData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/validMsgs"+file+".csv"), "utf-8"));
        String sql="";
        
        ArrayList<String>instances = getInstances(file);
        
        for(String instance: instances)
        {
          //QUERY
//         	sql="select distinct id,msgtypeid,description,timetolive,name,message,locked,createdonInsid,functionTag,wildcards,prefixid,pay_to,pmtid,keyphrasesbegin,header,footer,autowildcard,delivery_method,sms_fallback from msg,on_air where msg.msgtypeid !=145 and msg.createdoninsid=on_air.insid and msg.createdoninsid="+instance;
        	sql="select distinct msg.id,msg.msgtypeid,msg.description,msg.timetolive,msg.name,msg.message,msg.locked,msg.createdonInsid,msg.functionTag,msg.wildcards,msg.prefixid,msg.pay_to,msg.pmtid,msg.keyphrasesbegin,msg.header,msg.footer,msg.autowildcard,msg.delivery_method,msg.sms_fallback from msg,on_air,responselog where msg.msgtypeid !=145 and responselog.msgid=msg.id and responselog.rspdate>'01-AUG-18' and msg.createdoninsid=on_air.insid and msg.createdoninsid="+instance;
        	//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	  msg.id=rs.getString("id")!=null?rs.getString("id"):"null";
      	    	  msg.msgtypeid=rs.getString("msgtypeid")!=null?rs.getString("msgtypeid"):"null";
      	    	  msg.description=rs.getString("description")!=null?encode(rs.getString("description")):"null";
      	    	  msg.timetolive=rs.getString("timetolive")!=null?rs.getString("timetolive"):"null";
      	    	  msg.name=rs.getString("name")!=null?encode(rs.getString("name")):"null";
      	    	  msg.message=rs.getString("message")!=null?encode(rs.getString("message")):"null";
      	    	  msg.locked=rs.getString("locked")!=null?rs.getString("locked"):"null";
      	    	  msg.createdonInsid=rs.getString("createdonInsid")!=null?rs.getString("createdonInsid"):"null";
      	    	  msg.functionTag=rs.getString("functionTag")!=null ?rs.getString("functionTag"):"null";
      	    	  msg.wildcards=rs.getString("wildcards")!=null?rs.getString("wildcards"):"null";
      	    	  msg.prefixid=rs.getString("prefixid")!=null?rs.getString("prefixid"):"null";
      	    	  msg.pay_to=rs.getString("pay_to")!=null?rs.getString("pay_to"):"null";
      	    	  msg.pmtid=rs.getString("pmtid")!=null?rs.getString("pmtid"):"null";
      	    	  msg.keyphrasesbegin=rs.getString("keyphrasesbegin")!=null?rs.getString("keyphrasesbegin"):"null";
      	    	  msg.header=rs.getString("header")!=null?rs.getString("header"):"null";
      	    	  msg.footer=rs.getString("footer")!=null?rs.getString("footer"):"null";
      	    	  msg.autowildcard=rs.getString("autowildcard")!=null?rs.getString("autowildcard"):"null";
      	    	  msg.delivery_method=rs.getString("delivery_method")!=null?rs.getString("delivery_method"):"null";
      	    	  msg.sms_fallback=rs.getString("sms_fallback")!=null?rs.getString("sms_fallback"):"null";
      	    	  
         
      	    	 //Display values
      	         System.out.println("MSG:"+msg);
      	         
      	         //Write values
      	         writer.write(msg.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();

	}
	
	public static void getWordsData(Connection conn, String file) throws SQLException,IOException
	{
		WordsData wData = new WordsData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/wordData"+file+".csv",true));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select msgid,id,text,complexity from words where msgid="+msg;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	  wData.msgid=rs.getString("msgid")!=null?rs.getString("msgid"):"null";
      	    	  wData.id=rs.getString("id")!=null?rs.getString("id"):"null";
      	    	  wData.text=rs.getString("text")!=null?encode(rs.getString("text")):"null";
      	    	  wData.complexity=rs.getString("complexity")!=null?rs.getString("complexity"):"null";
      	    	  
      	    	  //Display values
      	         System.out.println("WordsData:"+wData);
      	         
      	         //Write values
      	         writer.write(wData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
        	
	}
	
	public static void getTrivia_QuestionData(Connection conn, String file) throws SQLException,IOException
	{
		Trivia_QuestionData tqData = new Trivia_QuestionData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/TriviaQuestionData"+file+".csv",true));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select msgid,text,question_number,prize_on_correct,prize_on_incorrect from trivia_question where msgid="+msg;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	  tqData.msgid=rs.getString("msgid")!=null?rs.getString("msgid"):"null";
      	    	  tqData.question_number=rs.getString("question_number")!=null?rs.getString("question_number"):"null";
      	    	  tqData.text=rs.getString("text")!=null?encode(rs.getString("text")):"null";
      	    	  tqData.prize_on_correct=rs.getString("prize_on_correct")!=null?rs.getString("prize_on_correct"):"null";
      	    	  tqData.prize_on_incorrect=rs.getString("prize_on_incorrect")!=null?rs.getString("prize_on_incorrect"):"null";

      	    	  
      	    	  //Display values
      	         System.out.println("TriviaQuestionData:"+tqData);
      	         
      	         //Write values
      	         writer.write(tqData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
        	
	}
	
	public static void getMimInfoData(Connection conn, String file) throws SQLException,IOException
	{
		MimInfoData mData = new MimInfoData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/MimInfoData"+file+".csv",true));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select mimid,key,value from miminfo where mimid="+msg;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	  mData.mimId=rs.getString("mimid")!=null?rs.getString("mimid"):"null";
      	    	  mData.key=rs.getString("key")!=null?rs.getString("key"):"null";
      	    	  mData.value=rs.getString("value")!=null?rs.getString("value"):"null";
      	    	  
      	    	  //Display values
      	         System.out.println("MimInfo:"+mData);
      	         
      	         //Write values
      	         writer.write(mData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
        	
	}
	
	public static void getListDelta(Connection conn, String file) throws SQLException,IOException
	{
		ListDeltaData ldData = new ListDeltaData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/ListDeltaData"+file+".csv",true));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select id,msgid,listid,activate from listdelta where msgid="+msg;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	  ldData.id=rs.getString("id")!=null?rs.getString("id"):"null";
      	    	  ldData.msgid=rs.getString("msgid")!=null?rs.getString("msgid"):"null";
      	    	  ldData.listid=rs.getString("listid")!=null?rs.getString("listid"):"null";
      	    	  ldData.activate=rs.getString("activate")!=null?rs.getString("activate"):"null";
      	    	  
      	    	  //Display values
      	         System.out.println("ListDelta:"+ldData);
      	         
      	         //Write values
      	         writer.write(ldData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
        	
	}
	
	public static void getRaffleData(Connection conn, String file) throws SQLException,IOException
	{
		RaffleData rData = new RaffleData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/RaffleData"+file+".csv",true));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select id,description,rtypeid,checkcodes,defaultprizeid,prizesraffleid,duration,codesraffleid,alreadywinnerrestriction,cycle,quantitymos,msgid,onecycleday,currentday,revenuetext,revenuesharepercent,mtafterlose from raffles where msgid="+msg;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	  rData.id=rs.getString("id")!=null?rs.getString("id"):"null";
      	    	  rData.msgid=rs.getString("msgid")!=null?rs.getString("msgid"):"null";
      	    	  rData.description=rs.getString("description")!=null?rs.getString("description"):"null";
      	    	  rData.rtypeid=rs.getString("rtypeid")!=null?rs.getString("rtypeid"):"null";
      	    	  rData.checkcodes=rs.getString("checkcodes")!=null?rs.getString("checkcodes"):"null";
      	    	  rData.defaultprizeid=rs.getString("defaultprizeid")!=null?rs.getString("defaultprizeid"):"null";
      	    	  rData.prizesraffleid=rs.getString("prizesraffleid")!=null?rs.getString("prizesraffleid"):"null";
      	    	  rData.duration=rs.getString("duration")!=null?rs.getString("duration"):"null";
      	    	  rData.codesraffleid=rs.getString("codesraffleid")!=null?rs.getString("codesraffleid"):"null";
      	    	  rData.alreadywinnerrestriction=rs.getString("alreadywinnerrestriction")!=null?rs.getString("alreadywinnerrestriction"):"null";
      	    	  rData.cycle=rs.getString("cycle")!=null?rs.getString("cycle"):"null";
      	    	  rData.quantitymos=rs.getString("quantitymos")!=null?rs.getString("quantitymos"):"null";
      	    	  rData.onecycleday=rs.getString("onecycleday")!=null?rs.getString("onecycleday"):"null";
      	    	  rData.currentday=rs.getString("currentday")!=null?rs.getString("currentday"):"null";
      	    	  rData.revenuetext=rs.getString("revenuetext")!=null?rs.getString("revenuetext"):"null";
      	    	  rData.revenuesharepercent=rs.getString("revenuesharepercent")!=null?rs.getString("revenuesharepercent"):"null";
      	    	  rData.mtafterlose=rs.getString("mtafterlose")!=null?rs.getString("mtafterlose"):"null";

      	    	  //Display values
      	          System.out.println("RaffleData:"+rData);
      	         
      	          if(rData.rtypeid.equals("1"))
      	        	  rData.rtypeid="5";
      	          
      	         //Write values
      	          writer.write(rData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
        	
	}
	
	public static void getPrizesData(Connection conn, String file) throws SQLException,IOException
	{
		PrizesData pData = new PrizesData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/prizesData"+file+".csv",true));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select prizes.id,prizes.rafid,prizes.quantity,prizes.remaining,prizes.validfrom,prizes.validto,prizes.description,prizes.mt,prizes.wpid,prizes.dropbycycle,prizes.remainingbycycle,prizes.iscurrent,prizes.targetmargin,prizes.valueprize,prizes.everyn,prizes.type_prize,prizes.textafterwin from prizes join raffles on raffles.id = prizes.rafid where raffles.msgid="+msg;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	  pData.id=rs.getString("id")!=null?rs.getString("id"):"null";
      	    	  pData.rafid=rs.getString("rafid")!=null?rs.getString("rafid"):"null";
      	    	  pData.quantity=rs.getString("quantity")!=null?rs.getString("quantity"):"null";
      	    	  pData.remaining=rs.getString("remaining")!=null?rs.getString("remaining"):"null";
      	    	  pData.validfrom=rs.getString("validfrom")!=null?rs.getString("validfrom"):"null";
      	    	  pData.validto=rs.getString("validto")!=null?rs.getString("validto"):"null";
      	    	  pData.description=rs.getString("description")!=null?rs.getString("description"):"null";
      	    	  pData.mt=rs.getString("mt")!=null?encode(rs.getString("mt")):"null";
      	    	  pData.wpid=rs.getString("wpid")!=null?rs.getString("wpid"):"null";
      	    	  pData.dropbycycle=rs.getString("dropbycycle")!=null?rs.getString("dropbycycle"):"null";
      	    	  pData.remainingbycycle=rs.getString("remainingbycycle")!=null?rs.getString("remainingbycycle"):"null";
      	    	  pData.iscurrent=rs.getString("iscurrent")!=null?rs.getString("iscurrent"):"null";
      	    	  pData.targetmargin=rs.getString("targetmargin")!=null?rs.getString("targetmargin"):"null";
      	    	  pData.valueprize=rs.getString("valueprize")!=null?rs.getString("valueprize"):"null";
      	    	  pData.everyn=rs.getString("everyn")!=null?rs.getString("everyn"):"null";
      	    	  pData.type_prize=rs.getString("type_prize")!=null?rs.getString("type_prize"):"null";
      	    	  pData.textafterwin=rs.getString("textafterwin")!=null?rs.getString("textafterwin"):"null";
      	    	  
      	    	  //Display values
      	          System.out.println("PrizesData:"+pData);
      	         
      	         //Write values
      	          writer.write(pData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
        	
	}
	
	
	public static void getAnswerData(Connection conn, String file) throws SQLException,IOException
	{
		AnswerData adData = new AnswerData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/AnswersData"+file+".csv",true));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select ans_index,answerset_id,msgid,label from answer where msgid="+msg;
      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){
      	        
      	    	  adData.ans_index=rs.getString("ans_index")!=null?rs.getString("ans_index"):"null";
      	    	  adData.msgid=rs.getString("msgid")!=null?rs.getString("msgid"):"null";
      	    	  adData.answerset_id=rs.getString("answerset_id")!=null?rs.getString("answerset_id"):"null";
      	    	  adData.label=rs.getString("label")!=null?encode(rs.getString("label")):"null";
      	    	  
      	    	  //Display values
      	         System.out.println("AnswerData:"+adData);
      	         
      	         //Write values
      	         writer.write(adData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
        	
	}
	
	public static void getDistributionLists(Connection conn, String file) throws SQLException,IOException
	{
		DistListData dlData = new DistListData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/DistListData"+file+".csv",true));
        String sql="";
        
        ArrayList<String>instances = getInstancesFromMSG(file);
        
        for(String instance: instances)
        {
          //QUERY
         	sql="select id,createdby,default_mo,default_msgid,description,pmtid,insid from distributionlist where insid="+instance;
      		
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
        }

        writer.close();
        	
	}
	
	public static void getResponseData(Connection conn, String file) throws SQLException,IOException
	{
		ResponseData rdData = new ResponseData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/ResponseData"+file+".csv",true));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select ans_index,msgid,responseset_id,response from response where msgid="+msg;

      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){

      	    	  rdData.ans_index=rs.getString("ans_index")!=null?rs.getString("ans_index"):"null";
      	    	  rdData.msgid=rs.getString("msgid")!=null?rs.getString("msgid"):"null";
      	    	  rdData.responseset_id=rs.getString("responseset_id").trim()!=null?rs.getString("responseset_id"):"null";
      	    	  rdData.response=rs.getString("response")!=null?encode(rs.getString("response")):"null";
      	    	  
      	    	  
      	    	  //Display values
      	         System.out.println("ResponseData:"+rdData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n"));
      	         
      	         //Write values
      	         writer.write(rdData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
        	
	}
	
	public static void getKeyPhraseData(Connection conn, String file) throws SQLException,IOException
	{
		KeyphraseData kData = new KeyphraseData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/KeyphraseData"+file+".csv",true));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select id,ans_index,keyphrase,answerset_id,msgid from keyphrase where msgid="+msg;

      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){

      	    	  kData.ans_index=rs.getString("ans_index")!=null?rs.getString("ans_index"):"null";
      	    	  kData.msgid=rs.getString("msgid")!=null?rs.getString("msgid"):"null";
      	    	  kData.id=rs.getString("id").trim()!=null?rs.getString("id"):"null";
      	    	  kData.keyphrase=rs.getString("keyphrase")!=null?encode(rs.getString("keyphrase")):"null";
      	    	  kData.answerset_id=rs.getString("answerset_id")!=null?rs.getString("answerset_id").trim():"null";

      	    	  
      	    	  
      	    	  //Display values
      	         System.out.println("ResponseData:"+kData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n"));
      	         
      	         //Write values
      	         writer.write(kData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
        	
	}
	
	public static void getUserInfoData(Connection conn, String file) throws SQLException,IOException
	{
		UserInfoData uData = new UserInfoData();
		
        Statement stmt = null;
        ResultSet rs= null;
        
        Writer writer = new BufferedWriter(new FileWriter("/home/albert/Desktop/Migration/"+file+"/UserInfoData"+file+".csv",true));
        String sql="";
        
        ArrayList<String>msgs = getMsg(file);
        
        for(String msg: msgs)
        {
          //QUERY
         	sql="select key,mobilenumber,itemid,listid,value from userinfo where key like '"+msg+"|%'";

      		
      		//EXECUTE QUERY
      	     stmt = conn.createStatement();
      	     
      	     rs = stmt.executeQuery(sql);

      	   //EXTRACT DATA FROM RESULT SET
      	      while(rs.next()){

      	    	  uData.key=rs.getString("key")!=null?rs.getString("key"):"null";
      	    	  uData.mobilenumber=rs.getString("mobilenumber")!=null?rs.getString("mobilenumber"):"null";
      	    	  uData.itemid=rs.getString("itemid")!=null?rs.getString("itemid"):"null";
      	    	  uData.listid=rs.getString("listid")!=null?rs.getString("listid"):"null";
      	    	  uData.value=rs.getString("value")!=null?rs.getString("value").trim():"null";

      	    	  //Display values
      	         System.out.println("UserInfoData:"+uData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n"));
      	         
      	         //Write values
      	         writer.write(uData.toString().replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n")+"\n");
      	         
      	      }
      	      rs.close();
        }

        writer.close();
        	
	}
	
	public static void getAll(Connection conn, String file) throws SQLException, IOException
	{
	
		getValidMsgs(conn,file);
		getDeliveryData(conn,file);
		getQuestionsData(conn,file);
		getOptionsData(conn,file);
		getListDelta(conn,file);
		getAnswerData(conn,file);
		getResponseData(conn,file);
		getKeyPhraseData(conn,file);
		getDistributionLists(conn,file);
		getMimInfoData(conn,file);
		getUserInfoData(conn,file);
		getTrivia_QuestionData(conn,file);//LIME WONT USE IT!
		getRaffleData(conn,file);//LIME WONT USE IT!
		getPrizesData(conn,file);//LIME WONT USE IT!
		getWordsData(conn,file); //LIME WONT USE IT!

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
			
			getAll(conn,file);
			
//			getValidMsgs(conn,file);
//			getDeliveryData(conn,file);
//			getQuestionsData(conn,file);
//			getOptionsData(conn,file);
//			getTrivia_QuestionData(conn,file);
//			getListDelta(conn,file);
//			getAnswerData(conn,file);
//			getResponseData(conn,file);
//			getKeyPhraseData(conn,file);
//			getDistributionLists(conn,file);
//			getMimInfoData(conn,file);
//			getUserInfoData(conn,file);
//			getRaffleData(conn,file);//LIME WONT USE IT!
//			getPrizesData(conn,file);//LIME WONT USE IT!
//			getWordsData(conn,file); //LIME WONT USE IT!

	
	
	}
	
	
	
	
}
;