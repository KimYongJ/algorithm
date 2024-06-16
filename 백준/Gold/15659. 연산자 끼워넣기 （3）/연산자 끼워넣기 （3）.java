// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int	max = Integer.MIN_VALUE;
	static int	min = Integer.MAX_VALUE;
	static int	numbers[];
	static int	operator[];
	static int	len;
	
	public static void cal(boolean flag) {
		ArrayList<Integer> number 	= new ArrayList<>();
		ArrayList<Integer> oper		= new ArrayList<>();
		
		number.add(numbers[0]);
		
		for(int i=0; i<len-1; i++) 
		{
			if(operator[i] != 1) 
			{
				if(operator[i] == 2) 
				{
					number.add(number.remove(number.size()-1) * numbers[i+1]);
				}
				else 
				{
					number.add(number.remove(number.size()-1) / numbers[i+1]);
				}
			}
			else
				number.add(numbers[i+1]);
		}
		
		int sum = number.stream().reduce(0, (a,b)-> a+ b);
		
		max = Math.max(max, sum);
		min = Math.min(min, sum);
	}
	public static void backtracking(int depth, int plus, int minus, int mul, int div, boolean flag) {
		if(depth < 0) 
		{
			cal(flag);
			return;
		}
		if(plus > 0) 
		{
			operator[depth] = 1;
			backtracking(depth - 1, plus-1, minus, mul, div, flag);
		}
		if(minus > 0)
		{
			operator[depth] = 1;
			numbers[depth+1] = -numbers[depth+1];
			backtracking(depth - 1, plus, minus-1, mul, div, flag);
			numbers[depth+1] = -numbers[depth+1];
		}
		if(mul > 0)
		{
			operator[depth] = 2;
			backtracking(depth - 1, plus, minus, mul-1, div, true);
		}
		if(div > 0)
		{
			operator[depth] = 3;
			backtracking(depth - 1, plus, minus, mul, div-1, true);
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		len			= Integer.parseInt(br.readLine());
		numbers			= new int[len];
		operator	= new int[len-1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<len; i++)
			numbers[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int plus	= Integer.parseInt(st.nextToken());
		int minus	= Integer.parseInt(st.nextToken());
		int mul		= Integer.parseInt(st.nextToken());
		int div		= Integer.parseInt(st.nextToken());
		
		backtracking(len-2, plus, minus, mul, div, false);

		StringBuilder sb = new StringBuilder();
		sb.append(max)
			.append('\n')
			.append(min);
		System.out.print(sb);
	}
}