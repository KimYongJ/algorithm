//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17251
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 2<=백만
		int left[] 	= new int[N];
		int right[]	= new int[N];
		
		for(int i=0; i<N; i++)
			left[i] = right[i] = read();
		
		for(int l=1,r=N-2;l<N; l++,r--)
		{
			left[l]	= Math.max(left[l - 1], left[l]);
			right[r]= Math.max(right[r+1], right[r]);
		}
		
		int lcnt = 0;
		int rcnt = 0;
		for(int i=0; i<N-1; i++)
		{
			if(left[i] < right[i+1])
				rcnt++;
			else if(right[i+1] < left[i])
				lcnt++;
		}
		
		if(lcnt == rcnt)
			System.out.print('X');
		else
			System.out.print(lcnt < rcnt ? 'B' : 'R');
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}