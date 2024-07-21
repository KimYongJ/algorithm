//https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node=node; this.next=next;
	}
}
class Main{
	
	static int N;
	static int arr[];
	static Node adNode[];
	static boolean visit[];
	public static int DFS(int node) {
		int cnt = 1;
		
		for(Node now=adNode[node]; now!=null; now=now.next) {
			if(visit[now.node]) {
				visit[now.node] = false;
				cnt += DFS(now.node);
			}
		}
		
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		StringBuilder sb	= new StringBuilder();
		N		= Integer.parseInt(st.nextToken());
		adNode	= new Node[N+1];
		
		int a,b;
		for(int i=1; i<N; i++) 
		{
			st	= new StringTokenizer(br.readLine());
			a	= Integer.parseInt(st.nextToken());
			b	= Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		int q = Integer.parseInt(br.readLine());
		while(q-->0) 
		{
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			arr = new int[a];
			visit = new boolean[N+1];
			for(int i=0; i<a; i++) 
			{
				b = Integer.parseInt(st.nextToken());
				arr[i] = b;
				visit[b] = true;
			}
			int res = 0;
			for(int start : arr) 
			{
				int cnt = 0;
				if(visit[start]) {
					visit[start] = false;
					cnt += DFS(start);
				}
				res += (cnt * (cnt-1)) / 2;
			}
			
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());		
	}
}