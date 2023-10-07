// https://github.com/KimYongJ/algorithm
import java.util.Scanner;
class Main{
    public static void main(String[] args)throws Exception{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+2];
        
        dp[1] = 1;
        dp[2] = 3;
        int idx = 0;
        for(int i=3; i<=n; i++){
            dp[i] = (dp[i-1] + 2* dp[i-2]) % 10007;
        }
        System.out.println(dp[n]);
        
    }
}