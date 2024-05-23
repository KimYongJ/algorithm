// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static int arr[];
	static int len;
	static int cnt;

	public static void backtracking(int depth,int before) {
		if(len == depth) 
		{
			cnt++;
			return;
		}
		
		for(int i=1; i<28; i++) 
			if(arr[i] > 0 && before != i) 
			{
				arr[i]--;
				backtracking(depth + 1, i);
				arr[i]++;
			}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str 	= br.readLine();
		len 		= str.length()+1;
		arr 		= new int[28];
		
		for(char c : str.toCharArray())
			arr[c-96]++;

		
		backtracking(1,0);
		
		System.out.print(cnt);
	}
}