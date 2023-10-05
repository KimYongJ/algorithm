// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 바텀업 방식
        int[] dp = new int[n+1];
        for(int i=2; i<=n; i++) { // 2부터 탐색 시작
        	dp[i] = dp[i-1]+1; // 이전값에서 1을 더한 값
        	if(i%3==0) { // 3으로 나눌 수 있는 경우
        		dp[i] = Math.min(dp[i], dp[i/3]+1); // 1을 더한 총 횟수와 3으로 나누기 전 인덱스 중 가장 작은 것으로 갱신
        	}
        	if(i%2==0) {// 2로 나눌 수 있는 경우
        		dp[i] = Math.min(dp[i],dp[i/2]+1);
        	}
        }
        
        System.out.println(dp[n]);
    }
}