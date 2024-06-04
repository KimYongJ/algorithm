// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, len, idx1, idx2;
	static int result;
	static boolean map[];
	public static void backtracking(int num) {
		if(num == idx2)
			backtracking(num-1);
		if(num == 0) 
		{
			result++;
			return;
		}
		for(int i=0; num+1+i<len; i++)
		{
			if(!map[i] && !map[num+1+i]) 
			{
				map[i] = map[num+1+i] = true;
				backtracking(num-1);
				map[i] = map[num+1+i] = false;
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		len		= N*2;
		idx1 	= Integer.parseInt(st.nextToken())-1;
		idx2 	= Integer.parseInt(st.nextToken())-1;
		map		= new boolean[len];
		map[idx1] = map[idx2] = true;
		idx2	-= idx1 + 1;
		
		backtracking(N);
		
		System.out.print(result);
	}
}