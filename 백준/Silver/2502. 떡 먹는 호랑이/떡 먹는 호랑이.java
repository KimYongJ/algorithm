//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2502

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());	// 할머니가 넘어온 날(3<=30)
		int K = Integer.parseInt(st.nextToken());	// 이 날 준 떡개수(10<=십만)
		int dp[] = new int[D];
		dp[0] = K;
		for(int i=1; i<K; i++) {
			dp[1] = i;
			
			for(int j=2; j<D; j++) {
				int diff = dp[j-2] - dp[j-1];
				if(0 < diff)
					dp[j] = diff;
				else 
					break;
			}
			if(dp[D-1] != 0 && dp[D-1] < dp[D-2]) {
				System.out.println(dp[D-1]);
				System.out.println(dp[D-2]);
				break;
			}
		}
	}
}