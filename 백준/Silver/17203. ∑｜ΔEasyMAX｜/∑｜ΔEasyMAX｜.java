//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17203
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 노래 개수(1<=천)
		int Q		= Integer.parseInt(st.nextToken());	// 질의개수(1<=천)
		int arr[]	= new int[N+1];
		int diff[]	= new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=2; i<=N; i++)
			diff[i] = Math.abs((arr[i] - arr[i-1])) + diff[i-1];
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			sb.append(diff[r] - diff[l]).append('\n');
		}
		System.out.print(sb);
	}
}