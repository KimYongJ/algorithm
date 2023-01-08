import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        
        int[][] dp = new int[31][31];

        for(int i=1; i<= 30; i++)
            dp[i][1] = dp[i][0] = i;
        
        for(int i=2; i<=30; i++)
            for(int j=2; j<=30; j++)
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];

        
        while(l-->0){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            sb.append(dp[n][m]).append("\n");
        }
        System.out.println(sb);
    }
}