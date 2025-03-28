//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19645
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int value[] = new int[N];
		int sum		= 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			sum += value[i] = Integer.parseInt(st.nextToken());
		
		int len = 850;
		
		boolean dp[][] = new boolean[len+1][len+1];// [x]선배와[y]선배 값을 통해 내가 가능한가?를 담음
		
		dp[0][0] = true;
		
		for(int val : value)
			for(int x=len; x>=0; x--)
				for(int y=len; y>=0; y--)
				{
					if(x - val >= 0)
						dp[x][y] |= dp[x - val][y];
					if(y - val >= 0)
						dp[x][y] |= dp[x][y - val];
				}

		int max = 0;
		
		for(int x=len; x>=0; x--)
			for(int y=len; y>=0; y--)
			{
				if(dp[x][y] && x > max && y > max)
					max = sum - x - y;
			}
		
		System.out.print(max);
	}
}