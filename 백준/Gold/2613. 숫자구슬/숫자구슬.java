// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 	N, M, marble[], marbleCnt[], resultMarbleCnt[], 
				left, right, mid, ans;
	public static boolean check(int mid) { 
		int cnt = 1; // 그룹의 수 
		int sum = 0; // 각 그룹당 더해진 수 mid보다 크면 안된다.
		marbleCnt = new int[M]; // 각 그룹에 속한 구슬의 개수
		
		for(int i=0; i<N; i++) {
			sum += marble[i];
			if(sum > mid || ( N-i == M-cnt )) {
				cnt++;
				sum = marble[i];
			}
			if(cnt > M)
				return false;
			marbleCnt[cnt-1]++;
		}
		return cnt <= M;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		N 			= Integer.parseInt(st.nextToken());
		M 			= Integer.parseInt(st.nextToken());
		marble		= new int[N];
		marbleCnt	= new int[M];
		resultMarbleCnt = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int n=0; n<N; n++) {
			marble[n] = Integer.parseInt(st.nextToken());
			left = Math.max(marble[n], left);
		}
		right = 30_001;
		while( left <= right ) {
			mid = (left + right) / 2; // mid는 각 그룹당 나올 수 있는 합의 상한이다.
			if(check(mid)) {
				ans = mid;
				right = mid - 1;
				resultMarbleCnt = marbleCnt.clone();
			}else {
				left = mid + 1;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(ans).append('\n');
		for(int i=0; i<M; i++)
			sb.append(resultMarbleCnt[i])
				.append(' ');
		System.out.println(sb);
	}
}