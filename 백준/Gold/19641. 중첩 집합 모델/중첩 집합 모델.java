//https://www.acmicpc.net/problem/19641
//1초 1024MB
//3// 정점 수 (2<=100,000)
//2 1 3 -1 // 정점 번호가 먼저 주어지고, -1입력 전까지 해당 정점에 연결된 노드가 주어진다.
//3 2 -1
//1 2 -1
//2// 루트노드 번호
//출력 : 루트노드 부터 탐색을 시작하여 번호가 가장 낮은 노드부터 오름차순 방문해서 중첩 집합을 구성할 때, 각 노드의 번호 left 필드와 right 필드 출력
//1 2 3// 정점 수 만큼 LEFT필드와 RIGHT필드를 출력
//2 1 6
//3 4 5
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int idx;
	static int order[][];
	static int adNode[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		order = new int[N + 1][2];
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
		
		dfs(Integer.parseInt(br.readLine()), 0);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(i).append(' ').append(order[i][0])
				.append(' ').append(order[i][1]).append('\n');
		
		System.out.print(sb);
	}
	static void dfs(int now, int parent)
	{
		order[now][0] = ++idx;
		
		for(int next : adNode[now])
			if(next != parent)
				dfs(next, now);
		
		order[now][1] = ++idx;
	}
}