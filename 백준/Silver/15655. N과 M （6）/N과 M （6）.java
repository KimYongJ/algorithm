// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int N, M, arr[], result[];
	static boolean visit[];
	static StringBuilder sb;
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
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
				back(depth+1,i+1);
				visit[i] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		arr 	= new int[N];
		result 	= new int[M];
		visit 	= new boolean[N];
		sb 		= new StringBuilder();
		for(int i=0; i<N; i++) 
			arr[i] = read();

		Arrays.sort(arr);
		
		back(0,0);
		
		System.out.println(sb);
	}
}