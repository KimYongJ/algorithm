// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int		N, M, sum;
	static int 		arr[];
	static boolean 	visit[], result[];
	
	public static void DFS(int depth, int sum, int idx) {
		if(depth == 0) 
		{
			if(!visit[sum]) 
				result[sum] = visit[sum] = true;
			return;
		}
		
		for(int i=idx; i<N; i++) {
			DFS(depth-1, sum + arr[i], i+1);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder 	sb = new StringBuilder();
		N 	= Integer.parseInt(st.nextToken());
		M 	= Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}
		
		result 	 = new boolean[sum+1];
		visit 	 = new boolean[sum+1];
		visit[0] = visit[1] = true;
		
		//에라토스테네스의 체
		for(int i=2; i<=sum; i++) 
		{
			if(visit[i]) 
				continue;
			
			int num = i<<1;
			while(num <= sum) {
				visit[num] = true;
				num += i;
			}
		}
		
		DFS(M, 0, 0);
		
		for(int i=0; i<=sum; i++)
			if(result[i])
				sb.append(i).append(' ');
		
		if(sb.length() == 0)
			sb.append(-1);
		
		System.out.print(sb);
	}
}