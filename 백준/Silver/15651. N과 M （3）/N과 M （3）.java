// https://github.com/KimYongJ/algorithm

class Main{

	static int N, M;
	static StringBuilder sb;

	// 빠른 입력을 위한 함수
	public static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
	// DFS 함수
	public static void DFS(int depth, int select[]) {
		if(depth == M) {
			for(int s : select) 
				sb.append(s).append(' ');
			sb.append('\n');
			return;
		}
		for(int i=1; i<=N; i++) {
			select[depth] = i;
			DFS(depth+1, select);
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		sb 		= new StringBuilder();
		
		DFS(0, new int[M]);
		
		System.out.print(sb);
	}
}