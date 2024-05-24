// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
	
	static int 		len, num, result, arr[];
	static boolean 	visit[];
    
	public static boolean backtracking(int depth, int sum) {
		if(depth == len) 
		{
			if(num < sum)
			{
				result = sum;
				return true;
			}
			return false;
		}
		for(int i=0; i<len; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				if(backtracking(depth + 1, sum*10 + arr[i])) return true;
				visit[i] = false;
			}
		return false;
	}
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		len 	= str.length();
		arr		= new int[len];
		visit	= new boolean[len];
		num 	= Integer.parseInt(str);
		
		for(int i=0; i<len; i++)
			arr[i] = str.charAt(i)-'0';

		Arrays.sort(arr);
		
		backtracking(0,0);
		
		System.out.print(result);
	}
}