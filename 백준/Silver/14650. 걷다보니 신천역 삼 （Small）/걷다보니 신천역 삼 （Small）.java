// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static int result;
    
	public static void DFS(int depth, int sum) {
		if(depth == 1) 
		{
			if(sum % 3 == 0) result++;
			return;
		}
		
		for(int i=0; i<3; i++)
			DFS(depth-1, sum*10 + i);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		DFS(N, 1);
		DFS(N, 2);
		
		System.out.print(result);
	}
}