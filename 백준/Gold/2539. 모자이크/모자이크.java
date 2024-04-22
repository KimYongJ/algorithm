// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, M, paper, maxY, maxX, xrr[], left, right, mid, ans;
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean check(int size) {
		int cnt = 0, hide = 0;
		for(int x : xrr)
			if(hide < x) {
				hide = x + size - 1;
				cnt++;
			}
		return cnt <= paper;
	}
	public static void main(String[] args)throws Exception{
		Y 		= read();
		X 		= read();
		paper 	= read();
		M 		= read();
		xrr		= new int[M];
		int y, x;
		for(int i=0; i<M; i++) {
			y 	 	= read();
			x	 	= read();
			maxY 	= Math.max(maxY, y);
			maxX 	= Math.max(maxX, x);
			xrr[i] 	= x;
		}
		
		Arrays.sort(xrr);
		
		left 	= maxY;
		right 	= 1_000_002;
		while(left <= right) {
			mid = (left + right) / 2;
			if(check(mid)) {
				ans = mid;
				right = mid - 1;
			}else
				left = mid + 1;
		}
		System.out.print(ans);
	}
}