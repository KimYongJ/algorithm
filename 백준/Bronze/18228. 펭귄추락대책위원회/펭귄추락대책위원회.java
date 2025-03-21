//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18228
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		int min1= 1<<30;
		int min2= 1<<30;
		boolean flag = true;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			int now = Integer.parseInt(st.nextToken());
			
			if(now < 0)
			{
				flag = !flag;
				continue;
			}
			
			if(flag)
				min1 = Math.min(min1, now);
			else
				min2 = Math.min(min2, now);
		}
		System.out.print(min1 + min2);
	}
}