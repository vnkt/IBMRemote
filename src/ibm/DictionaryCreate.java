/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibm;
import java.io.*;
/**
 *
 * @author freax
 */
public class DictionaryCreate
{
    public DictionaryCreate()
    {
        try
        {
            FileInputStream synonymStream = new FileInputStream(new File("C:\\Users\\vnKt\\Documents\\NetBeansProjects\\IBM\\src\\th_en_US_v2.dat"));
            BufferedReader synonymReader = new BufferedReader(new InputStreamReader(new DataInputStream(synonymStream)));
            FileWriter synonymFileWriter;           // used to write text input into a file
            //String lineBreak = System.getProperty("line.separator");
            int i, j, k;                      // variables for iteration and skipping text while parsing the synonyms of a given word
            int lineLength, synonymLineLength;
            int numOfOccurences = 0;                // has the number of occurences i.e howmany synonyms are there for a given word
            String synonymLine = null;              // to get a line input from the Thesaurus file
            String synonymBuilder = null;           // will store the synonyms character by character, of a word

            String dictionaryDirectory = "E:\\IBM Temp\\dictionary";
            if(!(new File(dictionaryDirectory).mkdir()))
            System.out.println("wasn't able to create the dictionary directory");

            String synonymFileName = "E:\\IBM Temp\\dictionary\\";     // directory path where all synonym files will be created and stored
            String synonymWord;                     // has the word, which is used as the filename, under which its synonyms would be there

            synonymLine = synonymReader.readLine();
            while((synonymLine = synonymReader.readLine()) != null)
            {
                if(synonymLine.charAt(0) != '(')
                {
                    i = 0;
                    j = 0;
                    lineLength = synonymLine.length();
                    System.out.println(synonymLine);
                    //System.out.println(lineLength);
                    i = synonymLine.indexOf('|');   // gets the index of | character to find the numOfOccurences and the word
                    synonymWord = synonymLine.substring(0, i);          // the word whose synonyms has to be found
                    //System.out.println(synonymWord + " is the word");
                    //System.out.println("index is " + i);
                    i += 1;

                    /*while(l < lineLength) {
                        numOfDigits++;
                        l++;
                    }*/

                    while(i < lineLength)
                    {
                        //System.out.println(String.valueOf(synonymLine.charAt(i)));
                        numOfOccurences += (Integer.parseInt(String.valueOf(synonymLine.charAt(i))) * Math.pow(10,(lineLength-i-1)));
                        i++;
                    }
                    //System.out.println("numOfOccurences is " + numOfOccurences);
                    while(j< numOfOccurences)
                    {
                        k = 0;
                        synonymLine = synonymReader.readLine();
                        synonymLineLength = synonymLine.length();
                        while(k<= synonymLineLength-1)
                        {
                            if(synonymLine.charAt(k) == '(')
                            {
                                while(synonymLine.charAt(k) != ')')
                                    k++;
                                k++;
                            }
                            else
                            {
                                if(synonymLine.charAt(k) == '|')
                                {
                                    k++;
                                    synonymBuilder += " ";
                                }
                                synonymBuilder += String.valueOf(synonymLine.charAt(k));
                                k++;
                            }
                        }
                        System.out.println(synonymBuilder);
                        synonymFileWriter = new FileWriter(new File(synonymFileName + synonymWord), true);
                        synonymFileWriter.write(synonymBuilder);
                        synonymBuilder = "";
                        synonymFileWriter.flush();
                        numOfOccurences = 0;
                        j++;
                    }
                    //System.out.println(numOfOccurences);
                 }
            }
        }
        catch(StringIndexOutOfBoundsException s)
        {
            System.out.println(s.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

