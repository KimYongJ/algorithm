//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14697

class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int a[]		= new int[] {read(),read(),read()};
		int target	= read();
		int lenA	= (target / a[0]) + 1;
		int lenB	= (target / a[1]) + 1;
		int lenC	= (target / a[2]) + 1;
		
		for(int i=0; i<lenA; i++)
			for(int j=0; j<lenB; j++)
				for(int z=0; z<lenC; z++)
				{
					int sum = i*a[0] + j*a[1] + z*a[2];
					if(target == sum)
					{
						System.out.print(1);
						return;
					}
					else if(target < sum)
						break;
				}

		
		System.out.print(0);
	}
}
