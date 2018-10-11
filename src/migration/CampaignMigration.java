package migration;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;

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

public class CampaignMigration {

	
	public static String encode(String toencode)
	{
		String encoded=Base64.getEncoder().encodeToString(toencode.getBytes());
		return encoded;
	}
	
	public static String decode(String todecode)
	{
		 return new String(Base64.getDecoder().decode(todecode.getBytes()));
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
	
	public static HashMap<String,String> distListMap(String file)
	{
		HashMap<String,String> mapDistList = new HashMap<>();
		
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/DistListMap"+file+".csv";
		
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
	
	public static HashMap<String,String> getRaffleMap(String file)
	{
		HashMap<String,String> mapRaffle = new HashMap<>();
		
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/RaffleMap"+file+".csv";
		
		String sCurrentLine;

		
				try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
					
					while ((sCurrentLine = br.readLine()) != null) {
						String[] shorts=sCurrentLine.split(",");
						mapRaffle.put(shorts[0],shorts[1]);
						
					}
				
				} catch (IOException e) {
					e.printStackTrace();
				}

		return mapRaffle;
		
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
	
	public static ArrayList<PrizesData> getPrizes(String file){
		
		ArrayList<PrizesData>pdatas = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/prizesData"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				PrizesData pdata = new PrizesData();
				  pdata.id=shorts[0];
    	    	  pdata.rafid=shorts[1];
    	    	  pdata.quantity=shorts[2];;
    	    	  pdata.remaining=shorts[3];;
    	    	  pdata.validfrom=shorts[4];
    	    	  pdata.validto=shorts[5];;
    	    	  pdata.description=shorts[6];;
    	    	  pdata.mt=decode(shorts[7]).replaceAll("'", "''");
    	    	  pdata.wpid=shorts[8];;
    	    	  pdata.dropbycycle=shorts[9];;
    	    	  pdata.remainingbycycle=shorts[10];;
    	    	  pdata.iscurrent=shorts[11];;
    	    	  pdata.targetmargin=shorts[12];;
    	    	  pdata.valueprize=shorts[13];;
    	    	  pdata.everyn=shorts[14];;
    	    	  pdata.type_prize=shorts[15];;
    	    	  pdata.textafterwin=shorts[16];;
				
    	    	  pdatas.add(pdata);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pdatas;
		
	}
	
	public static ArrayList<RaffleData> getRaffles(String file){
		
		ArrayList<RaffleData>rdatas = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/RaffleData"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				RaffleData rdata = new RaffleData();
				  rdata.id=shorts[0];
    	    	  rdata.description=shorts[1];
    	    	  rdata.rtypeid=shorts[2];;
    	    	  rdata.checkcodes=shorts[3];;
    	    	  rdata.defaultprizeid=shorts[4];
    	    	  rdata.prizesraffleid=shorts[5];;
    	    	  rdata.duration=shorts[6];;
    	    	  rdata.codesraffleid=shorts[7];;
    	    	  rdata.alreadywinnerrestriction=shorts[8];;
    	    	  rdata.cycle=shorts[9];;
    	    	  rdata.quantitymos=shorts[10];;
    	    	  rdata.msgid=shorts[11];;
    	    	  rdata.onecycleday=shorts[12];;
    	    	  rdata.currentday=shorts[13];;
    	    	  rdata.revenuetext=shorts[14];;
    	    	  rdata.revenuesharepercent=shorts[15];;
    	    	  rdata.mtafterlose=shorts[16];;
				
    	    	  rdatas.add(rdata);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rdatas;
		
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

    	    	  System.out.println(dd);
    	    	  dis.add(dd);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dis;
		
	}
	
	public static ArrayList<OptionData> getOptions(String file){
		
		ArrayList<OptionData>ops = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/optionsData"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				OptionData op = new OptionData();
				
				  op.msgid=shorts[0];
    	    	  op.option_number=shorts[1];
    	    	  op.question_number=shorts[2];
    	    	  op.text=decode(shorts[3]).replaceAll("'", "''");
    	    	  op.keyphrase=decode(shorts[4]).replaceAll("'", "''");
    	    	  op.correct=shorts[5];
    	    	  op.to_question_number=shorts[6];
    	    	  		
    	    	  ops.add(op);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ops;
		
	}
	
public static ArrayList<KeyphraseData> getKeyphrase(String file){
		
		ArrayList<KeyphraseData>kds = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/KeyphraseData"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				KeyphraseData kd = new KeyphraseData();
				
				  kd.id=shorts[0];
    	    	  kd.ans_index=shorts[1];
    	    	  kd.keyphrase=decode(shorts[2]);
    	    	  kd.answerset_id=shorts[3];
    	    	  kd.msgid=shorts[4];
        	    	  		
    	    	  kds.add(kd);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return kds;
		
	}

	
	public static ArrayList<QuestionsData> getQuestions(String file){
		
		ArrayList<QuestionsData>qds = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/questionData"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				QuestionsData qd = new QuestionsData();
				
				  qd.msgid=shorts[0];
    	    	  qd.question_number=shorts[1];
    	    	  qd.text=decode(shorts[2]).replaceAll("'", "''");
    	    	  qd.prize=shorts[3];
    	    	  		
    	    	  qds.add(qd);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return qds;
		
	}
	
public static ArrayList<AnswerData> getAnswers(String file){
		
		ArrayList<AnswerData>ads = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/AnswersData"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				AnswerData ad = new AnswerData();
				
				  ad.ans_index=shorts[0];
    	    	  ad.answerset_id=shorts[1];
    	    	  ad.msgid=shorts[2];
    	    	  ad.label=decode(shorts[3]).replaceAll("'", "''");
    	    	  		
    	    	  ads.add(ad);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ads;
		
	}

public static ArrayList<ResponseData> getResponse(String file){
	
	ArrayList<ResponseData>ads = new ArrayList<>();
	
	String FILENAME = "/home/albert/Desktop/Migration/"+file+"/ResponseData"+file+".csv";
	String sCurrentLine="";
	
	try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
		
		while ((sCurrentLine = br.readLine()) != null) {
			String[] shorts=sCurrentLine.split(",");
			ResponseData ad = new ResponseData();
			System.out.println(sCurrentLine);
			  ad.ans_index=shorts[0];
	    	  ad.responseset_id=shorts[2];
	    	  ad.msgid=shorts[1];
	    	  ad.response=decode(shorts[3]).replaceAll("'", "''");
	    	  		
	    	  ads.add(ad);
			
		}
	
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return ads;
	
}
	
	public static ArrayList<DeliveryData> getDeliveries(String file)
	{
		
		ArrayList<DeliveryData> dDatas = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/deliveryData"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				DeliveryData dData = new DeliveryData();
				
				  dData.msgid=shorts[1];
    	    	  dData.id=shorts[0];
    	    	  dData.opensat=shorts[2];
    	    	  dData.closesat=shorts[3];
				
    	    	  dDatas.add(dData);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dDatas;
		
		
	}
	
	public static ArrayList<ListDeltaData> getListDeltas(String file)
	{
		
		ArrayList<ListDeltaData> ldDatas = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/ListDeltaData"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				ListDeltaData ldData = new ListDeltaData();
				
				  ldData.msgid=shorts[1];
    	    	  ldData.id=shorts[0];
    	    	  ldData.listid=shorts[2];
    	    	  ldData.activate=shorts[3];
				
    	    	  ldDatas.add(ldData);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ldDatas;
		
		
	}
	
	
	public static ArrayList<UserInfoData> getUserInfos(String file)
	{
		
		ArrayList<UserInfoData> uiDatas = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/UserInfoData"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				UserInfoData uiData = new UserInfoData();
				
				  uiData.key=shorts[0];
    	    	  uiData.mobilenumber=shorts[1];
    	    	  uiData.itemid=shorts[2];
    	    	  uiData.listid=shorts[3];
    	    	  uiData.value=shorts[4];

				
    	    	  uiDatas.add(uiData);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return uiDatas;
		
		
	}
	
	public static ArrayList<MimInfoData> getMimInfos(String file)
	{
		
		ArrayList<MimInfoData> mimDatas = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/MimInfoData"+file+".csv";
		
		HashMap<String,String> raffleMap= getRaffleMap(file);
		
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				MimInfoData mimData = new MimInfoData();
				
				  mimData.mimId=shorts[0];
    	    	  mimData.key=shorts[1];
    	    	  mimData.value=shorts[2];
    	    	  
    	    	  
    	    	  if(mimData.key.equals("RAFFLE_ID") || mimData.key.equals("RAFFLEID") )
    	    	  {
        	    	  System.out.println("PREVIOUSLY RAFFLE ID CHANGE MIM:"+mimData.toString());
    	    		  mimData.value=raffleMap.get(mimData.value);
        	    	  System.out.println("AFTER RAFFLE ID CHANGE MIM:"+mimData.toString());

    	    	  }
			   	  mimDatas.add(mimData);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mimDatas;
		
		
	}
	
	public static ArrayList<WordsData> getWords(String file){
		
		ArrayList<WordsData>words = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/wordData"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				WordsData wData = new WordsData();
				
				  wData.msgid=shorts[2];
    	    	  wData.id=shorts[0];
    	    	  wData.text=decode(shorts[1]).replaceAll("'", "''");
    	    	  wData.complexity=shorts[3];
				
				
    	    	  words.add(wData);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return words;
	}
	
	
	public static ArrayList<Trivia_QuestionData> getTQs(String file){
		
		ArrayList<Trivia_QuestionData>tqs = new ArrayList<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/TriviaQuestionData"+file+".csv";
		String sCurrentLine="";
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			
			while ((sCurrentLine = br.readLine()) != null) {
				String[] shorts=sCurrentLine.split(",");
				Trivia_QuestionData tqData = new Trivia_QuestionData();
				  tqData.msgid=shorts[0];
    	    	  tqData.question_number=shorts[1];
    	    	  tqData.text=decode(shorts[2]).replaceAll("'", "''");
    	    	  tqData.prize_on_correct=shorts[3];
    	    	  tqData.prize_on_incorrect=shorts[4];
				
    	    	  tqs.add(tqData);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tqs;
		
		
		
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
	
	public static HashMap<String,String>msgMap(String file)
	{
		HashMap<String,String> mapMsg = new HashMap<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/MsgMap"+file+".csv";

				try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

					String sCurrentLine;

					while ((sCurrentLine = br.readLine()) != null) {
						String[] shorts=sCurrentLine.split(",");
						mapMsg.put(shorts[0], shorts[1]);
												
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			
		return mapMsg;
		
	}
	
	public static HashMap<String,String>raffleMap(String file)
	{
		HashMap<String,String> mapRaffle = new HashMap<>();
		
		String FILENAME = "/home/albert/Desktop/Migration/"+file+"/RaffleMap"+file+".csv";

				try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

					String sCurrentLine;

					while ((sCurrentLine = br.readLine()) != null) {
						String[] shorts=sCurrentLine.split(",");
						mapRaffle.put(shorts[0], shorts[1]);
												
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			
		return mapRaffle;
		
	}
	
	
	public static void migrateMsg(Connection conn, ArrayList<MsgData> msgs,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>instance=instancesMap(file);
		Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/MsgMap"+file+".csv"), "utf-8"));
	   
		for(MsgData msg : msgs)
		{
			String id = getNextVal(conn, "sq_msg_id");
			writer.write(msg.id+","+id+"\n");
			msg.id=id;
			msg.createdonInsid=instance.get(msg.createdonInsid);

			
			
			String sql="insert into msg values ("+msg.toSQL()+")";
			System.out.println(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		}
		stmt.close();
		writer.close();
		
		
	
	}
	
	public static void migrateDelivery(Connection conn, ArrayList<DeliveryData> dDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>msgMap=msgMap(file);
		
		
			for(DeliveryData dData : dDatas)
		{
			
			dData.msgid=msgMap.get(dData.msgid);
			
			String sql="insert into delivery values ("+dData.toSQL()+")";
			System.out.println(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		}
			
		stmt.close();
	
	}
	
	public static void migrateOptions(Connection conn, ArrayList<OptionData> oDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>msgMap=msgMap(file);
		
		
			for(OptionData oData : oDatas)
		{
			
			oData.msgid=msgMap.get(oData.msgid);
			
			String sql="insert into options values ("+oData.toSQL()+")";
			System.out.println(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		}
		
		if(stmt!=null)
		stmt.close();
	
	}
	
	public static void migrateQuestions(Connection conn, ArrayList<QuestionsData> qDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>msgMap=msgMap(file);
		
		
			for(QuestionsData qData : qDatas)
		{
			
			qData.msgid=msgMap.get(qData.msgid);
			
			String sql="insert into question values ("+qData.toSQL()+")";
			System.out.println(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		}
		
		if(stmt!=null)
		stmt.close();
	
	}
	
	public static void migrateAnswers(Connection conn, ArrayList<AnswerData> aDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;

			
		HashMap<String,String>msgMap=msgMap(file);
		
		
			for(AnswerData aData : aDatas)
			{
			
			aData.msgid=msgMap.get(aData.msgid);
			
			String sql2="insert into answerset values ("+aData.ans_index+","+aData.msgid+","+null+","+null+")";
			System.out.println(sql2);

			
				
			stmt=conn.prepareStatement(sql2);
			stmt.execute();
			
			}
			
			if(stmt!=null)
				stmt.close();
			
			for(AnswerData aData : aDatas)
			{
				
				
				String sql="insert into answer values ("+aData.toSQL()+")";
				System.out.println(sql);
				stmt=conn.prepareStatement(sql);
				stmt.execute();
						
			}
		
		if(stmt!=null)
		stmt.close();

	
	}
	
	public static void migrateResponse(Connection conn, ArrayList<ResponseData> rDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;

			
		HashMap<String,String>msgMap=msgMap(file);
		
		
			for(ResponseData rData : rDatas)
			{
			
			rData.msgid=msgMap.get(rData.msgid);
			
			String sql="insert into responseset values ("+rData.ans_index+","+rData.msgid+","+null+","+null+")";
			System.out.println(sql);
			stmt=conn.prepareStatement(sql);
			stmt.execute();
			
			}
			if(stmt!=null)
				stmt.close();
			
			for(ResponseData rData : rDatas)
			{
				
				String sql="insert into response values ("+rData.toSQL()+")";
				System.out.println(sql);
				stmt=conn.prepareStatement(sql);
				stmt.execute();
				
			}
		
		if(stmt!=null)
		stmt.close();

	
	}
	
	public static void migrateTrivia(Connection conn, ArrayList<Trivia_QuestionData> tqDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>msgMap=msgMap(file);
		
		
			for(Trivia_QuestionData tqData : tqDatas)
		{
			
			System.out.println(tqData.msgid);

			tqData.msgid=msgMap.get(tqData.msgid);
			
			String sql="insert into trivia_question values ("+tqData.toSQL()+")";
			System.out.println(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		}
		
		if(stmt!=null)
		stmt.close();
	
	}
	
	public static void migrateWords(Connection conn, ArrayList<WordsData> wDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>msgMap=msgMap(file);
		
		
			for(WordsData wData : wDatas)
		{
			
			wData.msgid=msgMap.get(wData.msgid);
			
			String sql="insert into words values ("+wData.toSQL()+")";
			System.out.println(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		}
		
		if(stmt!=null)
		stmt.close();
	
	}
	
	public static void migrateRaffle(Connection conn, ArrayList<RaffleData> rDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>msgMap=msgMap(file);
		Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/RaffleMap"+file+".csv"), "utf-8"));
	   
		
			for(RaffleData rData : rDatas)
		{
			String rafid = getNextVal(conn, "sq_raffles_id");
			writer.write(rData.id+","+rafid+"\n");
			rData.msgid=msgMap.get(rData.msgid);
			rData.id=rafid;
			
			String sql="insert into raffles values ("+rData.toSQL()+")";
			System.out.println(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		}
			writer.close();

		if(stmt!=null)
		stmt.close();
		
	
	}
	
	public static void migratePrizes(Connection conn, ArrayList<PrizesData> pDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>raffleMap=raffleMap(file);
		
		
			for(PrizesData pData : pDatas)
		{
			String prizefid = getNextVal(conn, "sq_prizes_id");
			pData.rafid=raffleMap.get(pData.rafid);
			pData.id=prizefid;
			
			String sql="insert into prizes values ("+pData.toSQL()+")";
			System.out.println(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		}
		
		if(stmt!=null)
		stmt.close();
	
	}
	
	public static void migrateKeyphrase(Connection conn, ArrayList<KeyphraseData> kDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
		HashMap<String,String>msgMap=msgMap(file);

		
			for(KeyphraseData kData : kDatas)
		{
			String keyphraseId = getNextVal(conn, "sq_keyphrase_id");
			
			kData.id=keyphraseId;
			kData.msgid=msgMap.get(kData.msgid);
			
			String sql="insert into keyphrase values ("+kData.toSQL()+")";
			System.out.println(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		}
		
		if(stmt!=null)
		stmt.close();
	
	}
	
	public static void migrateMimInfos(Connection conn, ArrayList<MimInfoData> mDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>msgMap=msgMap(file);

	
			for(MimInfoData mData : mDatas)
		{
			
			mData.mimId=msgMap.get(mData.mimId);
				
			String sql="insert into miminfo values ("+mData.toSQL()+")";
			System.out.println(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		}
		
		if(stmt!=null)
		stmt.close();
	
	}
	
	public static void migrateDistLists(Connection conn, ArrayList<DistListData> dlDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>instances=instancesMap(file);

		Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("/home/albert/Desktop/Migration/"+file+"/DistListMap"+file+".csv"), "utf-8"));
		
	
			for(DistListData dlData : dlDatas)
		{
			String id = getNextVal(conn, "sq_distributionlist_id");
			dlData.insid=instances.get(dlData.insid);
			writer.write(dlData.id+","+id+"\n");
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
	
	public static void migrateListDeltas(Connection conn, ArrayList<ListDeltaData> ldDatas,String file) throws SQLException, IOException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>msgMap=msgMap(file);
		HashMap<String,String>distListMap=distListMap(file);

	
			for(ListDeltaData ldData : ldDatas)
		{
			String id = getNextVal(conn, "sq_listdelta_id");
			ldData.id=id;
			ldData.msgid=msgMap.get(ldData.msgid);
			
			ldData.listid=distListMap.get(ldData.listid);
				
			String sql="insert into listdelta values ("+ldData.toSQL()+")";
			System.out.println(sql);
			
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		}
		
		if(stmt!=null)
		stmt.close();
	
	}
	
	public static void migrateUserInfos(Connection conn, ArrayList<UserInfoData> uiDatas,String file) throws SQLException, IOException, InterruptedException
	{
		PreparedStatement stmt = null;
			
		HashMap<String,String>distListMap=distListMap(file);
		HashMap<String,String>msgMap=msgMap(file);

		String newKey="";
		String split[];
		
			for(UserInfoData uiData : uiDatas)
		{
				
			uiData.listid=distListMap.get(uiData.listid);
			split=uiData.key.split("\\|");
			newKey=msgMap.get(split[0]);
			newKey=newKey+"|"+split[1];
			uiData.key=newKey;
			
			String sql="insert into userv2 values ("+uiData.mobilenumber+","+uiData.mobilenumber+",7)";
			String sql2="insert into userinfo values ("+uiData.toSQL()+")";

			System.out.println(sql);

			try {
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			}catch(Exception e) {
			continue;
			}
			Thread.sleep(5);
			System.out.println(sql2);
			stmt = conn.prepareStatement(sql2);
			stmt.execute();
			
		}
		
		if(stmt!=null)
		stmt.close();
	
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
	
	
	public static void deleteAll(ArrayList<MsgData> msgData,Connection conn,String file) throws SQLException
	{
		CallableStatement cs = null;
		HashMap<String,String>msgMap=msgMap(file);

		
		for(MsgData msg : msgData)
		{
			msg.id=msgMap.get(msg.id);
			System.out.println("DELETING MSG ID:"+msg.id);
			cs = conn.prepareCall("{call delete_msg(?)}");
			cs.setInt(1, Integer.valueOf(msg.id));
			cs.addBatch();
		}
			cs.executeBatch();
			cs.close();
		
		
	}
	
	
	 public static void main(String[] args) throws Exception {


		 // JDBC driver name and database URL
//		   String DB_URL = "jdbc:postgresql://staging2.ogangi.com:5432/development";

		   //  Database credentials
//		   String USER = "development";
//		   String PASS = "ogangico";
		   
	   String DB_URL="jdbc:postgresql://localhost:5432/mimapp";
	   String USER="mimapp";
	   String PASS="ogangico";
	   
	   
		   String file="CAW";
		   
		Connection conn=null;
		
		try {
			conn = DriverManager.
			        getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			System.out.println("COULD NOT CONNECT TO DB");
		}
		
		if(conn!=null)
		{

		ArrayList<MsgData> msgData = getMsgs(file);
		ArrayList<DeliveryData> deliveryData = getDeliveries(file);
		ArrayList<OptionData> optionData = getOptions(file);
		ArrayList<QuestionsData> questionsData = getQuestions(file);
		ArrayList<PrizesData> prizesData = getPrizes(file);
		ArrayList<RaffleData> raffleData = getRaffles(file);
		ArrayList<Trivia_QuestionData> tqData = getTQs(file);
		ArrayList<WordsData> wordsData = getWords(file);
		ArrayList<AnswerData> answerData=getAnswers(file);
		ArrayList<ResponseData> responseData=getResponse(file);
		ArrayList<MimInfoData> mimInfoData=getMimInfos(file);
		ArrayList<KeyphraseData> keyphraseData=getKeyphrase(file);
		ArrayList<DistListData> distListData=getDistLists(file);
		ArrayList<ListDeltaData> listDeltaData = getListDeltas(file);
		ArrayList<UserInfoData> userInfoData=getUserInfos(file);


//		deleteAll(msgData,conn,file);
		
		migrateMsg(conn,msgData,file);
		migrateDelivery(conn,deliveryData,file);
		migrateOptions(conn,optionData,file);
		migrateQuestions(conn,questionsData,file);
		migrateTrivia(conn,tqData,file);
		migrateAnswers(conn,answerData,file);
		migrateResponse(conn,responseData,file);
		migrateKeyphrase(conn,keyphraseData,file);
		migrateWords(conn,wordsData,file);
		migrateRaffle(conn,raffleData,file);
		migratePrizes(conn,prizesData,file);
		migrateDistLists(conn,distListData,file);
		migrateListDeltas(conn,listDeltaData,file);
		migrateUserInfos(conn,userInfoData,file);
		migrateMimInfos(conn,mimInfoData,file);

		}
		
			
		
	}
	
}
