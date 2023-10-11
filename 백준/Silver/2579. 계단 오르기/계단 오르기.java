// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int[] arr = new int[N+3];
    	int[] dp  = new int[N+3];
    	for(int i=3; i<N+3; i++)
    		arr[i] = Integer.parseInt(br.readLine());
    	
    	// 점화식 dp[i] = Math.max(arr[i-1]+dp[i-3],dp[i-2]) + arr[i];
    	for(int i=3; i<N+3; i++) {
    		dp[i] = Math.max(arr[i-1]+dp[i-3],dp[i-2]) + arr[i];
    	}
    	System.out.println(dp[N+2]);
    }
}
