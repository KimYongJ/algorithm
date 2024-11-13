//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2304
class Main{
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int N	= read();
		int H[] = new int[1001];
		int len = 0;
		int res = 0;
		int IDX	= 0;
		int sIdx= 0;

		while(N-->0)
		{
			int i	= read();			// 가로 인덱스
			H[i]	= read();			// 높이
			len		= Math.max(len, i);
		}

		for(int i=0, h = 0; i<=len; i++)
			if(h < H[i])				// 기존 높이보다 큰것이 나타나면
			{
				res += (i - IDX) * h;
				h	= H[i];				// 높이 다시 세팅
				IDX	= i;				// 시작 인덱스 다시 세팅
			}

		for(int i=len, h = 0; i>=IDX; i--)
			if(h < H[i])
			{
				res		+= (sIdx - i) * h;
				h		= H[i];
				sIdx	= i;
				if(IDX == i)
					res += h;
			}
			else if(IDX == i)
				res += (sIdx - i + 1) * h;

		System.out.print(res);
	}
}