// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int dl, cnt; Node(int dl, int cnt){this.dl=dl;this.cnt=cnt;}
}
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> stored = new PriorityQueue<Node>((a,b)->a.cnt-b.cnt);
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.dl != b.dl ? a.dl-b.dl : b.cnt-a.cnt);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int res = 0;
		boolean visit[] = new boolean[N+1];
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			pq.add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		for(int i=1; i<=N; i++) // 시간마다 탐색 
		{
			while(!pq.isEmpty() && pq.peek().dl == i) // 해당 시간까지 데드라인인 객체들은 모두 꺼낸다.
			{
				Node now = pq.poll();
				if(!visit[i]) // 방문을 하지 않았다면 
				{
					visit[i] = true;
					res += now.cnt;
					stored.add(now);
				}
				else if(now.cnt > stored.peek().cnt)// 방문 했고, 현재 객체의 라면개수가 그동안 결과에 담은 라면 개수보다 많다면 
				{
					res -= stored.poll().cnt;
					res += now.cnt;
					stored.add(now);
				}
			}
			if(!visit[i]) {
				visit[i] = true;
				if(!pq.isEmpty()) {
					Node now = pq.poll();
					res += now.cnt;
					stored.add(now);
				}
			}
		}
		System.out.print(res);
	}
}