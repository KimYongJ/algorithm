// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{

	static StringBuilder sb = new StringBuilder();
	
	public static boolean check(int num) {
		if(num < 2) 
			return false;
		else if(num > 2) {
			for(int i=2; i*i<=num; i++) {
				if(num%i == 0) {
					return false;
				}
			}
		}
		return true;
	}
	public static void backtracking(int depth, int sum, int[] map) {
		if(depth == 0) 
		{
			sb.append(sum).append('\n');
			return;
		}
		
		for(int i=0; i<6; i++) 
		{
			int next = sum * 10 + map[i];
			if(check(next))
				backtracking(depth - 1, next, map);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N 		= Integer.parseInt(br.readLine());
		int map[]	= new int[] {1,2,3,5,7,9};
		
		backtracking(N,0, map);
		
		System.out.print(sb);
	}
}