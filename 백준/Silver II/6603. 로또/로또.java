// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int K, arr[], base[];
	static boolean visit[];
	static StringBuilder sb = new StringBuilder();
	public static void print() 
	{
		for(int i=0; i<6; i++)sb.append(base[i]).append(' ');
		sb.append('\n');
	}
	public static void DFS(int depth, int i) {
		if(depth == 6) {
			print();
			return;
		} 
		for(; i<K; i++) {
			if(!visit[i]) {
				visit[i] = true;
				base[depth] = arr[i];
				DFS(depth+1, i);
				visit[i] = false;
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		base = new int[6];
		while(true) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			
			if(K == 0) break; 			// k가 0인 경우 종료 
			arr 	= new int[K];			// 값을 담을 배열 생성
			visit 	= new boolean[K]; 	// DFS에서 방문체크
			
			for(int i=0; i<K; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			DFS(0,0);
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
}