//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/20010
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class AdNode{
	int adNode, dist;
	AdNode next;
	AdNode(int adNode, int dist, AdNode next){
		this.adNode = adNode;
		this.dist = dist;
		this.next = next;
	}
}
class Node{
	int a,b, d; Node(int a1, int b1, int d1){a=a1; b=b1; d=d1;}
}
class Main{
	
	static int maxNode, maxDist;
	static int parent[];
	static AdNode[] adNode;
	static boolean visit[];
	
	public static int getParent(int x) {
		if(parent[x] == x) return x;
		return parent[x] = getParent(parent[x]);
	}
	
	public static void union(int a, int b) {
		if(b < a) {
			int dummy = b;
			b = a;
			a = dummy;
		}
		parent[b] = a;
	}
	
	public static void DFS(int node, int dist) {
		if(maxDist < dist)
		{
			maxDist = dist;
			maxNode = node;
		}
		for(AdNode now=adNode[node]; now!=null; now=now.next)
			if(!visit[now.adNode])
			{
				visit[now.adNode] = true;
				DFS(now.adNode, dist + now.dist);
			}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());		// 마을 수(1<=1000)
		int T		= Integer.parseInt(st.nextToken());		// 설치 가능 교역로 수(1<=백만)
		int cnt		= 1;									// 간선의 개수
		int total	= 0;									// 스패닝트리의 간선들의 합
		adNode		= new AdNode[N];						// 인접 노드를 담을 배열		
		parent		= new int[N];							// 최소스패닝트리를 구하기 위해 사이클을 확인알 배열
		
		for(int i=1; i<N; i++)
			parent[i] = i;	// 자기자신으로 초기화
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.d-b.d);
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			pq.add(new Node(a,b,d));
		}
		
		while(cnt < N)
		{
			Node now	= pq.poll();
			int aParent = getParent(now.a);
			int bParent = getParent(now.b);
			
			if(aParent != bParent)
			{
				++cnt;
				total += now.d;
				adNode[now.a] = new AdNode(now.b, now.d, adNode[now.a]);
				adNode[now.b] = new AdNode(now.a, now.d, adNode[now.b]);
				union(aParent, bParent);
			}
		}
		// 가장 먼노드의 거리는 특정 점에서 가장 먼노드를 먼저 찾고, 그 구한 노드의 가장 먼노드를 찾으면 노드간 가장 먼 노드의 거리를 구할 수 있다.
		visit			= new boolean[N];
		visit[0]		= true;
		DFS(0, 0);
		
		visit			= new boolean[N];
		visit[maxNode]	= true;
		DFS(maxNode, 0);
		
		System.out.println(total);
		System.out.println(maxDist);
	}
}