/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.*;

public class WordFactor
{
    static String answer_Text;
    static String question_Text;
    static String query_ID;
    static String query_Category;
    static String query_Type;
    static String result;
    static String questionResult;
    static String domain[] = {"eng_faq"};
    static String queries[] = {"English"};
    static String catgry;
    static int tokenCount;
    static int i,j,k;
    static String[] strQuestion;
    static String bannedWords[] = {"what","the","is","was","a","and","but","will","can","could","would","an","who"};
    static boolean flagRepeatedWord =   true;
    static boolean flagIsBanned    =   true;
    static Writer outputWriter  =   null;


     public WordFactor()
     {
         try
         {
            for(int a=0;a<domain.length;a++)
             {
                //Source file
                File file = new File("E:\\E Books\\FIRE_TRAINING_DATA\\FIRE_TRAINING_DATA\\FAQs\\"+queries[a]+"\\"+domain[a]+".xml");

                //Document Builder
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();

                //Getting all Sms
                NodeList nodeLst = doc.getElementsByTagName("FAQ");
                //creating the parent directory
                String s1 = "E:\\IBM Temp\\Trainigsetanswers\\"+queries[a];

                (new File(s1)).mkdirs();
                try
                {
               
                        System.out.print("DOne \n");

                         //to read all the nodes from the file under each language
                         for (int loop_count = 0; loop_count < nodeLst.getLength(); loop_count++)
                         {
                                //Looping for all SMS
                                Node fstNode = nodeLst.item(loop_count);
                                if (fstNode.getNodeType() == Node.ELEMENT_NODE)
                                {
                                    //Getting query ID
                                    Element fstElmnt = (Element) fstNode;
                                    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("FAQID");
                                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                                    NodeList fstNm = fstNmElmnt.getChildNodes();
                                    query_ID = ((Node) fstNm.item(0)).getNodeValue();

                                    //getting domain name
                                    Element fstElmnt1 = (Element) fstNode;
                                    NodeList fstNmElmntLst1 = fstElmnt1.getElementsByTagName("DOMAIN");
                                    Element fstNmElmnt1 = (Element) fstNmElmntLst1.item(0);
                                    NodeList fstNm1 = fstNmElmnt1.getChildNodes();
                                    catgry = ((Node) fstNm1.item(0)).getNodeValue();

                                        //loops for matching the category from the file and the given string array

                                    //to create a new folder with the domain name
                                    (new File(s1+"/"+catgry)).mkdirs();
                                    File file2 = new File(s1+"/"+catgry+ "/"+"log.txt");
                                   
                                    FileWriter fstream = new FileWriter(file2,true);
                                    outputWriter = new BufferedWriter(fstream);
                                    outputWriter.write(query_ID);
                                    outputWriter.write(" - ");
                                    fstNmElmntLst = fstElmnt.getElementsByTagName("QUESTION");
                                    fstNmElmnt = (Element) fstNmElmntLst.item(0);
                                    fstNm = fstNmElmnt.getChildNodes();
                                    question_Text = ((Node) fstNm.item(0)).getNodeValue();
                                    StringTokenizer token2 = new StringTokenizer(question_Text);
                                    tokenCount = token2.countTokens();
                                    strQuestion = new String[tokenCount];

                                    try
                                    {
                                        for(i=0;i<tokenCount;i++)
                                        {
                                            questionResult = token2.nextToken();
                                            questionResult = questionResult.trim();
                                            strQuestion[i] = questionResult;

                                            if(questionResult.endsWith("?")||questionResult.endsWith("."))
                                              questionResult = questionResult.substring(0, questionResult.length()-1);


                                            for(j=0;j<i;j++)
                                            {
                                                if(strQuestion[j].equalsIgnoreCase(questionResult))
                                                    flagRepeatedWord=false;
                                            }

                                            for(j=0;j<12;j++)
                                            {
                                                if(questionResult.equalsIgnoreCase(bannedWords[j]))
                                                    flagIsBanned=false;
                                            }

                                            if(flagRepeatedWord==true && flagIsBanned==true)
                                            {
                                                outputWriter.write(questionResult);
                                                //outputWriter.write(" ");
                                                System.out.println(questionResult);
                                                questionResult = questionResult.trim();
                                                
                                                try
                                                {
                                                    //System.out.println("E:\\IBM Temp\\dictionary\\" + questionResult);
                                                    FileInputStream synonymStream = new FileInputStream(new File("E:\\IBM Temp\\dictionary\\" + questionResult));
                                                    BufferedReader synonymReader = new BufferedReader(new InputStreamReader(new DataInputStream(synonymStream)));
                                                    String synonymLine = new String();
                                                    System.out.println(query_ID + " " + questionResult + " ");
                                                    while((synonymLine = synonymReader.readLine()) != null)
                                                    {
                                                            
                                                            outputWriter.write(synonymLine);
                                                            //outputWriter.write(" ");
                                                            System.out.println("\t" + synonymLine);

                                                    }
                                                    System.out.println("\n");
                                                }
                                                catch(Exception e)
                                                {
                                                    System.out.println("+1");
                                                    continue;
                                                }
                                           }

                                            flagRepeatedWord = true;
                                            flagIsBanned = true;

                                        }
                          
                                    }
                                    catch(Exception e)
                                    {
                                        System.out.print("Exception:" + e);
                                    }

                                    outputWriter.close();
                                    //to create answer files under each domain after matching
                                    File file1 = new File(s1+"/"+catgry+"/"+query_ID+".txt");
                                    Writer output = null;
                                    output = new BufferedWriter(new FileWriter(file1));
                                    //Getting SMS Content
                                    fstNmElmntLst = fstElmnt.getElementsByTagName("ANSWER");
                                    fstNmElmnt = (Element) fstNmElmntLst.item(0);
                                    fstNm = fstNmElmnt.getChildNodes();
                                    answer_Text = ((Node) fstNm.item(0)).getNodeValue();
                                    StringTokenizer token = new StringTokenizer(answer_Text);
                                    try
                                     {
                                        while (token.hasMoreTokens())
                                        {
                                            result = token.nextToken();
                                            output.write(result);
                                        }
                                        output.close();
                                      }
                                    catch(Exception e)
                                    {
                                        System.out.print("Exception" + e);
                                    }

                         }
                     }
                   
                  }
                  catch(Exception e)
                  {
                      System.out.print("Exception:" + e);
                  }
            }
         }
        catch (Exception e)
        {
            System.out.println("Exception " + e );
        }
     }
}
