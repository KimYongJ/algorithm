// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	
	static int N, M, snack[], left, right, mid, ans;
	public static boolean check(int mid) {
		int people = 0;
		for(int i=N-1; i>=0; i--) 
		{
			people += snack[i]/mid;
			if(people >= M)
				return true;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M 		= Integer.parseInt(st.nextToken()); // 조카의 수 
		N 		= Integer.parseInt(st.nextToken()); // 과자의 수 
		snack 	= new int[N];
		Arrays.sort(snack);
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			snack[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, snack[i]);
		}
		left = 1;
		while(left <= right) {
			mid = (left + right) / 2;
			if(check(mid)) {
				ans = mid;
				left = mid+1;
			}else {
				right = mid-1;
			}
		}
		System.out.println(ans);
	}
}