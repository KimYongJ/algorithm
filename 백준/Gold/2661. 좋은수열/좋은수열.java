// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{

	static int N;
	static int arr[];
	
	public static boolean check(int len) {
		int temp = (len+1)/2;
		for(int i = 2; i <= temp; i++) 
		{
			boolean isSame=true;
			int a = len-i*2+1;
			int b = len-i+1;
			for(int j = 0 ; j < i; j++) {
				if(arr[a+j]!=arr[b+j]) {
					isSame=false;
					break;
				}
			}
			if(isSame)
				return false;
		}
		return true;
	}
	public static boolean backtracking(int depth, int before) {
		if(depth == N) 
			return true;
		
		for(int i=1; i<=3; i++) 
			if(i != before) 
			{
				arr[depth] = i;
				if(check(depth) && backtracking(depth + 1, i))
					return true;
			}
		
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());
		arr = new int[N];
		backtracking(0,-1);
		
		StringBuilder sb = new StringBuilder();
		for(int a : arr)
			sb.append(a);
		System.out.print(sb);
	}
}