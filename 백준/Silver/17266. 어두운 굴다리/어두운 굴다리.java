//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/17266
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 굴다리 길이 (1<=십만)
		int M		= read();		// 가로등의 개수 M (1<=N)
		int pos[]	= new int[M];	// 가로등의 위치(오름차순 정렬)
		int maxGap	= pos[0] = read();
		
		for(int i=1; i<M; i++)
		{
			pos[i] = read();
			
			int gap = (int)Math.ceil((pos[i] - pos[i-1])/2.0);
			maxGap = Math.max(maxGap, gap);
		}
		
		maxGap = Math.max(maxGap, N - pos[M-1]);
		
		System.out.print(maxGap);
	}
}