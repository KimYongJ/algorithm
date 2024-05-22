// https://github.com/kimyongj/algorithm

class Main{
	
	static int 		N, MAX, nums[];
	static boolean 	visit[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int getMul(int l, int r) {
		while(visit[l] || visit[r]) 
		{
			if(visit[l])l--;
			if(visit[r])r++;
		}
		return nums[l] * nums[r];
	}

	public static void DFS(int depth, int sum) {
		if(depth == N) 
		{
			if(MAX < sum) 
				MAX = sum;
			return;
		}
		for(int i=1; i<=N; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				DFS(depth + 1, sum + getMul(i-1, i+1));
				visit[i] = false;
			}
	}
	
	public static void main(String[] args)throws Exception{
		N 		= read();
		nums	= new int[N];
		visit	= new boolean[N];
		
		for(int i=0; i<N; i++) 
			nums[i] = read();
		
		N		-= 2; // 빠른 연산을 위한 전처리 
		
		DFS(0,0);
		
		System.out.print(MAX);
	}
}