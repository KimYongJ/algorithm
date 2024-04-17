// https://github.com/kimyongj/algorithm
class Main{
	
	static int N, M, L, left, right, mid;
	static boolean map[];
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static boolean check(int mid,int m) {
		int cnt = 1;
		for(int i=1; i<=L; i++) {
			if(map[i]) 
			{
				cnt = 1;
			}
			else if(cnt == mid) 
			{
				if(i!=L && m==0){
					return false;
				}
				m--;
				cnt = 1;
			}else cnt++;
		}
		return true;
	}
	public static void main(String[] args)throws Exception{
		N 	= read();
		M 	= read();
		L 	= read();
		map = new boolean[L+1];
		
		for(int i=0; i<N; i++)
			map[read()] = true;
		
		left  = 1;
		right = 1000;
		
		while(left <= right) {
			mid = (left + right) / 2;
			if(check(mid,M))
				right = mid-1;
			else
				left = mid+1;
		}
		System.out.println(left);
	}
}
