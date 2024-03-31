// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, MAX, nextCnt, arr[][];
	public static void Back(int depth, int cnt) {
		if(depth == N) {
			MAX = Math.max(MAX, cnt);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(i==depth)  
				continue;
			
			if(arr[i][0] > 0 && arr[depth][0] > 0) {
				arr[i][0] 		-= arr[depth][1];
				arr[depth][0] 	-= arr[i][1];
				nextCnt 		=  cnt;
				if(arr[i][0]     <= 0)	nextCnt += 1;
				if(arr[depth][0] <= 0) 	nextCnt += 1;
				
				Back(depth+1, nextCnt);
				
				arr[i][0] 		+= arr[depth][1];
				arr[depth][0] 	+= arr[i][1];
			}else
				Back(depth+1, cnt);
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2]; // [N][0] 내구도, [N][1] 무게
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Back(0,0);
		
		System.out.println(MAX);
	}
}