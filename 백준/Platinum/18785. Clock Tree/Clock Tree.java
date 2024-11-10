//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18785

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node{int node; Node next; Node(int n, Node t){node=n; next=t;}}

class Main{
	
	static int N;
	static int[] time, origin;
	static Node adNode[];
	public static void DFS(int node, int prevNode) {
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(next.node != prevNode)
			{
				++time[next.node];
				DFS(next.node, node);
				time[node] += (12 - time[next.node]);
			}
		
		++time[prevNode];
		
		time[node] %= 12;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		origin	= new int[N+1];
		adNode	= new Node[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			origin[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a		= Integer.parseInt(st.nextToken());
			int b		= Integer.parseInt(st.nextToken());
			adNode[a]	= new Node(b, adNode[a]);
			adNode[b]	= new Node(a, adNode[b]);
		}
		
		int cnt = 0;
		for(int i=1; i<=N; i++)
		{
			time = Arrays.copyOf(origin, N+1);
			
			DFS(i,0);
			
			if(time[i] == 0 || time[i] == 1)
				cnt++;
		}
		System.out.print(cnt);
	}
}