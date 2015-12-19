package ibm;
/**
 *
 * @author freak
 */
import java.io.*;
import java.util.StringTokenizer;

public class QuerySearch {
    String query_Text;
    String query_ID;
    String query[] = {"railways","IRCTC","rail","train","pnr","PNR"};
    String choices[][]  =   {{"agriculture","agro", "bio", "crop", "food", "production", "industry", "biotechnology", "organic", "pesticides", "pests", "spray", "harvest", "weather", "soil", "fertile", "fertilizers", "environment"},
                            {"bank","account", "salary", "CSP", "customer", "savings", "deposits", "wealth", "bill", "utility", "withdraw", "ATM", "credit", "debit", "transactions", "cheque", "reciept", "payable", "clearance", "remittance", "denomination", "documents"},
                            {"career","education","personality","skills","counseling","assessment","marketing","confidence","job","passion","schedule","acedemic","admission","graduate","qualification"},
                            {"tourism","travel","expenditure","accomodation","food","expense"," destination","tour","package","trip","guide","fare","tax","cruise","cuisine","visit","vacation","holiday","tradition","region","language"},
                            {"visa","application","online","registration","VFS","apply","passport","migration","ETA","travel","attestation","affidavit","PCC"},
                            {"telecom","broadband","signal","transmission","distance","speed","mobile","computer","server","station","broadcast","coverage","telecact","TV","radio","tower","transmitter","reciever","channel","bandwidth","frequency","connection","circuit","protocol","internet","message","sms","connection","uplink","downlink","plan","pack","gprs"},
                            {"health","body","disease","ill","sick","medicine","hospital","reason","effect","cause","allergy","alcohol","rehydrate","fever","rest","liver","heart","brain","cosmetic","hygine","treatment","clinic","ambulance"},
                            {"internal devices","data","software","hardware","failure","error","device","driver","port","drive","computer","memory","chip","processor","battery","storage","USB","OS","cable","power"},
                            {"insurance","cost","amount","total","assessment","value","Rs.","fixed","deposit","banking","policy","property","wealth","purchase","record","agent","company","worth"},
                            {"gk","first","last","biggest","largest","smallest","tallest","longest","shortest","capital","nation","city","flag","country","lowest","world","greatest","maximum","oldest"},
                            {"personality","improvement","habit","tendency","success","achieve","goal","path","destination","objective","ambition","achievement","progress","performance","assessment"},
                            {"loan","cost","service","bank","policy","transcripts","sevices","accounts","renewal","period","lapse"},
                            {"recipy","taste","gravy","pepper","salt","mixture","ingredients","prepare","prepare","make"},
                            {"web","hosting","URL","WWW","2.0","resource","database","server","service","provider","vendor","software","networking","communication","social","media","online","warehouse","searchengines","google","facebook","twitter"},
                            {"sport","sport", "game", "energy", "win", "won", "lost", "defeat", "coach", "medal", "champion", "winner", "championship", "cup", "jersy", "captain", "players", "referee", "team", "football", "basketball", "hockey", "cricket", "tennis", "swimming", "boxing", "weightlifting", "sportstar", "chess", "golf","trophy"},
                            {"railways","IRCTC","rail","train","trains","pnr","PNR","tatkal","booking","payment","ticket","e-ticket"}};
    
    String available[] = {"ENG_AGRICULTURE","ENG_BANK","ENG_CAREER","ENG_TOURISM","VISA","TELECOMMUNICATION","ENG_HEALTH","INTERNAL DEVICES","ENG_INSURANCE","GK","PERSONALITY DEVELOPMENT","ENG_LOAN","RECIPES","WEB","ENG_SPORTS","INDIAN_RAILWAYS"};

    Levein l;
    //double id_match[][] = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
    
