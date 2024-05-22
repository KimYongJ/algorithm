// https://github.com/kimyongj/algorithm

class Main{
	
	static int N, op[], num[];
	static int MAX, MIN;

	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int plus, int minus, int mul, int div, int depth, int sum) {
		if(depth == N) 
		{
			if(sum > MAX) MAX = sum;
			if(sum < MIN) MIN = sum;
			return;
		}
		if(plus > 0)	DFS(plus-1, minus, mul, div, depth+1, sum + num[depth]);
		if(minus > 0)	DFS(plus, minus-1, mul, div, depth+1, sum - num[depth]);
		if(mul > 0)		DFS(plus, minus, mul-1, div, depth+1, sum * num[depth]);
		if(div > 0)		DFS(plus, minus, mul, div-1, depth+1, sum / num[depth]);
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		num 	= new int[N];
		op		= new int[4];
		MAX 	= Integer.MIN_VALUE;
		MIN 	= Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++)
			num[i] = read(); 	// 기준이 되는 숫자 삽입
		
		for(int i=0; i<4; i++)
			if((op[i]=read()) == N) // 연산자 개수 입력
				op[i] = N-1; 		// 연산자가 N을 초과하면 N-1개만큼 줄임
		
		DFS(op[0], op[1], op[2], op[3],1, num[0]);
		
		System.out.print(
				new StringBuilder().append(MAX)
						.append('\n').append(MIN).toString()
				);
	}
}