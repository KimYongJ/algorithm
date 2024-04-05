// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node{
	int node;  long money, maxMoney;
	Node(int node, long money, long maxMoney){
		this.node=node; this.money=money;
		this.maxMoney = maxMoney;
	}
}
public class Main {
	static long MAX = 10_000_000_000L;
	static long money, maxCost[], cost[];
	static int N, M, start, end;
	static boolean visit[];
	static PriorityQueue<Node> pq;
	static ArrayList<Node>[] adlist;
	public static void Dijkstra() {
		maxCost[start] = 0;					// start까지 가는데 든 지불비용 0
		pq.add(new Node(start, money,0));	// start 노드에서 money 만큼 갖고 시작
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(visit[now.node])continue;
			visit[now.node]= true;
			
			for(Node next : adlist[now.node]) 
			{
				long nextMaxMoney = Math.max(now.maxMoney,next.money); // 해당 노드까지 방문하는데 든 가장 큰 지불비용 삽입
				long nextCost = now.money - next.money;
				if(nextCost >= 0) 										// 돈을 지불해야 하기에 현재 갖고있는 돈이 지불할 비용(next.money)보다 커야함
				{
					if(maxCost[next.node]== nextMaxMoney ) {
						if(cost[next.node] < nextCost) {
							cost[next.node]= nextCost;
							pq.add(new Node(next.node, cost[next.node], nextMaxMoney));
						}
					}else if(maxCost[next.node] > nextMaxMoney) 	// 가려는 노드까지 도달할 때 드는 지불비용이 최소여야 다음 노드로 갈 수 있음
					{
						maxCost[next.node]= nextMaxMoney;
						cost[next.node] =  nextCost;
						pq.add(new Node(next.node, cost[next.node], nextMaxMoney));
					}
				}
				
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());			// 노드의 총개수
		M 		= Integer.parseInt(st.nextToken());			// 간선 개수
		start 	= Integer.parseInt(st.nextToken());			// 시작 노드
		end 	= Integer.parseInt(st.nextToken());			// 종료 노드
		money 	= Long.parseLong(st.nextToken());			// 처음 주어지는 돈
		cost	= new long[N+1];							// 노드를 index로 하여 각 노드당 남은 돈을 기록한다.
		maxCost	= new long[N+1];							// 해당 노드 까지 가는데 든 가장 적은 지불비용
		visit	= new boolean[N+1];							// 다익스트라 실행시 방문한 노드 체킹
		adlist	= new ArrayList[N+1];
		pq		= new PriorityQueue<Node>((a,b)->Long.compare(a.money, b.money));
		for(int i=0; i<=N; i++) {
			maxCost[i] 	= MAX;
			adlist[i]	= new ArrayList<>();
		}
		
		int a,b; long c;
		for(int i=0; i<M; i++) {
			st	= new StringTokenizer(br.readLine());
			a 	= Integer.parseInt(st.nextToken());
			b 	= Integer.parseInt(st.nextToken());
			c 	= Long.parseLong(st.nextToken());
			adlist[a].add(new Node(b,c,0));
			adlist[b].add(new Node(a,c,0));
		}
		
		Dijkstra();
		
		long result = maxCost[end];
		if(result == MAX)
			result = -1;
		System.out.println(result);
	}
}