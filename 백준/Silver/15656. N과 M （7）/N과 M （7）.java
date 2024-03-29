// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	static int N, M, arr[], result[];
	static StringBuilder sb;
	public static void Back(int depth) {
		if(depth == M) 
		{
			for(int i=0; i<M; i++)
				sb.append(result[i]).append(' ');
			sb.append('\n');
			return;
		}
		for(int i=0; i<N; i++) 
		{
			result[depth] = arr[i];
			Back(depth+1);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		sb 		= new StringBuilder();
		N 		= Integer.parseInt(st.nextToken());
		M 		= Integer.parseInt(st.nextToken());
		arr 	= new int[N];
		result 	= new int[M];
		st 		= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		Back(0);
		
		System.out.println(sb);
	}
}