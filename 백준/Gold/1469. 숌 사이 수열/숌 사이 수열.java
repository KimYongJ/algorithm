// https://github.com/kimyongj/algorithm

import java.util.ArrayList;
import java.util.Collections;

class Main{
	
	static int		len;
	static int		N;
	static int		base[];
	static int		result[];
	static boolean	visit[];
	static ArrayList<int[]> list = new ArrayList<>();

	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void backtracking(int depth) {
		if(depth == N) 
		{
			list.add(result.clone());
			return;
		}
		for(int i=0; i<len; i++)
		{
			int nextIdx = i + base[depth] + 1;
			if(!visit[i] && nextIdx < len && !visit[nextIdx])
			{
				visit[i] = visit[nextIdx] = true;
				result[i] = result[nextIdx] = base[depth];
				backtracking(depth + 1);
				visit[i] = visit[nextIdx] = false;
			}
		}
	}

	public static void main(String[] args)throws Exception{
		N		= read();
		len		= N<<1;
		base	= new int[N];
		result	= new int[len];
		visit	= new boolean[len];

		for(int i=0; i<N; i++) 
			base[i] = read();
		
		backtracking(0);
		
		if(list.size() == 0) 
			System.out.print(-1);
		
		else 
		{
			Collections.sort(list,(a,b)->{
				for(int i=0; i<len; i++) {
					if(a[i] < b[i])return -1;
					if(a[i] > b[i])return 1;
				}
				return 0;
			});
			StringBuilder sb = new StringBuilder();
			base = list.get(0);
			for(int b : base)
				sb.append(b).append(' ');
			System.out.print(sb);
		}
	}
}