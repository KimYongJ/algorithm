// https://github.com/kimyongj/algorithm

class Main{
	
	static int 		N, K, num, kit[];
	static int 		result;
	static boolean 	visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void Backtracking(int depth, int sum) {
		if(depth == N) 
		{
			if(sum >= 500) 
				result++;
			return;
		}
		for(int i=0; i<N; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				num = sum + kit[i];
				if(num >= 500) {
					Backtracking(depth + 1, num);
				}
				visit[i] = false;
			}
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		K 		= read();
		kit 	= new int[N];
		visit 	= new boolean[N];
		
		for(int i=0; i<N; i++)
			kit[i] = read() - K;
		
		Backtracking(0,500);
		
		System.out.print(result);
	}
}