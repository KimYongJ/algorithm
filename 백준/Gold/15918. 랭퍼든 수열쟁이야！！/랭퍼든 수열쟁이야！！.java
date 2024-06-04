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

		for(int i=0; num+1+i<l; i++)
			if(!M[i] && !M[num+1+i]) 
			{
				M[i] = M[num+1+i] = true;
				backtracking(num-1);
				M[i] = M[num+1+i] = false;
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