// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, MAX, nextCnt, arr[][];
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void Back(int depth, int cnt) {
		if(depth == N) {
			MAX = Math.max(MAX, cnt);
			return;
		}
		if(arr[depth][0] <= 0 ||cnt==N-1) {
			Back(depth+1, cnt);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(i==depth || arr[i][0] <= 0)  
				continue;
			arr[i][0] 		-= arr[depth][1];
			arr[depth][0] 	-= arr[i][1];
			nextCnt 		=  0;
			if(arr[i][0]     <= 0)	nextCnt += 1;
			if(arr[depth][0] <= 0) 	nextCnt += 1;
			
			Back(depth+1, nextCnt+cnt);
			
			arr[i][0] 		+= arr[depth][1];
			arr[depth][0] 	+= arr[i][1];
		}
		
	}
	public static void main(String[] args)throws Exception{
		N = read();
		arr = new int[N][2]; // [N][0] 내구도, [N][1] 무게
		for(int i=0; i<N; i++) {
			arr[i][0] = read();
			arr[i][1] = read();
		}
		
		Back(0,0);
		
		System.out.println(MAX);
	}
}