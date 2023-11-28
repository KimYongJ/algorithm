// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()), result = n;

        if(n>2){
            int dp[] = new int[n+1];
            dp[1] = 1;
            dp[2] = 2;
        
            for(int i=3; i<=n; i++)
                dp[i] = (dp[i-1] + dp[i-2])% 15746;
        
            result = dp[n];
        }
        
        System.out.println( result );
    }
}