// https://github.com/kimyongj/algorithm
class Main{
	
	static int 	N, M, marble[], marbleCnt[], resultMarbleCnt[], 
				left, right, mid, ans;
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
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
		return cnt == M;
	}
	public static void main(String[] args)throws Exception{
		N 				= read();
		M 				= read();
		marble			= new int[N];
		marbleCnt		= new int[M];
		resultMarbleCnt = new int[M];

		for(int n=0; n<N; n++) 
		{
			marble[n] = read();
			left = Math.max(marble[n], left);
		}
		
		right = 30_001;
		while( left <= right ) 
		{
			mid = (left + right) / 2; // mid는 각 그룹당 나올 수 있는 합의 상한이다.
			if(check(mid)) 
			{
				ans = mid;
				right = mid - 1;
				resultMarbleCnt = marbleCnt.clone();
			}
			else 
			{
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