// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node = node;
		this.next = next;
	}
}
class Main{
	
	static int N, M, cnt, result;
	static boolean visit[];
	public static void DFS(int node, Node[] adNode) {
		if(cnt >= M) return;
		for(Node now=adNode[node]; now!=null && cnt < M; now=now.next) 
		{
			if(!visit[now.node]) 
			{
				cnt++;
				visit[now.node]= true; 
				DFS(now.node, adNode);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Node[] forward = new Node[N+1];
		Node[] reverse = new Node[N+1];
		int a,b;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			forward[a] = new Node(b, forward[a]); // 정방향
			reverse[b] = new Node(a, reverse[b]); // 역방향
		}
		M = (N+1) / 2; // 중간값
		for(int i=1; i<=N; i++) {
			cnt = 0;
			visit = new boolean[N+1];
			visit[i] = true;
			DFS(i, forward);
			if(cnt < M) {
				cnt = 0;
				visit = new boolean[N+1];
				visit[i] = true;
				DFS(i, reverse);
			}
			if(cnt >= M)
				result++;
		}
		System.out.println(result);
	}
}