//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/12763
import java.util.PriorityQueue;
class Node{
	int node, time, money;
	Node next;
	Node(int n, int t, int m){node=n; time=t; money=m;}
	Node(int n, int t, int m, Node nxt){node=n; time=t; money=m;next=nxt;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		final int MAX	= 100_000_000;
		int N			= read();	// 건물의수(2<=100)
		int T			= read();	// 제한 시간
		int M			= read();	// 제한 돈
		int time[]		= new int[N+1];
		int money[]		= new int[N+1];
		Node[] adNode	= new Node[N+1];
		
		for(int i=1; i<=N; i++)
			time[i] = money[i] = MAX;
		
		int R = read();
		while(R-->0)
		{
			int a = read();	// 연결노드
			int b = read();	// 연결노드
			int t = read();	// 이동시간
			int m = read();	// 택시비
			adNode[a] = new Node(b, t, m, adNode[a]);	// 양방향 연결
			adNode[b] = new Node(a, t, m, adNode[b]);	// 양방향 연결
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.money - b.money);
		pq.add(new Node(1,0,0));
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();	// 현재 위치까지 오는데 든 총 이동시간과 총 택시비를 갖고 있다.
			
			for(Node next=adNode[now.node]; next!=null; next=next.next)
			{
				int nextTime = now.time + next.time;		// 현재까지 오는데 든 시간과 다음 노드로 가는데 드는 시간을 합침
				int nextMoney= now.money + next.money;		// 현재까지 오는데 든 돈과 다음 노드까지 가는데 드는 돈을 합침
				if(T < nextTime || M < nextMoney)
					continue;
				
				if(nextMoney < money[next.node]) {
					money[next.node]= nextMoney;
					pq.add(new Node(next.node, nextTime, nextMoney));
				}
				else if(nextTime < time[next.node]) {
					time[next.node] = nextTime;
					pq.add(new Node(next.node, nextTime, nextMoney));
				}
			}
		}

		System.out.print(money[N] == MAX ? -1 : money[N]);
	}
}