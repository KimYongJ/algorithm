// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int dp[] = new int[100]; // 1~99까지 숫자 중 최소로 동전을 사용하는 것
		
		for(int i=1; i<100; i++) 
		{
			int use_1 = i/10 + i%10;
			int use_25= 100;
			if(i>=25)
			{
				for(int num = i, idx = 0; num>=0; num-=25, idx++)
					use_25 = Math.min(use_25, idx + num/10 + num % 10);
			}
			dp[i] = Math.min(use_1, use_25);
		}
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			int cnt = 0;
			long origin = Long.parseLong(br.readLine());
			while(origin > 0) 
			{
				cnt += dp[(int)(origin % 100)];
				origin /= 100;
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}
