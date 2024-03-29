// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, after, arr[], result[];
	static StringBuilder sb;
	public static void print() {
		for(int i=0; i<M; i++)
			sb.append(result[i]).append(' ');
		sb.append('\n');
	}
	public static void Back(int depth, int now) {
		if(depth == M) {
			print();
			return;
		}
		int before = -1;
		for(int i=now; i<N; i++) {
			if(arr[i] != before) {
				before = arr[i];
				result[depth] = arr[i];
				Back(depth+1, i+1);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[M];
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		Back(0,0);
		
		System.out.println(sb);
	}
}