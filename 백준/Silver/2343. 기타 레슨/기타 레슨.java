// https://github.com/kimyongj/algorithm
class Main{
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void main(String[] args)throws Exception{
		int N, M, lec[], left, mid, right, cnt, sum;
		N 	 = read();	// 강의 개수
		M 	 = read();	// 블루레이 개수
		lec	 = new int[N];
		left = right = 0;
		for(int i=0; i<N; i++) {
			lec[i] = read();
			right += lec[i];
			left   = Math.max(left, lec[i]);
		}
		while(left <= right) {
			mid = (left + right) / 2; // 블루레이에 들어갈 강의 시간
			cnt = sum = 0;
			for(int n : lec) {
				if(mid < n) {
					cnt = M; 
					break;
				}
				if((sum += n) > mid) {
					cnt++;
					sum = n;
				} 
			}
			if(cnt >= M)
				left = mid+1;
			else 
				right = mid-1;
		}
		System.out.println(left);
	}
}
