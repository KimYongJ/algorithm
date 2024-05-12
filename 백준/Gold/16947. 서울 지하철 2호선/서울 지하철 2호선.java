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
	
	static int N;
	static Node[] adNode;
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
	public static int BFS(int startNode) {
		ArrayDeque<Point> q = new ArrayDeque<>();
		cycleCheck = new boolean[N+1];
		cycleCheck[startNode] = true;
		q.add(new Point(startNode, 0));
		
		while(!q.isEmpty()) 
		{
			Point n = q.poll();
			for(Node now=adNode[n.node]; now!=null; now=now.next) 
			{
				if(cycleBase[now.node])
					return n.dist + 1;
				if(!cycleCheck[now.node]) 
				{
					cycleCheck[now.node] = true;
					q.add(new Point(now.node, n.dist + 1));
				}
			}
		}
		return 0;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N			= Integer.parseInt(br.readLine());
		adNode		= new Node[N+1];
		cycleBase	= new boolean[N+1];
		cycleCheck	= new boolean[N+1];
		int a,b;
		for(int i=0; i<N; i++)
		{
			st 			= new StringTokenizer(br.readLine());
			a 			= Integer.parseInt(st.nextToken());
			b 			= Integer.parseInt(st.nextToken());
			adNode[a] 	= new Node(b, adNode[a]);
			adNode[b] 	= new Node(a, adNode[b]);
		}
		
		// 사이클을 체크해서 cycleBase에 미리 저장해 놓는다.
		for(int i=1; i<=N; i++)
			if(!cycleBase[i]) 
			{
				cycleCheck = Arrays.copyOf(cycleBase, N + 1);
				cycleCheck[i] = true;
				if(check(i,i,i)) 
				{
					for(int j=1; j<=N; j++) 
						if(cycleCheck[j])
							cycleBase[j] = cycleCheck[j];
					break;
				}
			}
		
		StringBuilder sb = new StringBuilder();
		
		// BFS를 돌면서 사이클까지 가장 가까운 거리를 체킹
		for(int i=1; i<=N; i++) 
			sb.append(cycleBase[i] ? 0 : BFS(i)).append(' ');
	
		System.out.print(sb);
	}
	
}