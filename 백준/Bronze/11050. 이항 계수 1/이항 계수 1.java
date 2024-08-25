//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11050
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
    public static void main(String[] args)throws Exception{
        int n = read();
        int k = read();
        int[][] dp = new int[n+1][k+1];
        
        for(int i=0; i<=n; i++)
            for (int j = 0; j <=k; j++) 
            	if(i>=j)
            	{
                    if (i == j || j== 0)
                          dp[i][j] = 1;
                    else
                          dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            	}

        System.out.println(dp[n][k]);
    }
}