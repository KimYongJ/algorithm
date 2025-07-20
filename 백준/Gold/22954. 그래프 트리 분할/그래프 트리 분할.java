//https://www.acmicpc.net/problem/22954
//2초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, gSize;
	static int parent[];
	static List<Node> edge;
	static List<Conn> adList[];
	static boolean visit[];
	// 최종 입력시 사용할 배열들
	static boolean node[][];
	static boolean e[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 정점개수(1<=100,000)
		M = Integer.parseInt(st.nextToken());// 간선개수(0<=200,000)
		parent = new int[N + 1];
		visit = new boolean[N + 1];
		edge = new ArrayList<>();
		adList = new ArrayList[N + 1];
		node = new boolean[3][N + 1];
		e = new boolean[3][M + 1];
		
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
			
			edge.add(new Node(a,b,i));
			
			adList[a].add(new Conn(b, i));
			adList[b].add(new Conn(a, i));
		}
		
		int cnt = 0;
		int size[] = new int[2];
		
		for(int n = 1; n <= N; n++)
		{
			if(!visit[n])
			{
				
				if(cnt == 2)
				{
					System.out.print(-1);
					return;
				}
				gSize = 1;
				visit[n] = true;
				node[cnt][n] = true;// 해당 노드 방문 마킹
				dfs(n, cnt);
				
				size[cnt] = gSize;
				cnt++;
			}
		}
		
		if(size[0] == size[1])
		{
			System.out.print(-1);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(cnt == 2)
		{
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
			return;
		}
		
		int leaf = 0;
		
		for(int i=0; i<=N; i++)
		{
			if(adList[i].size() == 1)
			{
				leaf = i;
				break;
			}
		}
		
		

		sb.append(1).append(' ').append(edge.size()).append('\n');
		sb.append(leaf).append('\n');
		
		Arrays.fill(node[0], false);
		Arrays.fill(e[0], false);
		
		for(int i=0; i<edge.size(); i++)
		{
			Node now = edge.get(i);
			
			if(now.a == leaf || now.b == leaf)
				continue;
			
			node[0][now.a] = node[0][now.b]= true;
			e[0][now.idx]= true; 
		}
		
		for(int i=0; i<=N; i++)
			if(node[0][i])
				sb.append(i).append(' ');
		
		sb.append('\n');
		
		for(int i=0; i<=M; i++)
			if(e[0][i])
				sb.append(i).append(' ');

		System.out.print(sb);
	}
	// 그래프를 셀 때 해당 노드와 간선을 미리 입력해 놓아야 한다.
	static void dfs(int now, int idx)
	{
		for(Conn next : adList[now])
		{
			if(visit[next.next])
				continue;
			
			gSize++;
			visit[next.next] = true; 
			node[idx][next.next] = true;
			e[idx][next.edgeIdx] = true;
			
			dfs(next.next, idx);
		}
	}
	static class Conn{
		int next, edgeIdx;
		Conn(int n, int i){
			next = n;
			edgeIdx = i;
		}
	}
	static class Node{
		int a,b, idx;
		Node(int a, int b, int idx){
			this.a=a;
			this.b=b;
			this.idx=idx;
		}
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}