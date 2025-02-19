//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25958
//1초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int M = Integer.parseInt(st.nextToken());// 목표 숫자, 1<=오천
		int K = Integer.parseInt(st.nextToken());// 나눌수(10^6 <=K <=10^7, K는 소수)
		ArrayList<Integer> list = getArr(M); // 1부터 M까지 있는 예쁜 수들
		
		int dp[] = new int[M + 1];
		dp[0] = 1;
		
		for(int a : list)
		{
			for(int j=a; j<=M; j++)
			{
				dp[j] += dp[j-a];
				dp[j] %= K;
			}
		}
		System.out.print(dp[M]);
	}
	public static ArrayList<Integer> getArr(int M) {
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=1; i<=M; i++)
		{
			int sum = 0;
			int s	= i;
			while(s != 0)
			{
				sum += s % 10;
				s	/=10;
			}
			if(i % sum == 0)
				list.add(i);
		}
		
		return list;
	}
}
