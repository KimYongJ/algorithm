// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, M, paper, maxY, maxX, xrr[], left, right, mid, ans;
	public static boolean check(int size) {
		int cnt = 0, hide = 0;
		for(int x : xrr) {
			if(hide < x) {
				hide = x + size - 1;
				cnt++;
			}
		}
		return cnt <= paper;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		paper 	= Integer.parseInt(br.readLine());
		M 		= Integer.parseInt(br.readLine());
		xrr		= new int[M];
		int y, x;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			maxY = Math.max(maxY, y);
			maxX = Math.max(maxX, x);
			xrr[i] = x;
		}
		
		Arrays.sort(xrr);
		
		left = maxY;
		right = 1_000_002;
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