//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1976
//2초 / 128MB

class Main{
	
	static int N, M;
	static int[] dir, parent;
	
	public static void main(String[] args)throws Exception{
		N		= read();	//도시 수1<=200
		M		= read();	//여행계획에 속한 도시 수 1<=천
		parent	= new int[N+1];
		dir		= new int[M];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;	// 부모는 자기 자시능로 세팅
		
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				if(read() == 1)
					union( find(y), find(x) );

		dir[0] = read();
		
		for(int i=1; i<M; i++)
		{
			dir[i] = read();
			
			if(find(dir[i-1]) != find(dir[i]))
			{
				System.out.print("NO");
				return;
			}
		}

		System.out.print("YES");
	}
	public static void union(int p1, int p2) {
		if(p1 < p2)
			parent[p2] = p1;
		else
			parent[p1] = p2;
	}
	public static int find(int node) {
		if(parent[node] == node)
			return node;
		return parent[node] = find(parent[node]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}