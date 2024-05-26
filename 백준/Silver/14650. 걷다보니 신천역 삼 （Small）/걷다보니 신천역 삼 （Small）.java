// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	public static int DFS(int depth, int sum) {
		if(depth == 1)
			return sum % 3 == 0 ? 1:0;
		
		int cnt = 0;
		
		for(int i=0; i<3; i++)
			cnt += DFS(depth-1, sum*10 + i);
	
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		System.out.print(DFS(N, 1) + DFS(N, 2));
	}
}