    public QuerySearch(String ID, String Message)
    {
        l = new Levein();
        try
        {
            //Document Builder
            query_Text = Message;
            query_ID = ID;

            StringTokenizer token = new StringTokenizer(query_Text);
            StringTokenizer logTokens = new StringTokenizer("");
            int count = 0;
            String result;
            try
            {
               while (token.hasMoreTokens())
               {
                   File file1 = new File("E:\\IBM temp\\working\\answer"+count+".txt");
                   Writer output = null;
                   output = new BufferedWriter(new FileWriter(file1));
                   result = token.nextToken();
                   output.write(result);
                   //System.out.println(result);
                   output.write("\r\n");
                   output.close();
                   count++;
               }
               double id_match[][] = {  {100,100,100,100,100,100},{100,100,100,100,100,100},{100,100,100,100,100,100},{100,100,100,100,100,100},
                                        {100,100,100,100,100,100},{100,100,100,100,100,100},{100,100,100,100,100,100},{100,100,100,100,100,100},
                                        {100,100,100,100,100,100},{100,100,100,100,100,100},{100,100,100,100,100,100},{100,100,100,100,100,100},
                                        {100,100,100,100,100,100},{100,100,100,100,100,100},{100,100,100,100,100,100},{100,100,100,100,100,100}};
               
               try
                {
                  double lowest =100;
                  int id = 0;
                  String a1 = null,b1 = null;
                  for(int i = count-1; i >= 0 ; i--)
                  {
                   File file1 = new File("E:\\IBM temp\\working\\answer" + i + ".txt");
                   BufferedReader br = new BufferedReader(new FileReader(file1));
                   String answer = br.readLine();
                   answer = answer.trim();
                  
                   for(int j=0;j<16;j++)
                   {
                       for(int k = 0;k < choices[j].length; k++)
                       {
                            for(int a =0 ;a < 5; a++)
                           {
                               double abcd = l.calulateVaue(answer, choices[j][k]);
                               if(abcd < id_match[j][a])
                               {
                                       id_match[j][a] = l.calulateVaue(answer,choices[j][k]);
                                       if(id_match[j][a] < lowest)
                                       {
                                           lowest = id_match[j][a];
                                           id = j;
                                           a1 = answer;
                                           b1 = choices[j][k];
                                       }
                                       //System.out.println(abcd + " " + answer + " " + choices[j][k]);
                                       break;
                               }
                           }
                       }
                   }
               }
               System.out.println("Hello " + lowest +" " + id + " " + a1 + " " +b1);
               }

               catch(Exception e)
               {
                   e.printStackTrace();
                   //System.out.println("Hello 123");
               }
              // int logLineLength;


               File file2 = new File("E:\\IBM temp\\Trainigsetanswers\\English\\INDIAN_RAILWAYS\\log.txt");
               BufferedReader logReader = new BufferedReader(new FileReader(file2));
               //StringBuffer logBuffer = new StringBuffer();
               //String logString;

               /*while((logString = logReader.readLine())!= null)
                   logBuffer.append(logString);

               logString = logBuffer.toString();
               logTokens = new StringTokenizer(logString);*/
               String logTokensLine;
               String logTokenString;
               int hiphenIndex = 0;

               for(int i = count-1; i >= 0 ; i--)
               {
                   File file1 = new File("E:\\IBM temp\\working\\answer" + i + ".txt");
                   BufferedReader br = new BufferedReader(new FileReader(file1));
                   String answer = br.readLine();
                   answer = answer.trim();

                   while((logTokensLine = logReader.readLine()) != null)
                   {
                	   //System.out.println(logTokensLine);
                	   hiphenIndex = logTokensLine.indexOf('-');


                	   //System.out.println(hiphenIndex);
                	   String id = null;
                	   double array[] = {100,100,100,100,100};
                	   String array1[] = new String[5];
                          //  	String input;

                	   //logTokensLine = logTokensLine.substring(hiphenIndex + 2);
                	   //System.out.println(logTokensLine);
                	   logTokens = new StringTokenizer(logTokensLine);
                	   int numOfTokens = logTokens.countTokens();

                	   while(numOfTokens > 0)
                	   {
                		   logTokenString = logTokens.nextToken();
                		   double abcd;
                		   abcd = l.calulateVaue(answer, logTokenString);

                		   for(int a =0 ;a < 5; a++)
                		   {
                			   if(abcd<array[a])
                			   {
                				   array[a] = l.calulateVaue(answer,logTokenString);
                				   array1[a] = id;
                				   //System.out.println(answer + " " + logTokenString + " " + array[a] + " " + array1[a]);
                				   break;
                			   }
                		   }
                		   /*if(newLineFlag == 1)
                        	{

                        	}*/
                		   --numOfTokens;
                	   }

                	   Writer output = null;
                	   output = new BufferedWriter(new FileWriter(file1));
                	   for(int b = 0; b <5 ; b++)
                	   {
                		   output.write(array[b]+" - " + array1[b]+"\n");
                	   }
                	   output.close();
                   	}
               }
             }
             catch(IOException ioe)
             {
                ioe.printStackTrace();
             }
        }
        catch(Exception ioe)
        {
        	ioe.printStackTrace();
        }

    }
}