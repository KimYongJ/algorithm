// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int N, sum, diff, pop[], area[];
	static boolean visit[];
	static ArrayList<Integer>[] adlist;
	public static int getSum() {
		int sum = pop[1];
		for(int i=2; i<=N; i++)
			if(area[1] == area[i])
				sum += pop[i];
		return sum;
	}
	public static void check(int node, int flag) {
		for(int next : adlist[node]) {
			if(!visit[next] && area[next] == flag) {
				visit[next] = true;
				check(next, flag);
			}
		}
	}
	public static boolean areaValidate() {
		int cnt = 0;
		visit	= new boolean[N+1];
		for(int i=1; i<=N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				check(i,area[i]);
				cnt++;
				if(cnt > 2) 
					return false;
			}
		}
		return cnt == 2;
	}
	public static void DFS(int depth) {
		if(depth > N) 
		{
			if(areaValidate()) {
				int a = getSum();
				int b = sum - a;
				diff = Math.min(diff, Math.abs(a-b));
			}
			return;
		}
		
		area[depth] = 1;
		DFS(depth + 1);
		
		area[depth] = 2;
		DFS(depth + 1);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N		= Integer.parseInt(br.readLine());
		pop 	= new int[N+1];
		area	= new int[N+1];
		diff 	= Integer.MAX_VALUE;
		adlist 	= new ArrayList[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			adlist[i] = new ArrayList<>();
			sum += pop[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = Integer.parseInt(st.nextToken());j>0; j--) {
				adlist[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		DFS(1);
		
		if(diff == Integer.MAX_VALUE)
			diff = -1;
		System.out.println(diff);
	}
}