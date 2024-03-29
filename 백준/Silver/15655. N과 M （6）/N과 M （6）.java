// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int N, M, arr[], result[];
	static boolean visit[];
	static StringBuilder sb;
	public static void back(int depth,int now) {
		if(depth == M) {
			for(int i=0; i<M; i++)
				sb.append(result[i]).append(' ');
			sb.append('\n');
			return;
		}
		for(int i=now; i<N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				result[depth] = arr[i];
				back(depth+1,i);
				visit[i] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		M 		= Integer.parseInt(st.nextToken());
		arr 	= new int[N];
		result 	= new int[M];
		visit 	= new boolean[N];
		sb 		= new StringBuilder();
		st 		= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(arr);
		
		back(0,0);
		
		System.out.println(sb);
	}
}