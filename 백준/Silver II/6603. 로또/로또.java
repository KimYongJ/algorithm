// https://github.com/KimYongJ/algorithm

class Main{
	
	static int K, arr[], base[];
	static boolean visit[];
	static StringBuilder sb;
	// 빠른 입력을 위한 함수
	static int read() throws Exception 
	{
	        int c, n = System.in.read() & 15;
	        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	        return n;
	} 

	public static void DFS(int depth, int i) 
	{
		if(depth == 6) 
		{
			for(int x : base) 
				sb.append(x).append(' ');
			sb.append('\n');
			return;
		} 
		for(; i<K; i++) 
		{
			if(!visit[i]) 
			{
				visit[i] 	= true;
				base[depth] = arr[i];
				DFS(depth+1, i);
				visit[i] 	= false;
			}
		}
		
	}
	public static void main(String[] args)throws Exception
	{
		sb 		= new StringBuilder();
		base 	= new int[6];
		while(true) {
			K 	= read();
			
			if(K == 0) break; 			// k가 0인 경우 종료 
			arr 	= new int[K];		// 값을 담을 배열 생성
			visit 	= new boolean[K]; 	// DFS에서 방문체크
			
			for(int i=0; i<K; i++)
				arr[i] = read();
			DFS(0,0);
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
}