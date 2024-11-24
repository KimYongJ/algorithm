//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1079
class Main{
	
	static int			res, M, N;
	static int[]		guilt;
	static int[][]		R;
	static boolean[]	kill;
	
	public static void main(String[] args)throws Exception{
		N		= read();
		guilt	= new int[N];
		R		= new int[N][N];
		kill	= new boolean[N];

		for(int i=0; i<N; i++)
			guilt[i] = read();

		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				R[y][x] = read();

		M = read();

		bruteforce(0, N);
		
		System.out.print(res);
	}
	
	public static void bruteforce(int day, int people) {
		if(people == 1)
		{
			res = Math.max(res, day);
			return;
		}

		if(people % 2 == 0)	// 참가자 짝수 = 밤(마피아가 타인 킬)
		{
			for(int i=0; i<N; i++)
				if(!kill[i] && i != M)
				{
					kill[i] = true;
					for(int j=0; j<N; j++) guilt[j] += R[i][j];
					bruteforce(day + 1, people - 1);
					for(int j=0; j<N; j++) guilt[j] -= R[i][j];
					kill[i] = false;
				}
		}
		else				// 홀수 = 낮(시민이 한명 킬)
		{
			int max = 0;
			int idx = 0;
			
			for(int i=0; i<N; i++)
				if(!kill[i] && max < guilt[i])
				{
					max = guilt[i];
					idx = i;
				}
			
			if(idx == M)
				res = Math.max(res, day);
			else
			{
				kill[idx] = true;
				bruteforce(day , people - 1);
				kill[idx] = false;
			}
		}
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}