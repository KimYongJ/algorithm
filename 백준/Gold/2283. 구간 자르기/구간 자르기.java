//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2283
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		final int MAX = 1_000_001;
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());
		int K		= Integer.parseInt(st.nextToken());
		int psum[]	= new int[MAX+1];
		
		while(N-->0)
		
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			psum[s]++;
			psum[e]--;
		}
		
		for(int i=1; i<MAX; i++)
			psum[i] += psum[i-1];
		
		int sum = psum[0];
		int s	= 0;
		int e	= 0;
		while(e<MAX)
		{
			if(sum == K)
			{
				System.out.print(new StringBuilder().append(s).append(' ').append(e + 1));
				return;
			}
			if(sum < K)
				sum += psum[++e];
			else
				sum -=psum[s++];
		}
		System.out.print("0 0");
	}
}