// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int		a, b, X;
	static String	str;
	static Node 	adNode[];
	static boolean 	flag, visit[];
	public static void DFS(int node) {
		if(node == b)
			flag = false;
		
		for(Node now=adNode[node]; flag && now!=null; now=now.next)
			if(!visit[now.node])
			{
				visit[now.node] = true;
				DFS(now.node);
			}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		adNode 	= new Node[26];
		X 		= Integer.parseInt(br.readLine());
		
		for(int i=0; i<X; i++) 
		{
			str 		= br.readLine();
			a 			= str.charAt(0) - 'a';
			b 			= str.charAt(5) - 'a';
			adNode[a]	= new Node(b, adNode[a]);
		}
		
		X = Integer.parseInt(br.readLine());
		for(int i=0; i<X; i++) 
		{
			str 		= br.readLine();
			a 			= str.charAt(0) - 'a';
			b 			= str.charAt(5) - 'a';
			visit 		= new boolean[26];
			visit[a]	= flag = true;
			DFS(a);
			sb.append(flag ? 'F' : 'T').append('\n');
		}
		
		System.out.print(sb);		
	}
}