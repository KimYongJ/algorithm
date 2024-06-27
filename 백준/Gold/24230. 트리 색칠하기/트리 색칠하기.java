// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node = node;this.next = next;
	}
}
class Main{
	
	static int		result;
	static int		N;
	static int		color[];
	static boolean	visit[];
	static Node		adNode[];
	
	public static void DFS(int node, int beforeColor) {
		for(Node now=adNode[node]; now!=null; now=now.next) 
		{
			if(!visit[now.node]) 
			{
				visit[now.node] = true; 
				if(beforeColor != color[now.node])
				{
					result ++; 
				}
				DFS(now.node, color[now.node]);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N		= Integer.parseInt(br.readLine());
		color	= new int[N+1];
		visit	= new boolean[N+1];
		adNode	= new Node[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) 
		{
			color[i] = Integer.parseInt(st.nextToken());
		}
		
		int a,b;
		for(int i=1; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		// 루트 노드가 칠해져 있는 경우 시작 부터 칠해야 하니까 시작을 1로한다.
		if(color[1] != 0) 
		{
			result = 1;
		}
		visit[1] = true;
		DFS(1,color[1]);
		
		System.out.print(result);
	}
}