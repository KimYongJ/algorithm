//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/17266
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean check(int pos[], int mid, int N) {
		int s = 0;// 가장왼쪽
		for(int p : pos)
		{
			if(p - mid > s)
				return false;
			s = p + mid;
		}
		return pos[pos.length-1] + mid >= N;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 굴다리 길이 (1<=십만)
		int M		= read();		// 가로등의 개수 M (1<=N)
		int pos[]	= new int[M];	// 가로등의 위치(오름차순 정렬)
		
		for(int i=0; i<M; i++)
			pos[i] = read();
		
		int res	= 0;
		int s	= 0;
		int e	= N;
		while(s <= e)
		{
			int mid = (s + e) / 2;
			if(check(pos, mid, N))
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		System.out.print(res);
	}
}