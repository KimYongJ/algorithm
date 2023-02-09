import java.io.*;
import java.util.*;

class Solution{
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int l = Integer.parseInt(br.readLine());
        for(int i=1; i<=l; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            String x = "";
            if(a>b) x = ">";
            else if(a<b) x = "<";
            else x="=";
            
            System.out.println("#"+i+" "+x);
        }
    }
}