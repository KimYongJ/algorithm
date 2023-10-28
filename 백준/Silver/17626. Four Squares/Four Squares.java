// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int dp[] = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1];
            for(int j=1; j*j<=i; j++){
                // dp의 이전 값과 임의의 j의 제곱에서 i를 뺀 값중 작은 것을 저장한다.
                dp[i] = Math.min(dp[i],dp[i-j*j]);
            }
            dp[i] += 1;
        }
        System.out.println(dp[n]);
    }
}