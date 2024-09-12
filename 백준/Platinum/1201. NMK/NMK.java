//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1201
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = read(); // 1~N까지 범위(1<=500)
		int M = read(); // 가장 긴 증가하는 부분수열
		int K = read(); // 가장 긴 감소하는 부분수열
		
		if(N < M+K-1 || M*K <N)
		{
			System.out.print(-1);
			return;
		} 
		for(int i=K; i>=1; i--)
			sb.append(i).append(' ');
		
		M--; // 남은 그룹 수
		N-=K;// 몇개의 숫자를 더 넣을 수 있는지.
		
		if(M > 0)
		{
			int repeat	= 0;		// 각 위치당 몇번을 반복해야 하는지 담는다.
			int number	= 0;		// 각 위치당 시작 숫자를 담는다.
			int remain	= N / M;	// 각 위치당 최소 반복해야 하는 수
			int mod		= N % M;	// 각 위치에서 더 반복할 수 있는 수
			for(int i=0; i<M; i++)
			{
				repeat = remain;
				if(mod > 0)
				{
					mod--;
					repeat++;
				}
				number = K += repeat;	// number는 내림 차순의 시작값
				
				while(repeat-->0)		// 구한 값으로 값을 넣는다.
					sb.append(number--).append(' ');
			}
		}
		
		System.out.print(sb.toString());
	}
}
