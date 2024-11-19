//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27172
import java.util.Arrays;

class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N			= read();		// 플레이어 수 (2≤N<십만)
		int origin[]	= new int[N];	// 각플레이어의 숫자 1~백만
		int arr[]		= new int[N];
		int score[]		= new int[1_000_001];
		boolean visit[] = new boolean[1_000_001];
		
		for(int i=0; i<N; i++)
		{
			origin[i] = arr[i] = read();
			visit[arr[i]] = true;
		}
		
		Arrays.sort(arr);
		
		int max = arr[N-1];
		
		for(int i=0; i<N; i++)
		{
			int meetCnt = 0;
			for(int n = arr[i]<<1; n <=max; n+=arr[i])
			{
				if(visit[n])
				{
					++meetCnt;
					--score[n];
				}
			}
			score[arr[i]] += meetCnt;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int o : origin)
			sb.append(score[o]).append(' ');
		System.out.print(sb.toString());
	}
}