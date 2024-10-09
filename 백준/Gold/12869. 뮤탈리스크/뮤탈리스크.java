//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12869
import java.util.ArrayDeque;
class Node{
	int a,b,c, cnt;Node(int a, int b, int c, int cnt){this.a=a;this.b=b;this.c=c;this.cnt=cnt;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		final int attack[][]	= {{9,3,1},{9,1,3},{1,3,9},{1,9,3},{3,1,9},{3,9,1}};
		ArrayDeque<Node> q		= new ArrayDeque<>();
		int N					= read();
		int e[]					= new int[3];
		boolean visit[][][]		= new boolean[61][61][61];
		
		for(int i=0; i<N; i++)
			e[i] = read();
		
		q.add(new Node(e[0], e[1], e[2], 0));
		
		visit[e[0]][e[1]][e[2]] = true;
		
		while(!q.isEmpty())
		{
			Node now = q.poll();
			if(now.a <= 0 && now.b<=0 && now.c<=0)
			{
				System.out.print(now.cnt);
				return;
			}
			int nextCnt = now.cnt + 1;
			for(int a[] : attack)
			{
				int nextA = Math.max(0, now.a - a[0]);
				int nextB = Math.max(0, now.b - a[1]);
				int nextC = Math.max(0, now.c - a[2]);
				if(!visit[nextA][nextB][nextC])
				{
					visit[nextA][nextB][nextC] = true;
					q.add(new Node(nextA, nextB, nextC, nextCnt));
				}
			}
			
		}
	}
}

