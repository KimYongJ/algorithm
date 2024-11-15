//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14225
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, arr[];
	static boolean visit[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MAX = 2_000_001;
		N		= Integer.parseInt(br.readLine());
		arr		= new int[N];
		visit	= new boolean[MAX];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=N; i++)
			comb(i, 0, 0);
		
		for(int i=1; i<=MAX; i++)
			if(!visit[i])
			{
				System.out.print(i);
				return;
			}
				
	}
	public static void comb(int depth, int idx, int sum) {
		if(depth == 0)
		{
			visit[sum] = true;
			return;
		}
		for(int i=idx; i<N; i++)
			comb(depth - 1, i + 1, sum + arr[i]);
	}
}