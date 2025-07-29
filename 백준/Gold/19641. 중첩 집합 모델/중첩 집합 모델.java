import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int idx;
	static int order[][];
	static int adNode[][];
	static boolean visit[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		order = new int[N + 1][2];
		visit = new boolean[N + 1];
		adNode = new int[N + 1][];
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int len = st.countTokens() - 1;
			adNode[a] = new int[len];
			
			for(int j=0; j<len; j++)
				adNode[a][j] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(adNode[a]);
		}
		
		int start = Integer.parseInt(br.readLine());
		
		visit[start] = true;
		dfs(start);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(i).append(' ').append(order[i][0])
				.append(' ').append(order[i][1]).append('\n');
		
		System.out.print(sb);
	}
	static void dfs(int now) {
		order[now][0] = ++idx;
		
		for(int next : adNode[now])
		{
			if(!visit[next])
			{
				visit[next] = true;
				dfs(next);
			}
		}
		
		order[now][1] = ++idx;
	}
}