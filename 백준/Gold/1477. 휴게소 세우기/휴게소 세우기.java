// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, M, L, left, right, mid, ans;
	static boolean map[];
	public static boolean check(int mid,int m) {
		int cnt = 1;
		for(int i=1; i<=L; i++) {
			if(map[i]) 
			{
				cnt = 1;
			}
			else if(cnt == mid) 
			{
				if(i!=L && m==0){
					return false;
				}
				m--;
				cnt = 1;
			}else cnt++;
		}
		return true;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 	= Integer.parseInt(st.nextToken());
		M 	= Integer.parseInt(st.nextToken());
		L 	= Integer.parseInt(st.nextToken());
		map = new boolean[L+1];
		st 	= new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++)
			map[Integer.parseInt(st.nextToken())] = true;
		
		left  = 1;
		right = 1000;
		
		while(left <= right) {
			mid = (left + right) / 2;
			if(check(mid,M)) {
				ans = mid;
				right = mid-1;
			}else{
				left = mid+1;
			}
		}
		System.out.println(ans);
	}
}
