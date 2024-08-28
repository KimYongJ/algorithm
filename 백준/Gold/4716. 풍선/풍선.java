// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/4716
import java.util.PriorityQueue;
class Node{
	int count, a, b, diff;
	Node(int cnt, int a, int b, int diff){
		this.count=cnt;	this.a=a;this.b=b;this.diff=diff;
	}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		while(true) 
		{
			int N = read(); // 팀수
			int A = read(); // A방 풍선 수
			int B = read(); // B방 풍선 수
			int result = 0; // 총 이동거리
			
			if(N==0 && A==0 && B==0)
				break;
			
			PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->b.diff-a.diff);
			for(int i=0; i<N; i++) 
			{
				int k = read();
				int a = read();
				int b = read();
				pq.add(new Node(k,a, b, Math.abs(a-b)));
			}
			
			while(!pq.isEmpty()) 
			{
				Node now = pq.poll();
				if(now.a > now.b) // b로가는게 더 이득일 때 
				{
					int balloon	= Math.min(B, now.count); // 최소로 없앨 수 있는 풍선 수
					B			-= balloon;
					now.count	-= balloon;
					result		+= balloon * now.b;
					if(now.count > 0) 
					{
						result	+= now.count * now.a;
						A		-= now.count;
					}
				}
				else // a로가는게 더 이득일 때
				{
					int balloon = Math.min(A, now.count); // 최소로 없앨 수 있는 풍선 수
					A			-= balloon;
					now.count	-= balloon;
					result		+= balloon * now.a;
					if(now.count > 0)
					{
						result	+= now.count * now.b;
						B		-= now.count;
					}
				}
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb.toString());
	}
}