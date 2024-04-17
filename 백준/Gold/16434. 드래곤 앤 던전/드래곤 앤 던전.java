// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static long N, at, left, mid, right, ans, map[][];
	public static boolean check(long MAX) {
		long energy = MAX;	// 현재 체력
		long power = at;	// 현재 공력력
		long cntWin;
		long cntLose;
		for(long m[] : map) {
			if(m[0] == 2) {
				energy = Math.min(MAX, energy + m[2]); 	// 에너지 추가 
				power += m[1];							// 공격력 업그레이드
			}else {
				cntWin  = (m[2] / power) + (m[2] % power == 0 ? 0 : 1);
				cntLose = (energy  / m[1])  + (energy  % m[1]  == 0 ? 0 : 1); 
				if(cntWin <= cntLose) {
					energy -=  --cntWin * m[1];
				}else {
					return false;
				}
			}
		}
		return energy != 0;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 	= Integer.parseInt(st.nextToken());
		at	= Integer.parseInt(st.nextToken());
		map = new long[(int)N][3];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
			map[i][2] = Integer.parseInt(st.nextToken());
		}
		right = 1000000000000000000L;

		while(left<=right) {
			mid = (left + right)/2;
			if(check(mid)) {
				ans = mid;
				right = mid-1;
			}else {
				left = mid+1;
			}
		}
		System.out.println(ans);
	}
}