// https://github.com/kimyongj/algorithm
class Main{
	static long read() throws Exception {// 빠른 입력을 위한 함수
		long c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N = (int)read();
		long max = 0;
		long sum = 0;
		for(int i=0; i<N; i++) 
		{
			long num = read();
			sum += num;
			if(max < num) 
			{
				max = num;
			}
		} 
		System.out.print(max + (sum - max)/2.0);
	}
}