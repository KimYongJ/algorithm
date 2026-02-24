//https://www.acmicpc.net/problem/30395
//1초 1024MB
//3 // 일수 1<=1,000
//1 0 2 // i일에 푼 문제 수(0<=100)
//답 : 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int nextStrick = -1;
		int max = 0;
		int now = 0;
		boolean use = false;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int day=0; day<N; day++)
		{
			if(day == nextStrick)
			{
				use = false;
			}
			
			int cnt = Integer.parseInt(st.nextToken());
			
			if(cnt == 0)
			{
				if(use)
				{
					now = 0;
				}
				else
				{
					use = true;
					nextStrick = day + 2;
				}
				continue;
			}
			
			max = Math.max(max, ++now);
		}
		
		System.out.print(max);
	}
}