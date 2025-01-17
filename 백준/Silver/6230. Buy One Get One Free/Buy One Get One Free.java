//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6230
// 1초 / 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 고품질 건초(1<=만)
		int M = Integer.parseInt(st.nextToken());	// 저품질 건초(1<=만);
		int a[] = new int[N];
		int b[] = new int[M];
		int res	= N;
		
		for(int i=0; i<N; i++)
			a[i] = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++)
			b[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(a);
		Arrays.sort(b);
		
		int bIdx = M-1;
		for(int i=N-1; i>=0; i--) {
			while(0<=bIdx && a[i] <= b[bIdx]) {
				--bIdx;
			}
			if(bIdx<0)
				break;
			++res;
			--bIdx;
		}
		
		System.out.print(res);
	}
}