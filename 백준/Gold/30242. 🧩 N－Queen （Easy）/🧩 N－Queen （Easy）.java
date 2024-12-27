//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/30242
class Main{
	
	static int N;
	static int[] order;
	static boolean[] visit, pass, dig1, dig2;
	
	public static void main(String[] args)throws Exception{
		N		= read();
		order	= new int[N+1];
		visit	= new boolean[21];
		pass	= new boolean[N+1];
		dig1	= new boolean[2*N+2];
		dig2	= new boolean[2*N+2];
		
		for(int i=1; i<=N; i++)
		{
			order[i] = read();
			visit[order[i]] = true;
			if(order[i] != 0)
			{
				pass[i] = true;
				dig1[i - order[i] + N] = dig2[i + order[i]] = true;	// 이미 입력된 곳의 대각선 마킹
			}
		}
		StringBuilder sb = new StringBuilder();
		if(back(1))
			for(int i=1; i<=N; i++)
				sb.append(order[i]).append(' ');
		else
			sb.append(-1);
		
		System.out.print(sb.toString());
	}
	public static boolean back(int depth) {
		if(N < depth)
			return true;
		if(pass[depth])
			return back(depth + 1);
		// row는 depth로 하기 때문에 체크할 필요 없고, col과 대각선만 체킹하면됨, 대각선은 행과열의 마이너스 플러스로 간단히 관리가능
		for(int i=1; i<=N; i++)
			if(!visit[i] && !dig1[depth - i + N] && !dig2[depth + i])	// 열과 대각선체크를 동시에
			{
				visit[i] = dig1[depth - i + N] = dig2[depth + i] = true;
				order[depth] = i;
				if(back(depth + 1))
					return true;
				visit[i] = dig1[depth - i + N] = dig2[depth + i] = false;
			}
		
		return false;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}