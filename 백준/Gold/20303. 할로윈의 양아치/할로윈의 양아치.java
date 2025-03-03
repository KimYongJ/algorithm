//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20303
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node=node;
		this.next=next;
	}
}
class Main{
	
	static int N, M, K, cnt, candysum;
	static int[] candy;
	static Node[] adNode;
	static boolean[] visit;
	static ArrayList<Integer> childCnt = new ArrayList<>();
	static ArrayList<Integer> candyCnt = new ArrayList<>();
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());// 거리에 있는 아이들 수(1<=삼만)
		M		= Integer.parseInt(st.nextToken());// 아이들의 친구 관계수(0<=십만)
		K		= Integer.parseInt(st.nextToken())-1;// 뺏을 수 있는 아이수(1<=삼천)
		candy	= new int[N + 1];
		adNode	= new Node[N + 1];
		visit	= new boolean[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			candy[i] = Integer.parseInt(st.nextToken());
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		for(int i=1; i<=N; i++)
		{
			if(!visit[i])
			{
				cnt = 0;
				candysum = 0;
				visit[i] = true;
				DFS(i);
				childCnt.add(cnt);
				candyCnt.add(candysum);
			}
		}
		int len = childCnt.size();
		int dp[] = new int[K + 1];
		
		for(int i=1; i<=len; i++)
		{
			int child = childCnt.get(i-1);
			int candy = candyCnt.get(i-1);
			for(int j=K; j>=child; j--)
				dp[j] = Math.max(dp[j], dp[j-child] + candy);
		}
		
		System.out.print(dp[K]);
	}
	public static void DFS(int node) {
		cnt++;
		candysum += candy[node];
		for(Node next=adNode[node]; next!=null; next=next.next)
		{
			if(!visit[next.node])
			{
				visit[next.node] = true;
				DFS(next.node);
			}
		}
	}
}