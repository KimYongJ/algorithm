//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16948
import java.util.ArrayDeque;

class Node{int idx, cnt; Node(int i, int c){this.idx=i; this.cnt=c;}}

class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] arggs)throws Exception{
		int N				= read();	// 배열의 크기(1<=천)
		int map[]			= new int[N+1];
		boolean visit[]		= new boolean[N+1];
		ArrayDeque<Node> q	= new ArrayDeque<>();
		
		for(int i=1; i<=N; i++)
			map[i] = read(); // 0<=100
		
		visit[1] = true;
		q.add(new Node(1, 0));
		
		while(!q.isEmpty())
		{
			Node now	= q.poll();
			if(now.idx == N)
			{
				System.out.print(now.cnt);
				return;
			}
			int jump	= map[now.idx];
			int nextCnt = now.cnt + 1;
			for(int i=1; i<=jump; i++)
			{
				int nextIdx = now.idx+i;
				if(nextIdx <= N&&!visit[nextIdx])
				{
					visit[nextIdx] = true;
					q.add(new Node(nextIdx, nextCnt));
				}
			}
		}
		System.out.print(-1);
	}
}
