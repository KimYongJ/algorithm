// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, K, total, cnt, score[];
	static int left, mid, right, ans;
	public static boolean check(int mid) {
		total = 0;
		cnt = 0;
		for(int s : score) {
			total += s;
			if(total >= mid) {
				total = 0;
				cnt++;
			}
		}
		return cnt >= K;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken()); // 시험지 개수 
		K 		= Integer.parseInt(st.nextToken()); // 그룹의 수 
		score 	= new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			score[i] = Integer.parseInt(st.nextToken());
		
		right = 3_000_000;
		while(left <= right){
			mid = (left + right) / 2;
			if(check(mid)) {
				left = mid + 1;
				ans = mid;
			}else
				right = mid-1;
		}
		
		System.out.println(ans);
	}
}