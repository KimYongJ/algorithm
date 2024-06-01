// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
	
	static final char[] operator= {' ','+','-'};
	static int N;
	static char[] op;
	static StringBuilder sb = new StringBuilder();
	public static boolean check() {
		int nums[] = new int[N];
		int idx = 0;
		nums[idx++] = 1;
		for(int i=1, num = 2; i<N; i++, num++) {
			if(op[i] == '+') {
				nums[idx++] = num;
			}else if(op[i] == '-') {
				nums[idx++] = -(num);
			}else {
				if(nums[idx-1] < 0)
					nums[idx-1] = nums[idx-1] * 10 - num;
				else
					nums[idx-1] = nums[idx-1] * 10 + num;
			}
		}
		return Arrays.stream(nums).sum() == 0;
	}
	public static void backtracking(int depth) {
		if(N == depth) 
		{
			if(check()) {
				for(int i=1; i<N; i++)
					sb.append(i).append(op[i]);
				sb.append(N).append('\n');
			}
			return;
		}
		for(char o : operator) 
		{
			op[depth] = o;
			backtracking(depth + 1);
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