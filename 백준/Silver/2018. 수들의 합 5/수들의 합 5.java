//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2018
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		int s	= 1;
		int e	= 1;
		int sum	= 1;
		int cnt = 0;
		
		while(s <= e && e <= N)
		{
			if(sum >= N)
			{
				if(sum == N)
					++cnt;
				sum -=s++;
			}
			else
			{
				sum += ++e;
			}
		}
		System.out.print(cnt);
	}
}