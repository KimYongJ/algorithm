//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 가장 확실한 것은 완전 탐색 , 시간이되는가 
// 노드가 100,000,000 
class Node{
	int node, dist;
	public Node(int node, int dist) {
		this.node = node; this.dist = dist;
	}
}

class Main{
	static int N, result;
	static boolean visit[];
	static ArrayList<Node>[] adlist;
	public static void DFS(int now, int sum) {
		if(!visit[now]) {
			visit[now] = true;
			if(result < sum)
				result = sum;
			
			for(Node next : adlist[now]) {
				DFS(next.node, sum + next.dist);
			}
			
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 노드의 개수
		adlist = new ArrayList[N+1];	// 인접 리스트
		
		for(int i=0; i<=N; i++) {
			adlist[i] = new ArrayList<>();
		}
		int a,b,c;
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adlist[a].add(new Node(b,c));
			adlist[b].add(new Node(a,c));
		}
		
		for(int i=1; i<=N; i++) {
			visit = new boolean[N+1];
			DFS(i, 0); // i노드 전체 탐색
		}
		
		System.out.println(result);
	}
}