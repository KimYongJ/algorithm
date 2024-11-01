//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17199

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int cnt;
	static Node adNode[];
	static boolean visit[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		adNode	= new Node[N + 1];
		for(int i=1; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[b] = new Node(a, adNode[b]); // 간선을 반대로 받음 
		}
		
		for(int i=1; i<=N; i++)
		{
			visit = new boolean[N+1];
			cnt	= 0;
			DFS(i);
			if(cnt == N-1)
			{
				System.out.print(i);
				return;
			}
		}

		System.out.print(-1);
	}
	public static void DFS(int now) {
		for(Node next=adNode[now]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				visit[next.node]= true;
				cnt++;
				DFS(next.node);
			}
	}
}