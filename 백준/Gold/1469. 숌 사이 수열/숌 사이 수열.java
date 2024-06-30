// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int		len;
	static int		N;
	static int		base[];
	static int		result[];
	static boolean	visit[];

	public static boolean backtracking(int idx) {
		if(idx == len) 
		{
			return true;
		}
		if(result[idx] != -1) 
		{
			return backtracking(idx + 1);
		}
		
		for(int i=0; i<N; i++)
		{
			int nextIdx = idx + base[i] + 1;
			if(!visit[i] && nextIdx < len && result[nextIdx] == -1)
			{
				visit[i] = true;
				result[idx] = result[nextIdx] = base[i];
				if(backtracking(idx + 1))
					return true;
				result[idx] = result[nextIdx] = -1;
				visit[i] = false;
			}
		}

		return false;
	}

	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N		= Integer.parseInt(br.readLine());
		len		= N<<1;
		base	= new int[N];
		result	= new int[len];
		visit	= new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			base[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<len; i++) {
			result[i] = -1;
		}
		
		Arrays.sort(base);
		
		if( backtracking(0) ) 
		{
			StringBuilder sb = new StringBuilder();
			for(int r : result)
				sb.append(r).append(' ');
			System.out.print(sb);
		}
		else {
			System.out.print(-1);
		}
	}
}