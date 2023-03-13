import java.util.*;
import java.io.*;

class Solution{
    static int[] daylist = {31,28,31,30,31,30,31,31,30,31,30,31};
    static int len;
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        for(int i=1; i<=l; i++){
            int day1=0, day2=0;
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            len = Integer.parseInt(st.nextToken());
            for(int x=0; x<len-1;x++)
                day1 += daylist[x];
            day1 += Integer.parseInt(st.nextToken());
            
            len = Integer.parseInt(st.nextToken());
            for(int x=0; x<len-1;x++) 
                day2 += daylist[x];
            day2 += Integer.parseInt(st.nextToken());

            sb.append("#").append(i).append(" ").append(day2-day1+1).append("\n");
        }
        System.out.println(sb);
    }
}