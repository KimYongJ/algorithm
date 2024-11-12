//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1977
class Main{
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int s	= read()-1;
		int e	= read();
		int sum	= 0;
		int min	= 0;
		
		boolean findMin = false;
		while(++s <= e)
		{
			if(Math.sqrt(s) % 1 == 0)
			{
				sum += s;
				if(!findMin)
				{
					findMin = true;
					min = s;
				}
			}
		}
		if(findMin)
			System.out.println(
								new StringBuilder().append(sum).append('\n').append(min)
								);
		else
			System.out.print(-1);
	}
}