// https://github.com/kimyongj/algorithm
class Main{
	static int N, K, total, cnt, score[];
	static int left, mid, right, ans;
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static boolean check(int mid) {
		total = cnt = 0;
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
		N 		= read(); // 시험지 개수 
		K 		= read(); // 그룹의 수 
		score 	= new int[N];
		for(int i=0; i<N; i++)
			score[i] = read();
		
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