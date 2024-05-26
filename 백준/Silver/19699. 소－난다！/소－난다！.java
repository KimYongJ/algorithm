// https://github.com/kimyongj/algorithm

class Main{
	
	static int		N, M, sum;
	static int 		arr[];
	static boolean 	visit[], result[];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int depth, int sum, int idx) {
		if(depth == 0) 
		{
			if(!visit[sum]) 
				result[sum] = visit[sum] = true;
			return;
		}
		
		for(int i=idx; i<N; i++)
			DFS(depth-1, sum + arr[i], i+1);
		
	}
	public static void main(String[] args)throws Exception{
		StringBuilder 	sb = new StringBuilder();
		N 	= read(); 
		M 	= read();
		arr = new int[N];
		
		for(int i=0; i<N; i++)
			sum += arr[i] = read();
		
		result 	 = new boolean[sum+1];
		visit 	 = new boolean[sum+1];
		visit[0] = visit[1] = true;
		
		//에라토스테네스의 체
		for(int i=2; i<=sum; i++) 
		{
			if(visit[i]) 
				continue;
			
			int num = i<<1;
			while(num <= sum) {
				visit[num] = true;
				num += i;
			}
		}
		
		DFS(M, 0, 0);
		
		for(int i=0; i<=sum; i++)
			if(result[i])
				sb.append(i).append(' ');
		
		if(sb.length() == 0)
			sb.append(-1);
		
		System.out.print(sb);
	}
}