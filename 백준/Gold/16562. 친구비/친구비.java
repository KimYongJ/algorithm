//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16562
//2초 / 512MB

class Main{
	
	static int N, M, TOTAL;
	static int[] parent, money;
	static boolean[] visit;
	
	public static void main(String[] args)throws Exception{
		N		= read();	// 학생수(1<=만)
		M		= read();	// 친구 관계수(0<=만)
		TOTAL	= read();	// 가지고있는돈(1<=천만)
		parent	= new int[N+1];
		money	= new int[N+1];
		visit	= new boolean[N+1];
		
		for(int i=1; i<=N; i++)
		{
			parent[i]	= i;
			money[i]	= read();
		}
		
		for(int i=0; i<M; i++)
		{
			int parent1 = getParent( read() );
			int parent2 = getParent( read() );
			if(parent1 != parent2)
			{
				if(parent2 < parent1)
				{
					int tmp = parent2;
					parent2 = parent1;
					parent1 = tmp;
				}
				
				parent[parent2] = parent1;
				money[parent1] = Math.min(money[parent1], money[parent2]);
			}
		}
		
		int total = 0;
		
		for(int i=1; i<=N; i++)
		{
			int parentNum = getParent(i);
			if(!visit[parentNum])
			{
				visit[parentNum] = true;
				total += money[parentNum];
			}
		}
		
		System.out.print(total <= TOTAL ? total : "Oh no");
	}
	public static int getParent(int node) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent[node]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}