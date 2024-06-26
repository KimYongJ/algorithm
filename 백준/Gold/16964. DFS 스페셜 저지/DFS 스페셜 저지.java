// https://github.com/kimyongj/algorithm
import java.util.HashSet;
class Main{
	
	static int 		N, now, cnt;
	static boolean 	visit[];
	static HashSet<Integer>[] list;
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void DFS(int node) {
		if(visit[node]) {
			try {now = read();}
			catch(Exception e) {
				System.out.print(cnt == N ? 1 : 0);
				System.exit(0);
			}
		}
		while(!visit[now] && list[node].contains(now)) // 무한 반복으로 값을 순차 탐색 한다 
		{
			visit[now] 	= true;
			cnt ++;
			DFS(now);
		}
	}
	public static void main(String[] args)throws Exception{
			N 	= read();
		visit 	= new boolean[N+20];
		list 	= new HashSet[N+1];
		for(int i=0; i<=N; i++)
			list[i] = new HashSet<>();
		
		int a,b;
		for(int i=1; i<N; i++) 
		{
			a 	= read();
			b 	= read();
			list[a].add(b);
			list[b].add(a);
		}
		read();
		visit[1] = true;
		DFS(cnt = 1);
		
		System.out.print(cnt == N ? 1 : 0);
	}
}