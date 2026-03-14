//https://www.acmicpc.net/problem/9924
//1초 128

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int cnt = 0;
		while(B != 0 && A != B) {
			int nextA = Math.max(A, B) - Math.min(A, B);
			int nextB = Math.min(A, B);
			A = nextA;
			B = nextB;
			++cnt;
		}
		
		System.out.print(cnt);
	}
}