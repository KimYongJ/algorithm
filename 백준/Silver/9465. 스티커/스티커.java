// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[2][N+2];
			int[][] DP  = new int[2][N+2];
			
			st = new StringTokenizer(br.readLine());
			for(int i=2; i<N+2;i++) 
				arr[0][i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=2; i<N+2; i++)
				arr[1][i] = Integer.parseInt(st.nextToken());
			
			for(int i=2; i<N+2; i++) {
				// DP 시작
				DP[0][i] = Math.max(DP[1][i-1], DP[1][i-2])+arr[0][i];
				DP[1][i] = Math.max(DP[0][i-1], DP[0][i-2])+arr[1][i];
			}
			sb.append(Math.max(DP[0][N+1], DP[1][N+1])).append('\n');

		}
		System.out.println(sb);
	}	

}