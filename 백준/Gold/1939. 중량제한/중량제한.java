//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1939
import java.util.PriorityQueue;
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
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		// 입력 부분
		int N			= read();			// 섬개수(2<=만)
		int M			= read();			// 다리개수 (1<=십만) 
		Node adNode[]	= new Node[N+1];	// 각 섬당 연결된 다리를 담을 배열( 빠른 연산을 위해 별도 자료구조 생성 )

		for(int i=0; i<M; i++)
		{
			int a = read();	// 섬(1<=N)
			int b = read();	// 섬(1<=N)
			int c = read();	// 중량(1<=십억)
			adNode[a] = new Node(b, c, adNode[a]);
			adNode[b] = new Node(a, c, adNode[b]);
		}
		
		// 변형된 다익스트라 부분
		int start		= read();
		int end			= read();
		int weight[]	= new int[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->b.weight-a.weight);
		pq.add(new Node(start, 1000000000));
		
		while(!pq.isEmpty())
		{
			// A -> B로 가는데 지나는 다리의 중량의 최소가 최대가 되도록 한다. 
			Node now = pq.poll();	// now.weight에는 해당 노드 까지 이동하는데 드는 최소이면서 최대인 중량이 들어있다. 이 now.weight은 weight[next.node] < minWeight 제한 조건에 의해 최대이다
			if(now.node == end)		// 우선순위 큐는 무게가 최대인것이 앞으로오기 때문에 end에 도착할 때가 최소 중량이 최대이다.
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