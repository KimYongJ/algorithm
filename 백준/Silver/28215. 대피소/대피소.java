//https://www.acmicpc.net/problem/28215
//3초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, K;
	static int[][] point;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		point = new int[N][2];
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			point[i][0] = Integer.parseInt(st.nextToken());
			point[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int result = Integer.MAX_VALUE;
		
		if(K == 1)
		{
			for(int a=0; a<N-2; a++)
				result = Math.min(result, getCal(a,a,a));
		}
		else if(K == 2)
		{
			for(int a=0; a<N-2; a++)
				for(int b=a+1; b<N-1; b++)
					result = Math.min(result, getCal(a,b,b));
		}
		else if(K == 3)
		{
			for(int a=0; a<N-2; a++)
				for(int b=a+1; b<N-1; b++)
					for(int c=b+1; c<N; c++)
						result = Math.min(result, getCal(a,b,c));
		}
		
		System.out.print(result);
	}
	static int getCal(int a, int b, int c) {
		
		int result = 0;
		
		for(int i=0; i<N; i++)
		{
			if(i == a || i == b || i == c)
				continue;
			
			int m1 = Math.abs(point[i][0] - point[a][0]) + Math.abs(point[i][1] - point[a][1]);
			int m2 = Math.abs(point[i][0] - point[b][0]) + Math.abs(point[i][1] - point[b][1]);
			int m3 = Math.abs(point[i][0] - point[c][0]) + Math.abs(point[i][1] - point[c][1]);
			
			result = Math.max(result, Math.min(m1,Math.min(m2, m3)));
		}
		
		return result;
	}
}