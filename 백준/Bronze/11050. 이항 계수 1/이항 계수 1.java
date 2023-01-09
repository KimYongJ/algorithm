import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n+1][k+1];
        
        
        for(int i=0; i<=n; i++)
            for (int j = 0; j <=k; j++) {
                    if(i<j) continue;
                    else if (i == j || j== 0) {
                          dp[i][j] = 1;
                    }else {
                          dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                    }
             }
                
        
        System.out.println(dp[n][k]);
    }
}