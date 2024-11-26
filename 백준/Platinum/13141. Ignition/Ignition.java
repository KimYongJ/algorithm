//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13141
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node, dist; Node next;
	Node(int n, int d, Node nt){node=n; dist=d; next=nt;}
}

class Main{
	
	static final int MAX = 1<<30;
	static int		N, M;
	static double[][]dist;
	static Node[]	adNode;
	static double	MIN = MAX;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 2<=200
		M		= Integer.parseInt(st.nextToken());	// N-1<=20000
		adNode	= new Node[N+1];
		dist	= new double[N+1][N+1];

		// 플로이드워셜을 위한 기본 세팅
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				if(y!=x)
					dist[y][x] = MAX;
		// 기본 입력 받음과 동시에 플로이드워셜 실행할 배열 값 세팅
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a		= Integer.parseInt(st.nextToken());
			int b		= Integer.parseInt(st.nextToken());
			int l		= Integer.parseInt(st.nextToken());
			adNode[a]	= new Node(b, l, adNode[a]);
			adNode[b]	= new Node(a, l, adNode[b]);
			dist[a][b]	= Math.min(dist[a][b], l);
			dist[b][a]	= Math.min(dist[b][a], l);
		}
		// 플로이드워셜 실행
		for(int k=1; k<=N; k++)
			for(int i=1; i<=N; i++)
				for(int j=1; j<=N; j++)
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

		// 최단거리 계산
		for(int start=1; start<=N; start++)
		{
			double maxBurnTime = 0;
			for(int nowNode=1; nowNode<=N; nowNode++) // 변경
			{
				for(Node next=adNode[nowNode]; next!=null; next=next.next)
				{
					int nextNode	= next.node;
					double max		= Math.max(dist[start][nowNode], dist[start][nextNode]);
					double min		= Math.min(dist[start][nowNode], dist[start][nextNode]);
					double burnTime = max + ((min+ next.dist-max) / 2.0);
					maxBurnTime = Math.max(maxBurnTime, burnTime);
				}
			}
			MIN = Math.min(MIN, maxBurnTime);
		}
		
		
		
//		for(int i=1; i<=N; i++)
//		{
//			dijkstra(i);
//			
//			double maxDist = 0;
//			
//			for(int n=1; n<=N; n++)
//				maxDist = Math.max(dist[n], maxDist);
//			
//			for(int n=1; n<=N; n++)
//			{
//				double res = 0;
//				if(dist[n] == maxDist)
//				{
//					for(Node next=adNode[n]; next!=null; next=next.next)
//					{
//						double min = Math.min(dist[n], dist[next.node]);
//						double max = Math.max(dist[n], dist[next.node]);
//						res = Math.max(res, max + ((min + next.dist-max) / 2.0));
//					}
//					MIN = Math.min(MIN, res);
//				}
//			}
//		}
		
		System.out.printf("%.1f",MIN);
	}
//	public static void dijkstra(int start) {
//		pq		= new PriorityQueue<>((a,b)->a.dist-b.dist);
//		dist	= new int[N+1];
//		Arrays.fill(dist, MAX);
//		dist[start] = 0;
//		pq.add(new Point(start, 0));
//		
//		while(!pq.isEmpty())
//		{
//			Point now = pq.poll();
//			
//			for(Node next=adNode[now.node]; next!=null; next=next.next)
//			{
//				int nextDist = now.dist + next.dist;
//				if(nextDist < dist[next.node])
//				{
//					dist[next.node] = nextDist;
//					pq.add(new Point(next.node, nextDist));
//				}
//			}
//		}
//	}
}