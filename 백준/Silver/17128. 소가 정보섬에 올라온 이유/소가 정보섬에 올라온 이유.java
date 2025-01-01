//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17128
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 소의개수	4<=이십만
		int Q		= Integer.parseInt(st.nextToken());	// 값 변경회수	1<=이십만
		long arr[]	= new long[N+3];
		long sum[]	= new long[N];
		long total	= 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		arr[N]	= arr[0];
		arr[N+1]= arr[1];
		arr[N+2]= arr[2];
		
		for(int idx=0, i=3; i<N+3; i++, idx++)
		{
			sum[idx] = 1;
			for(int s=i-3; s<=i; s++)
				sum[idx] *= arr[s];

			total += sum[idx];
		}
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		while(Q-->0)
		{
			int idx[] = getIdx(Integer.parseInt(st.nextToken()), N);
			
			for(int i : idx)
			{
				sum[i] = -sum[i];
				total += sum[i] + sum[i];
			}
			sb.append(total).append('\n');
		}
		System.out.print(sb);
	}
	public static int[] getIdx(int start,int N) {
		int idx[] = new int[4];
		for(int i=0; i<4; i++) {
			idx[i] = --start;
			if(idx[i] < 0)
				idx[i] = N + idx[i];
		}
		return idx;
	}
}