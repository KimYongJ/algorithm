// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int		result = Integer.MAX_VALUE;
	static int		N, K;
	static int		map[][];
	static boolean	visit[];
	
	public static void backtracking(int depth, int node, int sum) {
		if(depth == 0) 							// 모든 노드를 돌았다면 result 체크
		{
			result = Math.min(result, sum);
			return;
		}
		if(result <= sum)						// 조기종료
		{
			return;
		}
		
		for(int i=0; i<N; i++) 					// 노드 탐색
		{
			if(!visit[i]) 						// 백트레킹
			{
				visit[i] = true;
				backtracking(depth - 1, i, sum + map[node][i]);
				visit[i] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());
		K		= Integer.parseInt(st.nextToken());
		map		= new int[N][N];
		visit	= new boolean[N];
		
		for(int y=0; y<N; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) 
			{
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		// 플로이드 워셜로 모든 노드에서 모든 노드까지의 최단거리를 구함
		for(int k=0; k<N; k++) 
		{
			for(int y=0; y<N; y++) 
			{
				if(k==y) continue;
				for(int x=0; x<N; x++) 
				{
					if(k==x || y==x) 
						continue;
					if(map[y][x] > map[y][k] + map[k][x]) 
					{
						map[y][x] = map[y][k] + map[k][x];
					}
				}
			}
		}
		
		visit[K] = true;
		
		backtracking(N-1, K, 0);
		
		System.out.print(result);
	}
}