// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int i=1; i<N; i++) {
			for(int j=N-i; j>0; j--) {
				sb.append(' ');
			}
			for(int j=0; j<i*2-1; j++) {
				sb.append('*');
			}
			sb.append('\n');
		}
		
		for(int i=0; i<N*2-1; i++)
			sb.append('*');
		sb.append('\n');
		
		for(int i=1; i<N; i++) {
			for(int j=0; j<i; j++) {
				sb.append(' ');
			}
			for(int j=0; j<(N-i)*2-1; j++) {
				sb.append('*');
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}