
package ibm;
import java.io.*;
/**
 *
 * @author freax
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
            DataInputStream consoleInput = new DataInputStream(System.in);
            System.out.println("Enter the two strings whose LCS has to be found : ");
            try {
                
                String s1 = consoleInput.readLine();
                String s2 = consoleInput.readLine();
                int[][] num = new int[s1.length()+1][s2.length()+1];           
                //Actual algorithm
                for (int i = 1; i <= s1.length(); i++) {
                    for (int j = 1; j <= s2.length(); j++) {
                        if (s1.charAt(i-1) == s2.charAt(j-1))
                                num[i][j] = 1 + num[i-1][j-1];
                        else
                                num[i][j] = Math.max(num[i-1][j], num[i][j-1]);
                   }
                }
                System.out.println("length of LCS = " + num[s1.length()][s2.length()]);
                
                int s1position = s1.length(), s2position = s2.length();
                StringBuilder result = new StringBuilder();
                //List<E> result = new LinkedList<E>();
 
                while (s1position != 0 && s2position != 0)
                {
                    if (s1.charAt(s1position - 1) == (s2.charAt(s2position - 1)))
                    {
                        result.append(s1.charAt(s1position - 1));
                        s1position--;
                        s2position--;
                }
                else if (num[s1position][s2position - 1] >= num[s1position - 1][s2position])
                {
                        s2position--;
                }
                else
                {
                        s1position--;
                }
        }
        result.reverse();
        System.out.println(result.toString() + " is the LCS");


            }
            catch(IOException io) {
                System.out.println(io.getMessage());
            }
                
    }
}    
