// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static ArrayList<Integer>[] adlist;
	static boolean visit[];
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
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());	// 사람수
		M 		= Integer.parseInt(st.nextToken());	// 친구 관계 수
		visit	= new boolean[N];
		adlist 	= new ArrayList[N];
		
		for(int i=0; i<N; i++) {
			adlist[i] = new ArrayList<>();
		}
		
		int a,b;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
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
