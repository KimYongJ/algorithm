// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int J, N, cnt, arr[];
		
		while(T-->0)
		{
			st	= new StringTokenizer(br.readLine());
			J	= Integer.parseInt(st.nextToken()); // 사탕 개수
			N	= Integer.parseInt(st.nextToken()); // 상자의 개수
			arr = new int[N];
			cnt = 0;
			
			for(int i=0; i<N; i++) 
			{
				st = new StringTokenizer(br.readLine());
				arr[i] = Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			while(N-->0 && J > 0) 
			{
				cnt++;
				J -= arr[N];
			}
			
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
	
}