// https://github.com/kimyongj/algorithm

class Node{
	int a,b,c,d,e;
	Node(int a, int b, int c, int d, int e){
		this.a=a; this.b=b; 
		this.c=c; this.d=d; 
		this.e=e;
	}
}

class Main{
	
	static int 	result = Integer.MAX_VALUE;
	static int 	resultArr[];
	static int 	arr[];
	static int	N, A, B, C, D;
	static Node	node[];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static boolean validate() {
		for(int i=0; i<N; i++) 
			if(resultArr[i] == 0 && arr[i] != 0 || resultArr[i] < arr[i])
				return false;
			else if(resultArr[i] != 0 && arr[i] == 0 || resultArr[i] > arr[i]) 
				return true;
		return true;
	}
	public static void dfs(int depth, int idx, int a, int b, int c, int d, int e) {
		if(A<=a && B<=b && C<=c && D<=d) 
		{
			if(result == e) 
			{
				if(validate()) 
				{
					result		= e;
					resultArr	= arr.clone();
				}
			}
			else if(result >  e)
			{
				result		= e;
				resultArr 	= arr.clone();
			}
		}
		
		if(depth == N) 
			return;
		
		for(int i=idx; i<=N; i++) 
		{
			Node n = node[i];
			arr[depth] = i;
			dfs(depth + 1, i+1, a+n.a, b+n.b, c+n.c, d+n.d, e+n.e);
			arr[depth] = 0;
		}
	}
	public static void main(String[] args)throws Exception{
		N			= read();
		A			= read();
		B			= read();
		C			= read();
		D			= read();
		arr 		= new int[N];
		resultArr	= new int[N];
		node		= new Node[N+1];
		
		for(int i=1; i<=N; i++) 
			node[i] = new Node(read(),read(),read(),read(),read());
		
		
		dfs(0,1,0,0,0,0,0);
		
		if(result == Integer.MAX_VALUE) 
		{
			System.out.print(-1);
		}
		else {
			StringBuilder sb = new StringBuilder();
			
			sb.append(result).append('\n');
			
			for(int r : resultArr)
				if(r == 0)	
					break;
				else 		
					sb.append(r).append(' ');
			
			System.out.print(sb);
		}
	}
}