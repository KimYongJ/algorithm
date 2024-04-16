// https://github.com/kimyongj/algorithm
class Main{
	static long N, M, time[];
	static long left, mid, right;
    static long read() throws Exception {
        long c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static boolean check(long mid) {
		long men = 0;
		for(long t : time) 
		{
			men += mid / t;
			if(men >=M) 
				return true;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		N = read(); // 심사대
		M = read(); // 사람
		time = new long[(int) N];
		for(int i=0; i<N; i++)
			time[i] = read();
		
		right = 1_000_000_000_000_000_000L;
		while(left <= right) {
			mid = (left + right) / 2;
			if(check(mid))
				right = mid - 1;
			else
				left = mid + 1;
		}
		System.out.println(left);
	}
}