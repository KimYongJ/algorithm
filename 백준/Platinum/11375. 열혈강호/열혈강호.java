//https://github.com/KimyongJ/algorithm
//https://www.acmicpc.net/problem/11375
//2초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	 
	static int N, M;
	static int[] job;
	static boolean[] visit;
	static Node[] adNode;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adNode = new Node[N + 1];
		job = new int[M + 1];
		
		for(int i=1; i<=N; i ++)
		{
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken());
			while(j-->0)
				adNode[i] = new Node(Integer.parseInt(st.nextToken()), adNode[i]);
		}
		
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			visit = new boolean[M + 1];
			if(dfs(i))
				++cnt;
		}
		System.out.print(cnt);
	}
	static boolean dfs(int node) {
		for(Node next=adNode[node]; next != null; next=next.next)
		{
			if(visit[next.node])
				continue;
			
			visit[next.node] = true; 
			
			if(job[next.node] == 0 || dfs(job[next.node]))
			{
				job[next.node] = node; 
				return true;
			}
		}
		return false;
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
//5 5		// 직원 수(1<=1,000), 일의 수(1<=1,000)
//2 1 2	// 각 직원이 할 수 있는 일의 번호
//1 1
//2 2 3
//3 3 4 5
//1 1
//// 답
//4


