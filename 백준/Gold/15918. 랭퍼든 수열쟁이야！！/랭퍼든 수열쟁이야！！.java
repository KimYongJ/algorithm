// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, l, x, y;
	static int ans;
	static boolean M[];
	public static void backtracking(int num) {
		if(num == x){
			backtracking(num-1);
            return;
        }
		if(num == 0) 
		{
			ans++;
			return;
		}
		int plus = num+1;
		for(int i=0; plus+i<l; i++) {
			plus += i;
			if(!M[i] && !M[plus]) {
				M[i] = M[plus] = true;
				backtracking(num-1);
				M[i] = M[plus] = false;
			}
			plus -= i;
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		l		= N*2;
		x 		= Integer.parseInt(st.nextToken())-1;
		y 		= Integer.parseInt(st.nextToken())-1;
		M		= new boolean[l];
		M[x]	= M[y] = true;
		x		= y-x-1;
		
		backtracking(N);
		
		System.out.print(ans);
	}
}