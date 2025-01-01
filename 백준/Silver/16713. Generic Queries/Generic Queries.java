//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16713
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 개수(1<=백만)
		int Q = Integer.parseInt(st.nextToken());	// 쿼리개수(1<=삼백만)
		int arr[] = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken()) ^ arr[i-1];
		
		int res = 0;
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			res ^= (arr[r] ^ arr[l-1]);
		}
		System.out.print(res);
	}
}