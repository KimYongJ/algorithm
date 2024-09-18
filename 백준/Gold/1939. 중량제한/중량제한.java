//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1939
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int node, weight; Node(int n, int w){this.node = n; this.weight = w;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 섬개수(2<=만)
		int M = Integer.parseInt(st.nextToken());	// 다리개수 (1<=십만) 
		
		ArrayList<Node> adNode[] = new ArrayList[N+1]; // 각 섬당 연결된 다리를 담을 리스트
		for(int i=1; i<=N; i++)	// 리스트 초기화 
			adNode[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	// 섬(1<=N)
			int b = Integer.parseInt(st.nextToken());	// 섬(1<=N)
			int c = Integer.parseInt(st.nextToken());	// 중량(1<=십억)
			adNode[a].add(new Node(b,c));
			adNode[b].add(new Node(a,c));
		}
		st = new StringTokenizer(br.readLine());
		int start	= Integer.parseInt(st.nextToken());
		int end		= Integer.parseInt(st.nextToken());
		
		int weight[] = new int[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->b.weight-a.weight);
		pq.add(new Node(start, 1000000000));
		
		while(!pq.isEmpty())
		{
			// A -> B로 가는데 지나는 다리의 중량의 최소가 최대가 되도록 한다. 
			Node now = pq.poll(); // now에는 해당 노드 까지 이동하는데 드는 최소 중량이 들어있다.
			if(now.node == end) {
				System.out.print(now.weight);
				return;
			}
			for(Node next : adNode[now.node])
			{
				int minWeight = Math.min(now.weight, next.weight);// weight에 넣는 값을 구할 때는 최소를 구한다.
				if(weight[next.node] < minWeight) // weight[]에는 최대를 넣는다. 크지 않으면 못들어가게 해야한다.
				{
					weight[next.node] = minWeight;
					pq.add(new Node(next.node, minWeight));
				}
				
			}
		}
		
	}
}