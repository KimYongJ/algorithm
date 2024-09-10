//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10165
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int num, start, end;
	Node(int n, int s, int e){this.num=n; this.start=s; this.end=e;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());// 버스 정류장 개수 N(3<=10억)
		int M	= Integer.parseInt(br.readLine());// 노선수 M (2<=50십만)
		int MAX = 0;
		// 결과를 담을 우선순위 큐
		PriorityQueue<Integer> res = new PriorityQueue<Integer>((a,b)->a-b);
		// 시작기준 오름차순, 종료기준 내림차순 정렬
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.start!=b.start ? a.start-b.start : b.end-a.end);
		for(int i=1; i<=M; i++)
		{
			// 노선 s,e( 0<=s,e<=N-1 , s!=e)
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if(e < s) {
				MAX = Math.max(MAX, e);
				e += N;// 0을 지날경우 +N을 해주어 end를 큰수로 바꿔줌
			}
			pq.add(new Node(i, s, e));
		}		
		
		ArrayDeque<Node> q = new ArrayDeque<>();// 해당 큐에는 시작 노드에 따른 종료일자가 가장 긴것들이 담긴다.
		Node base = pq.poll();
		q.add(base);
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			if(now.end > base.end)	// 시작기준으로 오름차순 정렬되어있기 때문에, 큐에서 나온 데이터의 종료길이가 base보다 작거나 같다면 겹친다는것을 의미
			{
				base = now;			// 큐에서 나온데이터의 end가 더 클 때 교환해줌
				q.add(base);
			}
		}
		
		// 현재 위에서 담긴 q에는 시작 기준으로 오름차순 정렬이라 MAX보다 큐의 end가 작은 것들은 제거한다.
		while(!q.isEmpty())
		{
			base = q.poll();
			if(base.end > MAX)
				res.add(base.num);
		}
		
		StringBuilder sb = new StringBuilder();
		while(!res.isEmpty())
			sb.append(res.poll()).append(' ');
		
		System.out.print(sb.toString());
	}
}
