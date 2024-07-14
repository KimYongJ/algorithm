// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node{
	int n; double a;
	Node(int n, double a){this.n=n; this.a=a;}
}
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq	= new PriorityQueue<Node>((a,b)->Double.compare(b.a,a.a));
		BufferedReader	br		= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st		= new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 참가인원
		int M = Integer.parseInt(st.nextToken()); // 장르 개수
		int K = Integer.parseInt(st.nextToken()); // 본선에 나갈 인원
		int n;
		double a, ans = 0.0;
		boolean visit[] = new boolean[N+1];
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) 
			{
				n = Integer.parseInt(st.nextToken()); // 참가번호
				a = Double.parseDouble(st.nextToken());// 능력치
				pq.add(new Node(n,a));
			}
		}
		while(K-->0)
		{
			while(true)
			{
				Node node = pq.poll();
				if(!visit[node.n]) 
				{
					visit[node.n] = true;
					ans += node.a;
					break;
				}
			}
			
		}
		
		System.out.print(String.format("%.1f", ans));
	}
}