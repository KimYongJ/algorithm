// https://github.com/kimyongj/algorithm

class Main{
	
	static int N, M, jewel[], left, mid, right;
	public static void main(String[] args)throws Exception{
		N 		= read();
		M 		= read();
		jewel 	= new int[M];
		for(int i=0; i<M; i++) {
			jewel[i] = read();
			right = Math.max(right, jewel[i]);
		}
		left = 1;
		while(left < right) {
			mid = (left + right) / 2;
			if(check(mid)) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		System.out.println(right);
	}
	public static boolean check(int mid) {
		int people = 0;
		for(int j : jewel) {
			people += j/mid;
			people += j%mid == 0 ? 0 : 1;
			if(people > N)
				return false;
		}
		return true;
	}
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
}