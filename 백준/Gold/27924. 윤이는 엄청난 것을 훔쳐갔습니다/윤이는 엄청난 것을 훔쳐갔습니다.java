//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27924
import java.util.ArrayDeque;
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Person{
	boolean isCop; int node;
	Person(boolean i, int n){isCop=i;node=n;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N				= read();
		Node[] adNode		= new Node[N+1];	// 인접 노드
		boolean[] isLeaf	= new boolean[N+1];	// 해당 노드가 리프노드인지 체크
		boolean[] copVisit	= new boolean[N+1];	// 경찰의 방문 표시
		boolean[] runVisit	= new boolean[N+1];	// 도망자의 방문 표시

		for(int i=1; i<N; i++)
		{
			int a = read();
			int b = read();
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
			isLeaf[a] = !isLeaf[a];	// 리프노드인지 아닌지 담는다.
			isLeaf[b] = !isLeaf[b];	// 리프노드인지 아닌지 담는다.
		}

		int a = read();
		int b = read();
		int c = read();
		
		ArrayDeque<Person> q = new ArrayDeque<>();
		q.add(new Person(false, a));	// 큐에 도망자 부터 담는다.
		q.add(new Person(true, b));		// 그 후 경찰을 담음
		q.add(new Person(true, c));		// 그 후 경찰을 담음
		runVisit[a] = true;
		copVisit[b] = copVisit[c] = true;
		
		while(!q.isEmpty())
		{
			Person now = q.poll();
			if(!now.isCop)				// 도망자일 경우,
			{
				if(copVisit[now.node])	// 경찰이 도망자 위치에 방문했다면 연산하지 않음
					continue;
				if(isLeaf[now.node])	// 도망자가 리프 도착시 바로 종료
				{
					System.out.print("YES");
					return;
				}
			}
			for(Node next=adNode[now.node]; next!=null; next=next.next)
			{	// 경찰일 경우, 해당 위치에 경찰이 방문하지 않으면 큐에 넣는다.
				if(now.isCop && !copVisit[next.node])
				{
					copVisit[next.node] = true;
					q.add(new Person(true,  next.node));
				}
				// 도망자일 경우, 해당 위치가 도망자와 경찰이 방문하지 않을 때 넣는다.
				else if(!now.isCop && !copVisit[next.node]&& !runVisit[next.node])
				{
					runVisit[next.node] = true;
					q.add(new Person(false, next.node));
				}
			}
		}
		System.out.print("NO");
	}
}