// https://github.com/kimyongj/algorithm
class Main{
	
	static int N, result[], adlist[][];
	static boolean visit[][];
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<N; i++)
			sb.append(result[i]).append('\n');
		System.out.print(sb);
		System.exit(0);
	}
	public static void DFS(int depth) {
		if(depth == N) 
			print();
		
		for(int next : adlist[depth])
		{
			if(next == 0) 
				break;
			if(!visit[depth][next] && next != result[depth-1]) 
			{
				visit[depth][next] = true;
				result[depth] = next;
				DFS(depth + 1);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N 		= read()+1; 			// 날수 = 깊이
		result 	= new int[N];			// 날수(깊이) 에 따라 줄 떡
		adlist 	= new int[N][9];		// 해당 날에 가져갈 떡 종류
		visit  	= new boolean[N][10];	// 방문 체크
		for(int i=1,J; i<N; i++) 
		{
			J = read();
			for(int j=0; j<J; j++) 
				adlist[i][j] = read();
		}
		DFS(1);
		System.out.println(-1);
	}
}
