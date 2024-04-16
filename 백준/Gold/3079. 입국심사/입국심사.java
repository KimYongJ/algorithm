// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	static long N, M, time[];
	static long left, mid, right, ans;
	public static boolean check(long mid) {
		long men = 0;
		for(long t : time) {
			men += mid / t;
			if(men>=M) 
				return true;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken()); // 심사대
		M = Long.parseLong(st.nextToken()); // 사람
		time = new long[(int)N];
		for(int i=0; i<N; i++) {
			time[i] = Long.parseLong(br.readLine());
		}
		left = 1;
		right = Long.MAX_VALUE-100000000;
		while(left <= right) {
			mid = (left + right) / 2;
			if(check(mid)) {
				ans = mid;
				right = mid - 1;
			}else {
				left = mid + 1;
			}
		}
		System.out.println(ans);
	}
}