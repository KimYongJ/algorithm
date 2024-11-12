//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2851
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int sum		= 0;
		int before	= 0;
		for(int i=0; i<10; i++)
		{
			sum += read();
			if(100 <= sum)
			{
				if(100 - before < sum - 100)
					sum = before;
				break;
			}
			before = sum;
		}
		System.out.print(sum);
	}
}