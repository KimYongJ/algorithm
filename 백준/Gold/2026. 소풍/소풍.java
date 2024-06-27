// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;				// 최대인원수
	static int K;				// 소풍보낼 총 인원
	static int indegree[];		// 진출 차수 체크
	static boolean map[][];		// 친구관계를 담을 배열
	static boolean visit[];		// 방문 체크할 배열
	
	public static boolean validate(int node) {
		// 지금까지 방문한 노드와 모두 친구인지 ? 
		for(int i=1; i<=N; i++) 
		{
			if(visit[i] && !map[node][i]) 
			{
				return false;
			}
		}
		return true;
	}
	public static boolean DFS(int depth, int node) {
		if(depth == K) 
		{
			return true;
		}
		for(int i=1; i<=N; i++) 
		{
			if(!visit[i] && validate(i)) 
			{
				visit[i] = true;
				if(DFS(depth + 1, i))
					return true;
			}
		}
		return false;
	}

	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		StringBuilder sb	= new StringBuilder();
		K		= Integer.parseInt(st.nextToken());
		N		= Integer.parseInt(st.nextToken());
		map		= new boolean[N+1][N+1];
		indegree= new int[N+1];
		
		int a,b,F = Integer.parseInt(st.nextToken());
		for(int f=0; f<F; f++) 
		{
			st	= new StringTokenizer(br.readLine());
			a	= Integer.parseInt(st.nextToken());
			b	= Integer.parseInt(st.nextToken());
			map[a][b] = map[b][a] = true; // 친구관계 체킹
			indegree[a]++;
			indegree[b]++;
		}
		
		for(int i=1; i<=N; i++) 
		{
			if(indegree[i] < K-1) // 친구의 개수가 자기를 제외한 개수보다 커야 탐색한다. 
				continue;

			visit		= new boolean[N+1];
			visit[i]	= true;;
			if(DFS(1,i))
			{
				for(int v=1; v<=N; v++)
				{
					if(visit[v])
					{
						sb.append(v).append('\n');
					}
				}
				break;
			}
		}
		if(sb.length() == 0) 
		{
			sb.append(-1);
		}
		System.out.print(sb.toString());
	}
}