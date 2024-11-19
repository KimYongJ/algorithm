//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27172
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N			= read();		// 플레이어 수 (2≤N<십만)
		int origin[]	= new int[N];	// 각플레이어의 숫자 1~백만
		int arr[]		= new int[1_000_001];
		int score[]		= new int[1_000_001];
		int max			= 0;
		
		for(int i=0; i<N; i++)
		{
			arr[origin[i] = read()] = 1;
			if(max < origin[i])
				max = origin[i];
		}

		for(int i=1; i<=max; i++)
			if(arr[i] != 0)
				for(int j=i<<1; j<=max; j+=i)
					if(arr[j] != 0)
					{
						++score[i];
						--score[j];
					}
		
		StringBuilder sb = new StringBuilder();
		for(int o : origin)
			sb.append(score[o]).append(' ');
		System.out.print(sb.toString());
	}
}