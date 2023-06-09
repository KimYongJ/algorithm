import java.io.*;
import java.util.*;
class Solution{
 	public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        for(int i=1; i<=l; i++)
            sb.append("#").append(i).append(" ").append(new String(Base64.getDecoder().decode(br.readLine()))).append("\n");
         System.out.println(sb);
    }

}