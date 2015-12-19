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

public class Main
{
    static String query_Text;
    static String query_ID;
    static QuerySearch qs;
    
    public static void main(String argv[])
    {
        try
        {
            //Creating dictionary
            //new DictionaryCreate();
            
            //Creating the daatbase
            //new WordFactor();

            //Source file
            File file = new File("E:\\E Books\\FIRE_TRAINING_DATA\\FIRE_TRAINING_DATA\\SMS_Queries\\Monolingual Task\\English\\eng-mono.xml");
                     
            //Document Builder 
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            
            //Getting all Sms
            NodeList nodeLst = doc.getElementsByTagName("SMS");
            System.out.println("Number of Queries " + nodeLst.getLength());
            for (int loop_count = 0; loop_count < nodeLst.getLength(); loop_count++)
            {
                //Looping for all SMS
                Node fstNode = nodeLst.item(loop_count);
                
                    if (fstNode.getNodeType() == Node.ELEMENT_NODE)
                    {
                        //Getting query ID

                        Element fstElmnt = (Element) fstNode;
                        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("SMS_QUERY_ID");
                        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        NodeList fstNm = fstNmElmnt.getChildNodes();
                        System.out.println("ID : "  + ((Node) fstNm.item(0)).getNodeValue());
                        query_ID = ((Node) fstNm.item(0)).getNodeValue();
            

                        //Getting SMS Content
                        fstNmElmntLst = fstElmnt.getElementsByTagName("SMS_TEXT");
                        fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        fstNm = fstNmElmnt.getChildNodes();
                        System.out.println("Text : "  + ((Node) fstNm.item(0)).getNodeValue());
                        query_Text = ((Node) fstNm.item(0)).getNodeValue();

                        qs = new QuerySearch(query_ID, query_Text);
                        System.exit(0);
                    }
                System.out.println("\n");
                }
         }
        catch (Exception e)
        {
            System.out.println("Exception " + e );
        }
    }
}