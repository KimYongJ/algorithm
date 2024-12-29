//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12841
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N		= Integer.parseInt(br.readLine());
		long len[]	= new long[N+1];	// 횡단보도 길이(1<=십만)
		long left[] = new long[N+1];	// 왼쪽 길 거리(1<=십만)
		long right[]= new long[N+1];	// 왼쪽 길 거리(1<=십만)
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			len[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=2; i<=N; i++)
			left[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=2; i<=N; i++)
			right[i] = Integer.parseInt(st.nextToken());
		
		for(int i=2; i<=N; i++)
		{
			left[i] += left[i-1];
			right[i]+= right[i-1];
		}
		
		long min = 1L<<60;
		int idx = 0;
		
		for(int i=1; i<=N; i++) {
			long sum = left[i] + len[i] + right[N] - right[i];
			if(sum < min) {
				min = sum;
				idx = i;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(idx).append(' ').append(min);
		System.out.print(sb);
	}
}