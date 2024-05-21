// https://github.com/kimyongj/algorithm

class Main{
	
	static int 		N, arr[];
	static boolean 	visit[];
	static StringBuilder sb;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	static void input() {
		for(int i=1; i<N; i++)
			sb.append(arr[i]).append(' ');
		sb.append('\n');
	}
	public static void backtracking(int depth) {
		if(depth == N) 
		{
			input();
			return;
		}
		for(int i=1; i<N; i++)
			if(!visit[i]) 
			{
				visit[i] 	= true;
				arr[depth] 	= i;
				backtracking(depth + 1);
				visit[i] 	= false;
			}
	}
	public static void main(String[] args)throws Exception{
		N 		= read()+1;
		arr		= new int[N];
		visit 	= new boolean[N];
		sb 		= new StringBuilder();
		
		backtracking(1);
		
		System.out.print(sb);
	}
}