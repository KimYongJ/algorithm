// https://github.com/kimyongj/algorithm
import java.util.ArrayList;
class Main{
	
	static int N, M;
	static ArrayList<Integer>[] adlist;
	static boolean visit[];
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void DFS(int idx, int cnt) {
		if(cnt == 5) {
			System.out.println(1);
			System.exit(0);
		}
		for(int x : adlist[idx]) {
			if(!visit[x]) {
				visit[x] = true;
				DFS(x,cnt+1);
				visit[x] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();	// 사람수
		M 		= read();	// 친구 관계 수
		visit	= new boolean[N];
		adlist 	= new ArrayList[N];
		
		for(int i=0; i<N; i++) {
			adlist[i] = new ArrayList<>();
		}
		
		int a,b;
		for(int i=0; i<M; i++) {
			a = read();
			b = read();
			adlist[a].add(b);
			adlist[b].add(a);
		}
		
		for(int i=0; i<N; i++) {
			visit[i] = true;
			DFS(i,1);
			visit[i] = false;
		}
		System.out.println(0);
	}
}
