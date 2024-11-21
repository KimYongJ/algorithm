//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7573
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();	// 맵크기 2<=만
		int L		= read();	// 그물 길이 (4<=100, L≤4N-4만족하는짝수)
		int M		= read();	// 물고기 수 (1<=100)
		int[][]pos	= new int[M][2];
		
		for(int i=0; i<M; i++)
			pos[i] = new int[] {read(),read()};
		
		int max = 0;
		int len = L / 2;
		for(int width=1; width<len; width++)
		{
			int originHeight = len - width;
			// 특정 점을 기준으로 그 점이 그물의 가장 왼쪽위일때 혹은, 그물의 가장 왼쪽아래일때 까지 모두 다 탐색해야 한다.
			for(int height = originHeight; height>=0; height--)
			{
				for(int ps[] : pos)
				{
					int sy	= ps[0] - height;
					int sx	= ps[1];
					int ey	= sy + originHeight;
					int ex	= ps[1] + width;
					int cnt = 0;
					for(int p[] : pos)
					{
						if(sy<=p[0] && p[0]<=ey && sx<=p[1] && p[1]<=ex)
							++cnt;
					}
					max = Math.max(max, cnt);
				}
			}
		}

		System.out.print(max);
	}
}