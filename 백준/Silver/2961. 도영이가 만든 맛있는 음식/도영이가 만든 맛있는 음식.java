// https://github.com/kimyongj/algorithm

class Main{
	
	static int 		N, S[], B[];
	static int		result;
	static boolean 	visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void Back(int depth,int mul, int sum, int idx) {
		if(depth > 0)				// 모든 재료의 경우에 대해서 결과 탐색
			result = Math.min(result, Math.abs(mul - sum));
		
		if(depth == N) 				// 끝일 때 종료 
			return;

		for(int i=idx; i<N; i++) 
			if(!visit[i]) 			// 방문하지 않았을 때만 연산실행
			{
				visit[i] = true;	// 백트레킹
				Back(depth + 1, mul * S[i] , sum + B[i], i+1); // 조합이기 때문에 i + 1로 탐색
				visit[i] = false;
			}
		
	}
	public static void main(String[] args)throws Exception{
		N = read();
		S = new int[N];		// 곱
		B = new int[N];		// 합
		visit = new boolean[N];
		result = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) 
		{
			S[i] = read();
			B[i] = read();
		}

		Back(0, 1, 0, 0);

		System.out.print(result);
	}
}