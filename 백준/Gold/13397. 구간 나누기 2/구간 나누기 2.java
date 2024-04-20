// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static final int MAX_VALUE = 100_000_000;
	static int N, M, num[], left , mid, right, ans, min, max, cnt;
	public static boolean check(int mid) {
		max = -MAX_VALUE;
		min = MAX_VALUE;
		cnt = 1;
		for(int i=0; i<N; i++) 
		{
			min = Math.min(min, num[i]);
			max = Math.max(max, num[i]);
			if(Math.abs(max - min) > mid) 
			{
				cnt ++;
				max = min = num[i];
			}
		}
		return cnt <= M;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, num[i]);
		}
		
		while(left <= right) {
			mid = (left + right)/2;
			if(check(mid)) {
				ans = mid;
				right = mid-1;
			}else {
				left = mid + 1;
			}
		}
		System.out.print(ans);
	}
}