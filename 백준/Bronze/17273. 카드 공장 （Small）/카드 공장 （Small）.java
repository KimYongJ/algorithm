//https://www.acmicpc.net/problem/17273
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int res[] = new int[N];
		int nums[][] = new int[N][2];
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			nums[i][0] = Integer.parseInt(st.nextToken());
			nums[i][1] = Integer.parseInt(st.nextToken());
			res[i] = nums[i][0];
		}
		
		while(M-->0)
		{
			int k = Integer.parseInt(br.readLine());
			
			for(int i=0; i<N; i++)
				if(res[i] <= k)
					res[i] = res[i] == nums[i][0] ? nums[i][1] : nums[i][0];
		}
		
		int sum = 0;
		
		for(int i=0; i<N; i++)
			sum += res[i];
		
		System.out.print(sum);
	}
}