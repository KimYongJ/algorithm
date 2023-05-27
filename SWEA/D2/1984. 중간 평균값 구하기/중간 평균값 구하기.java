import java.io.*;
import java.util.*;

class Solution{
 	public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());

        for(int r = 1; r<=l; r++){
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            int min = 10001;
        	int max = -1;
            for(int i=0; i<10; i++){
                int n = Integer.parseInt(st.nextToken());
                sum += n;
                if(n<min) min = n;
                if(n>max) max = n;
            }       
            sb.append("#").append(r).append(" ").append(Math.round((sum-min-max)/8.0)).append("\n");
        }
        
        System.out.println(sb);
    }
}