// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Node{
	int a,b;
	Node(int a, int b){this.a=a; this.b=b;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0) {
			PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.a-b.a);
			int N = read();
			for(int i=0; i<N; i++)
			{
				pq.add(new Node(read(),read()));
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