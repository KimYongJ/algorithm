// https://github.com/KimYongJ/algorithm

class Main{

	static int N, M;
	static char select[];
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
	public static void DFS(int depth) {
		if(depth == M) { 
			sb.append(select).append('\n');
			return;
		}
		for(int i=1; i<=N; i++) {
			select[depth] = (char) (i+'0');
			select[depth+1] = ' ';
			DFS(depth+2);
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read()*2;
		select	= new char[M];
		sb 		= new StringBuilder();
		DFS(0);
		System.out.print(sb);
	}
}