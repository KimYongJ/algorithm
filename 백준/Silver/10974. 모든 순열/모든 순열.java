// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	
	static int 		N, END;
	static char		arr[];
	static boolean 	visit[];
	static StringBuilder sb;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}

	public static void backtracking(int depth) {
		if(depth >= END) 
		{
			sb.append(arr).append('\n');
			return;
		}
		for(int i=1; i<=N; i++)
			if(!visit[i]) 
			{
				visit[i] 	= true;
				arr[depth] 	= (char) (i+'0');
				backtracking(depth + 2);
				visit[i] 	= false;
			}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		END		= N*2;
		arr		= new char[END];
		visit 	= new boolean[N+1];
		sb 		= new StringBuilder();
		
		Arrays.fill(arr, ' ');
		
		backtracking(0);
		
		System.out.print(sb);
	}
}