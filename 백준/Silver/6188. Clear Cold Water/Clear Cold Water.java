//https://www.acmicpc.net/problem/6188
//1초 128MB
//5 2 // 노드 개수, 주어지는 간선정보 개수
//3 5 4 // 부모노드번호, 자식노드 번호 2개
//1 2 3
//
//1 // i번째 노드에서 루트노드 까지의 거리 출력
//2
//2
//3
//3
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, C;
	static int cnt[];
	static Node adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cnt = new int[N + 1];
		adList = new Node[N + 1];
		
		for(int i=0; i<C; i++)
		{
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			adList[r] = new Node(c1, adList[r]);
			adList[r] = new Node(c2, adList[r]);
		}
		
		dfs(1, 0, 1);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(cnt[i]).append('\n');
		
		System.out.print(sb);
	}
	static void dfs(int now, int prev, int depth) {
		
		cnt[now] = depth;
		
		for(Node next = adList[now]; next != null; next=next.next)
			dfs(next.node, now, depth + 1);
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