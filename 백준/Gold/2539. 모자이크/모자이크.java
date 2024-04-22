// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int y, x, mid, cnt, hide, maxY = 0, maxX = 0, ans = 0;
		read(); read();
		int paper 	= read();
		int M 		= read();
		int xrr[]	= new int[M];
		
		for(int i=0; i<M; i++) {
			y 	 	= read();
			x	 	= read();
			maxY 	= Math.max(maxY, y);
			maxX 	= Math.max(maxX, x);
			xrr[i] 	= x;
		}
		
		Arrays.sort(xrr);
		
		int left 	= maxY;
		int right 	= 1_000_002;
		while(left <= right) {
			mid = (left + right) / 2;
			cnt = hide = 0;
			for(int xp : xrr)
				if(hide < xp) {
					hide = xp + mid - 1;
					cnt++;
				}
			
			if(cnt <= paper) {
				ans = mid;
				right = mid - 1;
			}else
				left = mid + 1;
		}
		System.out.print(ans);
	}
}