// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static int		len;
	static int		N;
	static int		base[];
	static int		result[];
	static boolean	visit[];
	static ArrayList<int[]> list = new ArrayList<>();

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
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N		= Integer.parseInt(br.readLine());
		len		= N<<1;
		base	= new int[N];
		result	= new int[len];
		visit	= new boolean[len];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			base[i] = Integer.parseInt(st.nextToken());
		}
		
		backtracking(0);
		
		if(list.size() == 0) 
		{
			System.out.print(-1);
		}
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