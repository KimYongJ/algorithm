// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, len, x, y;
	static int result;
	static boolean map[];
	public static void backtracking(int num) {
		if(num == x){
			backtracking(num-1);
            return;
        }
		if(num == 0) 
		{
			result++;
			return;
		}
		int plus = num+1;
		for(int i=0; plus+i<len; i++) {
			plus += i;
			if(!map[i] && !map[plus]) {
				map[i] = map[plus] = true;
				backtracking(num-1);
				map[i] = map[plus] = false;
			}
			plus -= i;
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		len		= N*2;
		x 		= Integer.parseInt(st.nextToken())-1;
		y 		= Integer.parseInt(st.nextToken())-1;
		map		= new boolean[len];
		map[x]	= map[y] = true;
		x		= y-x-1;
		
		backtracking(N);
		
		System.out.print(result);
	}
}