//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10266
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MAX	= 360_000;
		final int LEN	= MAX<<1;
		int fail[]		= new int[MAX];
		boolean patn[]	= new boolean[MAX];
		boolean text[]	= new boolean[LEN];
		int N			= Integer.parseInt(br.readLine());// 바늘수(2 ≤ 200 000)
		// (0 ≤ n < 360,000)
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)text[Integer.parseInt(st.nextToken())] = true;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)patn[Integer.parseInt(st.nextToken())] = true;
		
		for(int i=0, j=MAX; i<MAX;)
			text[j++] = text[i++];
		
		for(int i=1,j=0; i<MAX; i++)
		{
			while(0<j && patn[i] != patn[j])
				j = fail[j - 1];
			
			if(patn[i] == patn[j])
				fail[i] = ++j;
		}
		
		for(int i=0, j=0; i<LEN; i++)
		{
			while(0<j && text[i] != patn[j])
				j = fail[j - 1];
			
			if(text[i] == patn[j])
			{
				if(++j== MAX)
				{
					System.out.print("possible");
					return;
				}
			}
		}
		System.out.print("impossible");
	}
}
