// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static final char[] operator= {' ','+','-'};
	static int N;
	static char[] op;
	static StringBuilder sb = new StringBuilder();
	public static boolean check() {
		int sum 	= 1;
		int before 	= 1;
		for(int i=1, num = 2; i<N; i++, num++) 
        {
			if(op[i] == '+') 
            {
				sum 	+= num;
				before 	= num;
			}else if(op[i] == '-') 
            {
				sum 	-= num;
				before 	= -num;
			}else {
				int flag = num;
				if(before < 0) 
                {
					flag = -num;
				}
				sum 	+= before * 9 + flag;
				before 	= before * 10 + flag;
			}
		}
		return sum == 0;
	}
	public static void backtracking(int depth) {
		if(N == depth) 
		{
			if(check())     // 합이 0 인지 구함
            {
				for(int i=1; i<N; i++)
					sb.append(i).append(op[i]);
				sb.append(N).append('\n');
			}
			return;
		}
		for(char o : operator) 
		{
			op[depth] = o;
			backtracking(depth + 1);    // 모든 경우의 수를 대입
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0) 
		{
			N	= Integer.parseInt(br.readLine());
			op	= new char[N];
			backtracking(1);
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}