// https://github.com/kimyongj/algorithm

import java.util.Arrays;

class Main{
	
	static int		len;
	static int		N;
	static int		base[];
	static int		result[];
	static boolean	visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}

	public static boolean backtracking(int idx) {
		if(idx == len) 
			return true;
		if(result[idx] != -1) 
			return backtracking(idx + 1);
		
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
		N		= read();
		len		= N<<1;
		base	= new int[N];
		result	= new int[len];
		visit	= new boolean[N];
		
		for(int i=0; i<N; i++) 
		{
			base[i] = read();
			result[i] = result[N+i] = -1;
		}
		
		Arrays.sort(base);
		
		if( backtracking(0) ) 
		{
			StringBuilder sb = new StringBuilder();
			for(int r : result)
				sb.append(r).append(' ');
			System.out.print(sb);
		}
		else
			System.out.print(-1);
	}
}