//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2170
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int s,e; Node(int s,int e){this.s=s;this.e=e;}
}
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.s-b.s);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int s,e, res = 0;
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			pq.add(new Node(s,e));
		}
		Node now = pq.poll();
		s = now.s;
		e = now.e;
		while(!pq.isEmpty()) {
			now = pq.poll();
			if(now.s <= e) {
				e = Math.max(e, now.e);
			}else {
				res += e - s;
				s = now.s;
				e = now.e;
			}
		}
		System.out.print(res + e - s);
	}
}