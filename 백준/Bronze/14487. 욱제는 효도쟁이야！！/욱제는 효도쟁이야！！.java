// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N = read();
		int max = 0;
		int res = 0;
		int n;
		for(int i=0; i<N; i++) 
		{
			res += n = read();
			if(max < n)
				max = n;
		}
		System.out.print(res - max);
	}
}
