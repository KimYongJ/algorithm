// https://github.com/kimyongj/algorithm

class Main{
	static long total, win, main, left, right, mid, ans;
    static long read() throws Exception {// 빠른 입력을 위한 함수
        long c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
    public static long get(long cnt) {
    	return ((win+cnt)*100) / (total+cnt);
    }
	public static void main(String[] args)throws Exception{
		total 	= read();
		win   	= read();
		main 	= get(0);
		ans 	= -1;
		left 	= 0;
		right 	= 1_000_000_000;
		while(left <= right) {
			mid = (left + right) / 2;
			if(main != get(mid)) {
				right = mid-1;
				ans = mid;
			}
			else 
				left = mid+1;
		}
		System.out.print(ans);
	}
}