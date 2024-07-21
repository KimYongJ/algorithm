//https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
	static int cnt[];
	static int arr[];
	static int parent[];
	static int treeParent[];
	static boolean v[];
	static Node adNode[];
	
	public static void makeParent(int node, int prev) {
		for(Node now=adNode[node]; now!=null; now=now.next) 
		{
			if(now.node != prev) 
			{
				treeParent[now.node]= node; 
				makeParent(now.node, node);
			}
		}
	}
	static int find(int node) {
		return parent[node] == node ? node : (parent[node] = find(parent[node]));
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		StringBuilder	sb	= new StringBuilder();
		N			= Integer.parseInt(st.nextToken());
		arr			= new int[N+1];
		cnt			= new int[N+1];
		parent		= new int[N+1];
		treeParent	= new int[N+1];
		v			= new boolean[N+1];
		adNode		= new Node[N+1];
		
		int a,b;
		for(int i=1; i<N; i++) 
		{
			st	= new StringTokenizer(br.readLine());
			a	= Integer.parseInt(st.nextToken());
			b	= Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		for(int i=0; i<=N; i++) {
			cnt[i] = 1;
			parent[i] = i;
		}
		
		makeParent(1,-1);
		
		int Q = Integer.parseInt(br.readLine());
		while(Q-->0)
		{
			st		= new StringTokenizer(br.readLine());
			long res = 0;
			int K	= Integer.parseInt(st.nextToken());
			
			for(int i=0; i<K; i++) 
			{
				arr[i]		= Integer.parseInt(st.nextToken());
				v[arr[i]]	= true;
			}

			for(int i=0; i<K; i++) 
			{
				if(v[treeParent[arr[i]]])
				{
					int parent1 = find(arr[i]);
					int parent2 = find(treeParent[arr[i]]);
					res += (long) cnt[parent1] * cnt[parent2];
					parent[parent1] = parent2;
					cnt[parent2] += cnt[parent1];
				}
			}
			
			for(int i=0; i<K; i++) 
			{
				v[arr[i]]		= false;
				cnt[arr[i]]		= 1;
				parent[arr[i]]	= arr[i];
			}

			sb.append(res).append('\n');
			
		}
		System.out.print(sb.toString());		
	}
}