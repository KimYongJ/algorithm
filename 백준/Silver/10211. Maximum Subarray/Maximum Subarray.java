//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10211
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			int N		= Integer.parseInt(br.readLine());
			int max		= -1000;
			int arr[]	= new int[N+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++)
			{
				arr[i] = Integer.parseInt(st.nextToken());
				if(0 < arr[i - 1])
					arr[i] += arr[i-1];
				
				max = Math.max(max, arr[i]);
			}
			
			sb.append(max).append('\n');
		}
		System.out.print(sb);
	}
}
