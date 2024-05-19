// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node, dist;
	Node next;
	Node(int node, int dist, Node next){this.node=node; this.dist=dist; this.next=next;}
}
class Main{
	
	static int a, b, d, T, N, M, sum;
	static boolean visit[];
	static Node adNode[];
	// DFS는 자기와 부모와의 거리 대(:) 자기와 자기 자식 노드들의 거리의 합(그냥 합이아니라 최솟값의 합) 을 반환한다.
	// 원래 DFS 코드는 dp로 이해 하기 쉽게 작성했으나 빠른 실행을 위해 dp를 없애고 바로 반환토록 간소화 시킨 코드임
	public static int DFS(int now, int parentDist) {
		visit[now] = true;
		int sum = 0, cnt = 0;
		
		for(Node n=adNode[now]; n!=null; n=n.next)
			if(!visit[n.node]) 
			{
				sum += DFS(n.node, n.dist);
				cnt++;
			}

		if(now == 1)						// 루트 노드일 경우는 sum이 하위 노드들 중 짧은 거리의 합이기 때문에 바로 sum 리턴
			return sum;
		
		if(cnt == 0)						// 리프 노드일 경우는 부모와의 거리 그대로 리턴 
			return parentDist;
		
		return Math.min(sum, parentDist); 	// 중간 노드일 경우는, 자기 자신과 부모의 거리와 자기 자식들의 거리의 합 중 작은것 리턴 
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder 	sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			N 		= Integer.parseInt(st.nextToken());
			M 		= Integer.parseInt(st.nextToken());
			adNode 	= new Node[N+1];
			visit	= new boolean[N+1];
			sum		= 0;
			for(int i=0; i<M; i++) 
			{
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				adNode[a] = new Node(b,d,adNode[a]);
				adNode[b] = new Node(a,d,adNode[b]);
			}
			
			sb.append(	DFS(1,0)	)
				.append('\n');

		}
		System.out.print(sb);
	}
}