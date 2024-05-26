// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 		result = Integer.MAX_VALUE;
	static int 		map[];
	static boolean 	visit[];
	
	public static boolean check() {
		int base = map[0]+map[1]+map[2];
		if(	
			base != map[3]+map[4]+map[5] ||
			base != map[6]+map[7]+map[8] ||
			base != map[0]+map[3]+map[6] ||
			base != map[1]+map[4]+map[7] ||
			base != map[2]+map[5]+map[8] ||
			base != map[0]+map[4]+map[8] ||
			base != map[2]+map[4]+map[6]
				)
			return false;
		return true;
	}
	public static void DFS(int idx, int sum) {
		if(idx == 9) {
			if(check()) 
				result = Math.min(result, sum);
			return;
		}

		int tmp = map[idx];
		for(int i=1; i<=9; i++)			// 1부터 9까지 모두 써야하기 때문에
			if(!visit[i]) 
			{
				visit[i] = true;
				map[idx] = i;
				DFS(idx+1, sum + Math.abs(i-tmp));
				map[idx] = tmp;
				visit[i] = false;
			}
		
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map   = new int[10];
		visit = new boolean[10];
		
		int idx = 0;
		for(int i=0; i<3; i++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++)
				map[idx++] = Integer.parseInt(st.nextToken());
		}
		
		DFS(0,0);
		
		System.out.print(result);
	}
}