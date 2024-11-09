//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/10678
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Node{
	int node, dist; Node next;
	Node(int n, int d, Node t){node=n; dist=d; next=t;}
}
class Main{
	
	static int N, M;
	static Node[] adNode1, adNode2;
	static ArrayList<Integer> list1, list2;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());
		M		= Integer.parseInt(st.nextToken());
		adNode1	= new Node[N+1];
		adNode2	= new Node[N+1];
		list1	= new ArrayList<>();
		list2	= new ArrayList<>();
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a		= Integer.parseInt(st.nextToken());
			int b		= Integer.parseInt(st.nextToken());
			int c		= Integer.parseInt(st.nextToken());
			int d		= Integer.parseInt(st.nextToken());
			adNode1[a]	= new Node(b, c, adNode1[a]);
			adNode2[a]	= new Node(b, d, adNode2[a]);
		}
		
		DFS(1, 0, adNode1, list1);
		DFS(1, 0, adNode2, list2);
		
		Collections.sort(list1);
		Collections.sort(list2);
		
		for(int l1 : list1)
			for(int l2 : list2)
			{
				if(l1 == l2)
				{
					System.out.print(l1);
					return;
				}
				else if(l1 < l2)
					break;
			}
		
		System.out.print("IMPOSSIBLE");
	}
	public static void DFS(int node, int dist, Node adNode[], ArrayList<Integer> list) {
		if(node == N)
		{
			list.add(dist);
			return;
		}
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(node < next.node)
				DFS(next.node, dist + next.dist, adNode, list);

	}
}