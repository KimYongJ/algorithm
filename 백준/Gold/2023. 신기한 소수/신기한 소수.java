// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static int N, next;
	static int map[][];
	static StringBuilder sb = new StringBuilder();
	public static boolean check(int num) {
		if(num < 2) 
			return false;
		if(num > 2) 
			for(int i=2; i*i<=num; i++)
				if(num%i == 0) 
					return false;
		return true;
	}
	public static void backtracking(int depth, int sum) {
		if(depth == N) 
		{
			sb.append(sum).append('\n');
			return;
		}
		
		for(int i=0; i<6; i++) 
		{
			next = sum * 10 + map[depth][i];
			if(check(next))
				backtracking(depth + 1, next);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N 	= Integer.parseInt(br.readLine());
		map	= new int[N][6];
		
		for(int i=0; i<N; i++)
			map[i] = new int[] {1,2,3,5,7,9};
		
		backtracking(0,0);
		
		System.out.print(sb);
	}
}