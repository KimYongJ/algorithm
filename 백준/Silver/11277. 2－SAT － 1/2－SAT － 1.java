// https://github.com/kimyongj/algorithm

class Main{
	
	static int 		N, M, data[][];
	static boolean  flag, bool[];
	
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	
	public static boolean get(int num) {
		return num > 0 ? bool[Math.abs(num)] : !bool[Math.abs(num)];
	}

	public static boolean DFS(int depth, boolean f) {
		if(depth > N) {
			flag = get(data[0][0]) || get(data[0][1]);
			
			for(int i=1; i<M && flag; i++) 
				flag = flag && (get(data[i][0]) || get(data[i][1]));
			
			return flag;
		}

		bool[depth] = f;
		
		return DFS(depth + 1, true) || DFS(depth + 1, false);
	}
	
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		bool 	= new boolean[N+1];
		data	= new int[M][2];
		
		for(int i=0; i<M; i++) 
		{
			data[i][0] = read();
			data[i][1] = read();
		}
		
		
		// bool 배열에 true와 false에 대해 모든 것을 담아야 함으로 조합으로 구현
		int num = 0;
		
		if(DFS(1, true) || DFS(1, false))
			num = 1;

		System.out.print(num);
	}
	
}