// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

class Main{
	
	static int N, arr[];
	static StringBuilder sb;
	static boolean visit[];
	static Set<Integer> set, sub_set;
	public static void DFS(int start, int idx) {
		if(visit[idx] || set.contains(idx)) {
			if(idx != start)
				sub_set.clear();
			return;
		}
		visit[idx] = true;
		sub_set.add(idx);

		DFS(start, arr[idx]);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N 			= Integer.parseInt(br.readLine())+1;
		arr 		= new int[N];
		sb			= new StringBuilder();
		set			= new TreeSet<>();
		sub_set 	= new TreeSet<>();
		for(int i=1; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for(int i=1; i<N; i++) {
			visit = new boolean[N];
			DFS(i,i); // 방문하지 않은 경우만 반복
			if(sub_set.size() != 0) {
				set.addAll(sub_set);
				sub_set.clear();
			}
		}
		
		sb.append(set.size()).append('\n');
		for(Integer s : set) {
			sb.append(s).append('\n');
		}
		System.out.println(sb);
	}
}