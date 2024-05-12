//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Point{
	int node, dist;
	Point(int node, int dist){this.node=node; this.dist=dist;}
}
class Main{
	
	static int N, dist[];
	static Node[] adNode;
	static ArrayDeque<Point> q;
	static boolean cycleBase[], cycleCheck[];
	public static boolean check(int base, int nowNode, int beforeNode) {
		for(Node now=adNode[nowNode]; now!=null; now=now.next) {
			if(cycleCheck[now.node] && now.node == base && beforeNode !=now.node)
				return true;
			else if(!cycleCheck[now.node]) 
			{
				cycleCheck[now.node] = true;
				if(check(base,now.node,nowNode))
					return true;
				cycleCheck[now.node] = false;
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N			= Integer.parseInt(br.readLine());
		dist		= new int[N+1];
		adNode		= new Node[N+1];
		cycleBase	= new boolean[N+1];
		cycleCheck	= new boolean[N+1];
		q 			= new ArrayDeque<>();
		
		int a,b;
		for(int i=0; i<N; i++)
		{
			st 			= new StringTokenizer(br.readLine());
			a 			= Integer.parseInt(st.nextToken());
			b 			= Integer.parseInt(st.nextToken());
			adNode[a] 	= new Node(b, adNode[a]);
			adNode[b] 	= new Node(a, adNode[b]);
		}
		
		// 사이클을 체크해서 cycleBase에 미리 저장해 놓는다. 저장할 때 큐에도 동시 저장
		for(int i=1; i<=N; i++)
			if(!cycleBase[i]) 
			{
				cycleCheck = Arrays.copyOf(cycleBase, N + 1);
				cycleCheck[i] = true;
				if(check(i,i,i)) 
				{
					for(int j=1; j<=N; j++) 
						if(cycleCheck[j]) 
						{
							cycleBase[j] = cycleCheck[j];
							q.add(new Point(j,0));
						}
					break; // 사이클이 하나만 존재한다는 가정하에 break, 사이클이 여러개면 break을 하면 안됨
				}
			}

		// 큐에는 사이클이 담겨있고, 사이클에서 사이클이 아닌 노드들까지의 거리를 계산해 dist에 담는다.
		while(!q.isEmpty()) {
			Point now = q.poll();
			for(Node next=adNode[now.node]; next!=null; next=next.next) {
				if(!cycleBase[next.node]) {
					cycleBase[next.node] = true;
					q.add(new Point(next.node, dist[next.node] = now.dist + 1));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(dist[i]).append(' ');
		System.out.print(sb);
	}
	
}