//https://www.acmicpc.net/problem/22954
//2초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int edgeSize;
	static int size[];
	static int parent[];
	static boolean visit[];
	static List<Conn> adList[];

	// 최종 입력시 사용할 배열들
	static boolean node[][];// 해당 그룹에 포함된 노드들
	static boolean e[][];// 해당 그룹에 포함된 간선들
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 정점개수(1<=100,000)
		M = Integer.parseInt(st.nextToken());// 간선개수(0<=200,000)
		size = new int[2];
		parent = new int[N + 1];
		visit = new boolean[N + 1];
		adList = new ArrayList[N + 1];
		node = new boolean[2][N + 1];
		e = new boolean[2][M + 1];
		
		if(N <= 2)
		{
			System.out.print(-1);
			return;
		}
		
		for(int i=0; i<=N; i++)
		{
			parent[i] = i;
			adList[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int p1 = find(a);
			int p2 = find(b);
			
			if(p1 == p2)
				continue;
			
			if(parent[p1] < parent[p2])
				parent[p2] = p1;
			else
				parent[p1] = p2;
			
			edgeSize++;
			
			adList[a].add(new Conn(b, i));
			adList[b].add(new Conn(a, i));
		}
		
		int cnt = 0;
		
		for(int n = 1; n <= N; n++)
		{
			if(!visit[n])
			{
				
				if(cnt == 2)
				{
					cnt = 3;
					break;
				}
				
				visit[n] = true;
				node[cnt][n] = true;// 해당 노드 방문 마킹
				
				size[cnt] += dfs(n, cnt, -1);
				
				cnt++;
			}
		}
		// 컴포넌트가 3개이상이거나, 2개인데 사이즈 같다면 -1출력
		if(cnt == 3 || size[0] == size[1])
		{
			System.out.print(-1);
			return;
		}
		// 컴포넌트가 2개이면 바로 출력
		if(cnt > 1)
		{
			print();
			return;
		}
		// 이하는 컴포넌트가 무조건 1개인것, 리트노드만 하나로 자르고 나머지는 하나로 하면됨
		// 초기화
		for(int i=0; i<=N; i++) visit[i] = node[0][i] = node[1][i] = false;
		for(int i=0; i<=M; i++)e[0][i] = e[1][i] = false;
		
		// 리프노드를 가져옴
		int leaf = getLeaf();
		
		size[0] = edgeSize;// 첫번째 그룹 사이즈 세팅
		size[1] = 1;// 두번째 그룹 사이즈 세팅 1개 노드만 있음
		node[1][leaf] = true;
		
		
		for(int i=1; i<=N; i++)
		{
			if(!visit[i] && leaf != i)
			{
				dfs(i, 0, leaf);
				break;
			}
		}
		
		print();
	}
	static void print() {
		StringBuilder sb = new StringBuilder();
		sb.append(size[0]).append(' ').append(size[1]).append('\n');
		
		for(int r = 0; r<2; r++)
		{
			for(int i=0; i<=N; i++)
				if(node[r][i])
					sb.append(i).append(' ');
			
			sb.append('\n');
			
			for(int i=0; i<=M; i++)
				if(e[r][i])
					sb.append(i).append(' ');
			
			sb.append('\n');
		}
		System.out.print(sb);
	}
	static int getLeaf()
	{
		for(int i=0; i<=N; i++)
			if(adList[i].size() == 1)
				return i;
		
		return 0;
	}
	// 그래프를 셀 때 해당 노드와 간선을 미리 입력해 놓아야 한다.
	static int dfs(int now, int idx, int except)
	{
		int nodeCnt = 1;
		
		for(Conn next : adList[now])
		{
			if(visit[next.next] || next.next == except)
				continue;
			
			visit[next.next] =  
			node[idx][next.next] = 
			e[idx][next.edgeIdx] = true;
			
			nodeCnt += dfs(next.next, idx, except);
		}
		
		return nodeCnt;
	}
	static class Conn{
		int next, edgeIdx;
		Conn(int n, int i){
			next = n;
			edgeIdx = i;
		}
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}