
package ibm;

/**
 *
 * @author Santhosh
 */
import java.io.*;
import java.util.*;


public class Levein
{
    String ss;
    char s[];
    char t[];
    int d[][];
    int i,j;
    int m,n;
    int[] a;

    public double calulateVaue(String string1, String string2)
    {
        if(string1.charAt(0) != string2.charAt(0))
            return(1000);
        double lt,lcs,LCSratio,EditDistance,Alpha;
        lt = (string1.length()>string2.length())?string1.length():string2.length();
        lcs = LongestCommonSubsequence(string1,string2);
        LCSratio = (lcs/lt);

        EditDistance = editDistance(string1,string2);

        Alpha = (EditDistance/LCSratio);
        System.out.println("alpha " + Alpha + " " + string1 + " " + string2);
        return Alpha;
    }

    public double editDistance(String string1,String string2)
    {

        String st1 = CS(string1);
        String st2 = CS(string2);
        if(st2.equals("NA"))
            return 1111;
        //System.out.println("111  "+st2 + " " +st2.length());
         s = st1.toCharArray();
        t = st2.toCharArray();
        m = st1.length();
        n = st2.length();
        d = new int[m+1][n+1];
        a = new int[3];

        try
        {
            for(i=0;i<=m;i++)
                 d[i][0] = i;

            for(j=0;j<=n;j++)
                d[0][j] = j;

            for(j=1;j<n;j++)
            {
                for(i=1;i<m;i++)
                {
                    if(s[i] == t[j])
                        d[i][j] = d[i][j] + d[i-1][j-1];
                    else
                    {
                        a[0] = d[i - 1][j] + 1;
                        a[1] = d[i][j-1] + 1;
                        a[2] = d[i-1][j-1] + 1;
                        Arrays.sort(a);
                        d[i][j] = a[0];
                    }
                }
            }
           // int aa = d[m-1][n-1].length;
            double answer = 0;
            answer = (double)d[m-1][n-1];
            return(answer);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        return 1000;
    }

    String CS(String str)
        {
            String s;
            s = str;
            //System.out.println("Value  "+s + " " +s.length());
            StringBuilder sb = new StringBuilder();
            //add first char
            char lastChar = s.charAt(0);
            sb.append(lastChar);

            int len = s.length();

            for(int i = 1; i < len; i++)
            {
                    char c = s.charAt(i);
                    if(c != lastChar)
                    {
                        sb.append(c);
                        lastChar = c;
                    }
            }
             ss = sb.toString();
             String s1 = ss.replaceAll("[aeiou]", "");
             if (s1.length()==0)
                 return "NA";
            return s1;
         }

   double LongestCommonSubsequence(String sub1, String sub2)
    {
              String s1 = sub1;
                String s2 = sub2;
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
                //System.out.println("length of LCS = " + num[s1.length()][s2.length()]);

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
                //System.out.println(result.toString() + " is the LCS");
                return num[s1.length()][s2.length()];
          
      }
}