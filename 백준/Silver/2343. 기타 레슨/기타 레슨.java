// https://github.com/kimyongj/algorithm
class Main{
	
	static int N, M, lec[], left, mid, right, ans;
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
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
	public static void main(String[] args)throws Exception{
		N 	= read();	// 강의 개수
		M 	= read();	// 블루레이 개수
		lec	= new int[N];
		for(int i=0; i<N; i++) {
			lec[i] = read();
			right += lec[i];
			left   = Math.max(left, lec[i]);
		}
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
}
