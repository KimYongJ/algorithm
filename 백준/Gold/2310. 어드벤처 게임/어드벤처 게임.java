//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	static Node adNode[];
	static int arr[];	// 각 방의 에너지 추가, 차감 정도를 나타냄
	static boolean visit[];
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		while(N != 0) 
		{
			arr		= new int[N+1];
			adNode	= new Node[N+1];
			visit	= new boolean[N+1];
			
			for(int i=1; i<=N; i++) 
			{
				st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				int cost = Integer.parseInt(st.nextToken());
				if(c == 'T') { // 트롤일 경우 음수로 변환
					arr[i] = -cost;
				}
				else {
					arr[i] = cost;
				}
				while(true) {
					int next = Integer.parseInt(st.nextToken());
					if(next == 0)
						break;
					adNode[i] = new Node(next, adNode[i]);
				}
			}
			visit[1] = true;
			sb.append(DFS(1, 0) ? "Yes" : "No")
				.append('\n');
			
			N = Integer.parseInt(br.readLine());
		}
		System.out.print(sb);
	}
	public static boolean DFS(int nowNode, int cost) {
		if(arr[nowNode] < 0) {
			cost += arr[nowNode];
		}
		else {
			cost = Math.max(arr[nowNode], cost);
		}
		if(cost < 0)
			return false;
		if(nowNode == N) 
			return true;
		for(Node now=adNode[nowNode]; now!=null; now=now.next) 
		{
			if(!visit[now.node]) {
				visit[now.node] = true;
				if(DFS(now.node, cost))
					return true;
				visit[now.node] = false;
			}
		}
		
		return false;
	}
}