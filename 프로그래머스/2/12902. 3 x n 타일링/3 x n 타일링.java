// https://github.com/KimYongJ/algorithm
class Solution {
    public int solution(int n) {
        int answer = 0;
        int mod = 1_000_000_007;
        int[] dp = new int[n+1];
        dp[1] = 2;
        dp[2] = 3;
        for(int i = 3; i <= n; i++){
            if(i%2 == 0){
                //짝수
                dp[i] = dp[i-1]%mod + dp[i-2]%mod;
            } else {
                // 홀수
                dp[i] = dp[i-1]*2%mod + dp[i-2]%mod;
            }
            dp[i] = dp[i]%mod;
        }
        return dp[n%2==1 ? 0 : n];
    }
}