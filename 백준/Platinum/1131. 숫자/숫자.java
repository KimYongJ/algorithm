//https://www.acmicpc.net/problem/1131
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int A, B, K;
	static int pow[];
	static long dp[] = new long[6000000];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());//1 ≤ A ≤ B ≤ 1,000,000
		B = Integer.parseInt(st.nextToken());//1 ≤ A ≤ B ≤ 1,000,000
		K = Integer.parseInt(st.nextToken());//1 ≤ K ≤ 6
		pow = new int[10];
		
		for(int i=1; i<10; i++)
			pow[i] = (int)Math.pow(i, K);
		
		long sum = 0;
		
		for(int i=A; i<=B; i++)
			sum += dfs(i);
		
		System.out.print(sum);
	}
	static long dfs(int num)
	{
		if(dp[num] > 0)
			return dp[num];
		
		if(dp[num] == -1)
		{
	        long min = num;
	        int cur = getSum(num);
	        while (cur != num) {          // 싸이클 한 바퀴 돌며 최소값 찾기
	            if (cur < min) min = cur;
	            cur = getSum(cur);
	        }
	        return min;  
		}
		
		dp[num] = -1;
		
		return dp[num] = Math.min(num, dfs(getSum(num)));
	}
	static int getSum(int num)
	{
		int sum = 0;
		
		for(char c : String.valueOf(num).toCharArray())
			sum += pow[c - '0'];
		
		return sum;
	}
}