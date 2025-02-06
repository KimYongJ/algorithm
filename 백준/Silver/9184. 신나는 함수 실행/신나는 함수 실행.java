//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9184
//1초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int dp[][][] = new int[101][101][101];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());// -50<=50
			int b = Integer.parseInt(st.nextToken());// -50<=50
			int c = Integer.parseInt(st.nextToken());// -50<=50
			if(a== -1 && b==-1 && c==-1)
				break;

			sb.append("w(").append(a).append(", ")
			.append(b).append(", ").append(c)
			.append(") = ").append(DFS(a+50,b+50,c+50)).append('\n');
		}
		System.out.print(sb);
	}
	public static int DFS(int a, int b, int c) {
		if(dp[a][b][c] != 0)
			return dp[a][b][c];

		if(a<=50 || b<=50 || c<=50)
			return dp[a][b][c] = 1;
		
		if(a>70 || b>70 || c>70)
			return dp[a][b][c] = DFS(70,70,70);
		
		if(a<b && b<c)
			return dp[a][b][c] = DFS(a,b,c-1) + DFS(a,b,c-1) - DFS(a, b-1, c);

		return dp[a][b][c] = DFS(a-1, b,c) + DFS(a-1,b-1,c) 
								+ DFS(a-1,b,c-1) - DFS(a-1,b-1,c-1); 
	}
}
