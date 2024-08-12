// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int a, c;
	Node(int a, int c){this.a=a;this.c=c;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.a-b.a);
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 도착시간
			int c = Integer.parseInt(st.nextToken()); // 검문시간
			pq.add(new Node(a,c));
		}
		
		int res = 0;
		for(int i=0; i<N; i++) 
		{
			Node now = pq.poll();
			res = Math.max(now.a, res);
			res += now.c;
		}
		System.out.print(res);
	}
}
