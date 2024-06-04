// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, len, idx1, idx2;
	static int map[];
	static int result;
	public static void backtracking(int num) {
		if(num == map[idx1])
			backtracking(num+1);
		if(N < num) 
		{
			result++;
			return;
		}
		int plus = num+1;
		for(int i=0; i<len; i++) {
			plus += i;
			if(plus < len && map[i] == 0 && map[plus] == 0) {
				map[i] = map[plus] = num;
				backtracking(num+1);
				map[i] = map[plus] = 0;
			}
			plus -= i;
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		len		= N*2;
		idx1 	= Integer.parseInt(st.nextToken())-1;
		idx2 	= Integer.parseInt(st.nextToken())-1;
		map		= new int[len];
		map[idx1] = map[idx2] = idx2 - idx1 - 1;
		
		backtracking(1);
		
		System.out.print(result);
	}
}