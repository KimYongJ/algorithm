//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16395
//1초 / 256mb
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 1<=30
		int K		= Integer.parseInt(st.nextToken());	// 1<=30
		int arr[][] = new int[N+1][N+1];
		
		arr[1][1] = 1;
		
		for(int i=2; i<=N; i++)
			for(int j=1; j<=i; j++)
				arr[i][j] = arr[i-1][j] + arr[i-1][j-1];
		
		
		System.out.print(arr[N][K]);
	}
}