// https://github.com/kimyongj/algorithm

class Main{
	static int total, win, main, left, right, mid, ans;
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
    public static int get(long cnt) {
    	return (int) ((((long)win+cnt)*100) / (total+cnt));
    }
	public static void main(String[] args)throws Exception{
		total = read();
		win   = read();
		main  = get(0);
		ans	  = -1;
		left  = 1;
		right = 1_000_000_000;
		while(left <= right) {
			mid = (left + right) / 2;
			if(main != get(mid)) {
				right = mid-1;
				ans   = mid;
			}else { 
				left  = mid+1;
			}
		}
		System.out.print(ans);
	}
}