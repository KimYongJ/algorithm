//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11969
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 소의 숫자(1<=십만)
		int Q = Integer.parseInt(st.nextToken());	// 질의 개수(1<=십만)
		int arr[][] = new int[4][N+1];
		
		for(int i=1; i<=N; i++)
		{
			arr[Integer.parseInt(br.readLine())][i] = 1;
			arr[1][i] += arr[1][i-1];
			arr[2][i] += arr[2][i-1];
			arr[3][i] += arr[3][i-1];
		}
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			sb.append(arr[1][r] - arr[1][l-1]).append(' ')
			.append(arr[2][r] - arr[2][l-1]).append(' ')
			.append(arr[3][r] - arr[3][l-1]).append('\n');
		}
		System.out.print(sb);
	}
}