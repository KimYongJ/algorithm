//https://www.acmicpc.net/problem/13344
//5초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int parent[];
	static boolean visit[];
	static boolean onStack[];
	static List<Node> list;
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());//선수 수 2<=50,000
		M		= Integer.parseInt(st.nextToken());//보고된 경기 수 1<=250,000
		parent	= new int[N];
		visit	= new boolean[N];
		onStack = new boolean[N];
		list	= new ArrayList<>();
		adList	= new ArrayList[N];
		
		for(int i=0; i<N; i++)
		{
			parent[i] = i;
			adList[i] = new ArrayList<>();
		}

		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			int b = Integer.parseInt(st.nextToken());
			
			if(c == '=')
			{
				union(a,b);
				continue;
			}
			else if(c == '<')
			{
				int tmp = b;
				b = a;
				a = tmp;
			}
			
			list.add(new Node(a, b));
		}
		
		for(Node now : list)
		{
			int p1 = find(now.big);
			int p2 = find(now.small);
			
			if(p1 == p2)
			{
				System.out.print("inconsistent");
				return;
			}
			
			adList[p1].add(p2);
		}
		
		int flag = 0;
		
		for(int i=0; i<N; i++)
		{
			if(!visit[i])
			{
				visit[i] = true;
				if(isCycle(i))
				{
					System.out.print("inconsistent");
					return;
				}
			}
		}

		System.out.print("consistent");
	}
	static boolean isCycle(int now)
	{
		onStack[now] = true;
		for(int next : adList[now])
		{
			if(onStack[next])
				return true;
			
			if(visit[next])
				continue;
			
			visit[next] = true;
			
			if(isCycle(next))
				return true;
		}
		onStack[now] = false;
		return false;
	}
	static void union(int a, int b) {
		int p1 = find(a);
		int p2 = find(b);
		
		if(p1 == p2)
			return;
		
		if(parent[p1] < parent[p2])
			parent[p2] = p1;
		else
			parent[p1] = p2;
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	static class Node {
		int big, small;
		Node(int b, int s){
			big = b;
			small = s;
		}
	}
}