// https://github.com/kimyongj/algorithm
import java.util.HashSet;
class Main{
	
	static int 		N, now, next, num, cnt;
	static boolean 	visit[];
	static HashSet<Integer>[] list;
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void DFS(int node) {
		if(now == -1) {
			try {now = read();}
			catch(Exception e) {return;}
		}
		while(list[node].contains(now)) {
			if(!visit[now]) {
				visit[now] = true;
				next = now;
				now = -1;
				cnt ++;
				DFS(next);
			}else
				break;
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		visit 	= new boolean[N+1];
		list 	= new HashSet[N+1];
		num		= 1;
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
		if(1 == read()) {
			visit[1] = true;
			now = -1;
			cnt = 1;
			DFS(1);
		}
		System.out.print(cnt == N ? 1 : 0);
	}
}