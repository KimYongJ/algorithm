// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static int		N, M, sum;
	static int 		arr[];
	static boolean 	visit[];
	static ArrayList<Integer> list = new ArrayList<>();
	
	public static void DFS(int depth, int sum, int idx) {
		if(depth == 0) {
			if(!visit[sum]) {
				list.add(sum);
				visit[sum] = true;
			}
			return;
		}
		
		for(int i=idx; i<N; i++) {
			DFS(depth-1, sum + arr[i], i+1);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}
		visit = new boolean[sum+1];
		visit[0] = visit[1] = true;
		
		//에라토스테네스의 체
		for(int i=2; i<=sum; i++) 
		{
			if(visit[i]) continue;
			
			int num = i+i;
			while(num <= sum) {
				visit[num] = true;
				num += i;
			}
		}
		
		DFS(M, 0, 0);
		
		if(list.size() == 0) {
			sb.append(-1);
		}else {
			Collections.sort(list);
			
			for(int l : list)
				sb.append(l).append(' ');
		}
		System.out.print(sb);
	}
}