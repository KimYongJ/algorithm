// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, M, lec[], left, mid, right, ans;
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 	= Integer.parseInt(st.nextToken());	// 강의 개수
		M 	= Integer.parseInt(st.nextToken());	// 블루레이 개수
		lec	= new int[N];
		st	= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			lec[i] = Integer.parseInt(st.nextToken());
			right += lec[i];
		}
		left = 1;
		while(left <= right) {
			mid = (left + right) / 2; // 블루레이에 들어갈 강의 시간
			if(check(mid)) {
				ans = mid;
				right = mid-1;
			}else {
				left = mid+1;
			}
		}
		System.out.println(ans);
	}
	public static boolean check(int mid) {
		int cnt = 0, sum = 0;
		// mid시간에 M개 안에 다 넣을 수 있느냐 ?
		for(int i=0; i<N; i++) {
			sum += lec[i];
			if(sum > mid) {
				cnt++;
				sum = lec[i];
			}
			// mid보다 강의 시간이 길면 무조건 false 리턴 
			if(cnt >= M || mid < lec[i])
				return false;
		}
		return true;
	}
}