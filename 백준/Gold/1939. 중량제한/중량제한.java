//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1939
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int node;
	int weight;
	Node next;
	Node(int n, int w){
		this.node = n;
		this.weight = w;
	}
	Node(int n, int w, Node next){
		this.node = n;
		this.weight = w;
		this.next = next;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 섬개수(2<=만)
		int M = Integer.parseInt(st.nextToken());	// 다리개수 (1<=십만) 
		
		Node adNode[] = new Node[N+1]; // 각 섬당 연결된 다리를 담을 배열( 빠른 연산을 위해 별도 자료구조 생성 )

		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	// 섬(1<=N)
			int b = Integer.parseInt(st.nextToken());	// 섬(1<=N)
			int c = Integer.parseInt(st.nextToken());	// 중량(1<=십억)
			adNode[a] = new Node(b, c, adNode[a]);
			adNode[b] = new Node(a, c, adNode[b]);
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
			Node now = pq.poll(); // now.weight에는 해당 노드 까지 이동하는데 드는 최소 중량이 들어있다. 이 최소 중량은 weight[next.node] < minWeight 제한 조건에 의해 최대이다
			if(now.node == end)
			{
				System.out.print(now.weight);
				return;
			}
			for(Node next= adNode[now.node]; next != null; next=next.next)
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