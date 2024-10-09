//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12869
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Node{
	int a,b,c, cnt;Node(int a, int b, int c, int cnt){this.a=a;this.b=b;this.c=c;this.cnt=cnt;}
}
class Main{
	public static void main(String[] args)throws Exception{
		final int attack[][] = {{9,3,1},{9,1,3},{1,3,9},{1,9,3},{3,1,9},{3,9,1}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Node> q = new ArrayDeque<>();
		int e[] = new int[3];
		boolean visit[][][] = new boolean[61][61][61];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			e[i] = Integer.parseInt(st.nextToken());
		
		q.add(new Node(e[0], e[1], e[2], 0));
		
		visit[e[0]][e[1]][e[2]] = true;
		
		while(!q.isEmpty())
		{
			Node now = q.poll();
			if(now.a <= 0 && now.b<=0 && now.c<=0) {
				System.out.print(now.cnt);
				return;
			}
			int nextCnt = now.cnt + 1;
			for(int a[] : attack) {
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

