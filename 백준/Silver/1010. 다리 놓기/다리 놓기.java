import java.io.*;
import java.util.*;

class Main{
    static final int MAX = 31;
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int[][] dp = new int[MAX][MAX];
        for(int i=1; i<MAX; i++)
            dp[i][1] = i;
            
        for(int i=2; i<MAX; i++)
            for(int j=2; j<MAX; j++)
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
        
        
        while(l-->0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            sb.append(dp[m][n]).append("\n");
        }
        System.out.println(sb);
    }
}