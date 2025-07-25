//https://www.acmicpc.net/problem/25515
//3초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static Node adNode[];
	static int N;
	static long value[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());// 노드 수(2<=십만)
		adNode = new Node[N];
		value = new long[N];
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)// 각 노드의 고유 값 (-십만<=십만)
			value[i] = Integer.parseInt(st.nextToken());
			
		System.out.print(dfs(0));
	}
	static long dfs(int now)
	{
		long val = value[now];
		
		for(Node next = adNode[now]; next != null; next=next.next)
		{
			long nextVal = dfs(next.node);
			
			if(nextVal >= 0)
				val += nextVal;
		}
		return val;
	}
	static class Node{
		int node;
		Node next;
		Node(int node, Node next){
			this.node = node;
			this.next = next;
		}
	}
}