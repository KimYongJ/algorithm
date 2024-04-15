// https://github.com/kimyongj/algorithm

class Main{
	
	static long N, cnt, sum;
	public static void main(String[] args)throws Exception{
		N 	= read();
		while(sum <= N) 
		{
			sum += ++cnt;
		}
		System.out.println(--cnt);
	}
    static long read() throws Exception {// 빠른 입력을 위한 함수
        long c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
}