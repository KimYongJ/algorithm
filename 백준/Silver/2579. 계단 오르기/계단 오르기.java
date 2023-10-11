// https://github.com/KimYongJ/algorithm
class Main{
    public static void main(String[] args)throws Exception{
    	int N = read();
    	int[] arr = new int[N+3];
    	int[] dp  = new int[N+3];
    	for(int i=3; i<N+3; i++)
    		arr[i] = read();
    	
    	// 점화식 dp[i] = Math.max(arr[i-1]+dp[i-3],dp[i-2]) + arr[i];
    	for(int i=3; i<N+3; i++) {
    		dp[i] = Math.max(arr[i-1]+dp[i-3],dp[i-2]) + arr[i];
    	}
    	System.out.println(dp[N+2]);
    }
    private static int read() throws Exception {
        int c, n = System.in.read() - 48;
        while ((c = System.in.read()) > 32) n = 10 * n + c - 48;
        return n;
    }
}
