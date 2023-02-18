import java.io.*;
class Solution{
 	public static void main(String[] args)throws Exception{
        for(char c : new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray()) System.out.print((c-64)+" ");
    }
}