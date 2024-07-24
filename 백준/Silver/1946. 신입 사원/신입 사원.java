// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int a,b;
	Node(int a, int b){this.a=a; this.b=b;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.a-b.a);
			int N = Integer.parseInt(br.readLine());
			for(int i=0; i<N; i++)
			{
				st = new StringTokenizer(br.readLine());
				pq.add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			int cnt = 1;
			int score = pq.poll().b;
			for(int i=1; i<N; i++) {
				int b = pq.poll().b;
				if(b < score) {
					score = b;
					cnt++;
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}