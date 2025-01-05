//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/28018
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[1_000_002];
		
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr[s]++;
			arr[e+1]--;
		}
		
		for(int i=2; i<=1000000; i++)
			arr[i] += arr[i-1];
		
		int Q = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		while(Q-->0)
			sb.append(arr[Integer.parseInt(st.nextToken())]).append('\n');
		System.out.print(sb);
	}
}