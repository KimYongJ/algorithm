// https://github.com/KimYongJ/algorithm

class Main{
	
	static int MAX, MIN;
	static int maxplus, maxminus, maxmul, maxdiv;
	static int N, N_1, arr[];
	static boolean visit[];

	// 빠른 입력을 위한 함수
	static int read() throws Exception 
	{
	        int c, n = System.in.read() & 15;
	        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	        return n;
	}
	public static int calculator(int flag, int a, int b) 
	{
		if(flag == 0)		return a+b;
		else if(flag == 1)	return a-b;
		else if(flag == 2)	return a*b;
		else				return a/b;
	}
	public static void DFS(int plus, int minus, int mul,int div, int sum, int depth) {
		if(depth == N_1) {
			MAX = Math.max(sum, MAX);
			MIN = Math.min(sum, MIN);
			return;
		}
		
		if(plus < maxplus) {
			plus++;
			DFS(plus, minus, mul, div, calculator(0,sum,arr[depth+1]), depth+1);
			plus--;
		}
		if(minus < maxminus) {
			minus++;
			DFS(plus, minus, mul, div, calculator(1,sum,arr[depth+1]), depth+1);
			minus--;
		}
		if(mul < maxmul) {
			mul++;
			DFS(plus, minus, mul, div, calculator(2,sum,arr[depth+1]), depth+1);
			mul--;
		}
		if(div < maxdiv) {
			div++;
			DFS(plus, minus, mul, div, calculator(3,sum,arr[depth+1]), depth+1);
			div--;
		}
	}

	public static void main(String[] args)throws Exception
	{
		N 			= read();
		N_1 		= N-1;
		arr 		= new int[N];  		  		// 숫자들을 받을 배열
		visit 		= new boolean[N_1]; 		// 연산자들에 대해 DFS진행시 방문 체크할 배열
		MAX 		= Integer.MIN_VALUE;
		MIN 		= Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++)
			arr[i] 	= read();
		
		maxplus 	= read();
		maxminus 	= read();
		maxmul 		= read();
		maxdiv 		= read();
	
		DFS(0,0,0,0,arr[0],0);
		
		System.out.println(MAX);
		System.out.println(MIN);
	}
}