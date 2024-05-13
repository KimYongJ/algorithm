// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
class Main{
	
	static int 		N, now, next, num;
	static boolean 	visit[];
	static StringTokenizer 		st;
	static HashSet<Integer>[] list;
	
	public static void DFS(int node) {
		if(now == -1) {
			if(!st.hasMoreTokens()) {
				return;
			}
			now = Integer.parseInt(st.nextToken());
		}
		while(list[node].contains(now)) {
			if(!visit[now]) {
				visit[now] = true;
				next = now;
				now = -1;
				DFS(next);
			}else
				break;
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		N 		= Integer.parseInt(br.readLine());
		visit 	= new boolean[N+1];
		list 	= new HashSet[N+1];
		num		= 1;
		for(int i=0; i<=N; i++)
			list[i] = new HashSet<>();
		
		int a,b;
		for(int i=1; i<N; i++) 
		{
			st	= new StringTokenizer(br.readLine());
			a 	= Integer.parseInt(st.nextToken());
			b 	= Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		st = new StringTokenizer(br.readLine());
		if(1 == Integer.parseInt(st.nextToken())) {
			visit[1] = true;
			now = -1;
			DFS(1);
		}
		for(int i=1; i<=N; i++)
			if(!visit[i]) 
			{
				num = 0;
				break;
			}
		System.out.print(num);
	}
}