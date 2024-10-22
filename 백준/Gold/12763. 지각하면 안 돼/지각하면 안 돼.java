//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/12763
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int node, time, money;
	Node(int n, int t, int m){node=n; time=t; money=m;}
}
class Main{
	public static void main(String[] args)throws Exception{
		final int MAX = 100_000_000;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 건물의수(2<=100)
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T			= Integer.parseInt(st.nextToken());	// 제한 시간
		int M			= Integer.parseInt(st.nextToken());	// 제한 돈
		int time[]		= new int[N+1];
		int money[]		= new int[N+1];
		int res			= MAX;
		ArrayList<Node>[] adNode = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++)
		{
			time[i] = money[i] = MAX;
			adNode[i] = new ArrayList<>();
		}
		
		int R = Integer.parseInt(br.readLine());
		while(R-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	// 연결노드
			int b = Integer.parseInt(st.nextToken());	// 연결노드
			int t = Integer.parseInt(st.nextToken());	// 이동시간
			int m = Integer.parseInt(st.nextToken());	// 택시비
			adNode[a].add(new Node(b, t, m));			// 양방향 연결
			adNode[b].add(new Node(a, t, m));			// 양방향 연결
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.money - b.money);
		pq.add(new Node(1,0,0));
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();	// 현재 위치까지 오는데 든 총 이동시간과 총 택시비를 갖고 있다.
			
			if(now.node == N)
				res = Math.min(res, now.money);
			
			for(Node next : adNode[now.node])
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
		
		if(res == 100_000_000)
			res = -1;
		System.out.print(res);
	}
}