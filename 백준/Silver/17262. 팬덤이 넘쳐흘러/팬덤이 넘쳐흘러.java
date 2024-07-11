// https://github.com/kimyongj/algorithm
class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int N = read();
		int s,e, min = 9999999, max = 0;
		for(int i=0; i<N; i++) 
		{
			s = read();
			e = read();
			if(min > e) 
			{
				min = e;
			}
			if(max < s) 
			{
				max = s;
			}
		}
		int result = max - min;
		System.out.print(result > 0 ? result : 0);
	}
}