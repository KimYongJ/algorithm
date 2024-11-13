//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1233
class Main{
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int a		= read();
		int b		= read();
		int c		= read();
		int max		= 0;
		int cnt[]	= new int[81];
		
		for(int i=1; i<=a; i++)
			for(int j=1; j<=b; j++)
				for(int z=1; z<=c; z++)
					if(max < ++cnt[i+j+z])
						max = cnt[i+j+z];
		
		for(int i=0; i<81; i++)
			if(cnt[i] == max)
			{
				System.out.print(i);
				return;
			}
		
	}
}