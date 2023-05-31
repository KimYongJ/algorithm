 import java.io.*;
import java.util.*;

class Solution{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(st.nextToken());
        
        for(int i=1; i<=l; i++){
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int m1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());
            
            int Total_min = h1*60 + h2*60 + m1 + m2;
            
            int hour = (Total_min/60)%12;
            if(hour==0) hour = 12;
            
            int minute = Total_min%60;
            
            sb.append("#").append(i).append(" ").append(hour).append(" ").append(minute).append("\n");
        }
        
        System.out.println(sb);
    }
    
}