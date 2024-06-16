// https://github.com/kimyongj/algorithm

import java.util.ArrayList;

class Main{
	
	static int	max = Integer.MIN_VALUE;
	static int	min = Integer.MAX_VALUE;
	static int	numbers[];
	static int	operator[];
	static int	len;
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void cal() {
		ArrayList<Integer> number 	= new ArrayList<>();
		
		number.add(numbers[0]);
		
		for(int i=0; i<len-1; i++) 
		{
			if(operator[i] != 1) 
			{
				if(operator[i] == 2) 
					number.add(number.remove(number.size()-1) * numbers[i+1]);
				else 
					number.add(number.remove(number.size()-1) / numbers[i+1]);
			}
			else
				number.add(numbers[i+1]);
		}
		int sum = 0;
		
		for(int n : number) 
			sum += n;
		
		if(max < sum) max = sum;
		if(min > sum) min = sum;
	}
	public static void backtracking(int depth, int plus, int minus, int mul, int div) {
		if(depth < 0) 
		{
			cal();
			return;
		}
		if(plus > 0) 
		{
			operator[depth] = 1;
			backtracking(depth - 1, plus-1, minus, mul, div);
		}
		if(minus > 0)
		{
			operator[depth] = 1;
			numbers[depth+1] = -numbers[depth+1];
			backtracking(depth - 1, plus, minus-1, mul, div);
			numbers[depth+1] = -numbers[depth+1];
		}
		if(mul > 0)
		{
			operator[depth] = 2;
			backtracking(depth - 1, plus, minus, mul-1, div);
		}
		if(div > 0)
		{
			operator[depth] = 3;
			backtracking(depth - 1, plus, minus, mul, div-1);
		}
	}
	
	public static void main(String[] args)throws Exception{
		len			= read();
		numbers		= new int[len];
		operator	= new int[len-1];

		for(int i=0; i<len; i++)
			numbers[i] = read();
		
		backtracking(len-2, read(), read(), read(), read());

		System.out.print( new StringBuilder().append(max)
							.append('\n')
							.append(min).toString());
	}
}