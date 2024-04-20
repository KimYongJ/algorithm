// https://github.com/kimyongj/algorithm

class Main{
	static final int MAX_VALUE = 10_001;
	static int N, M, num[], left , mid, right, min, max, cnt;
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean check(int mid) {
		max = -MAX_VALUE;
		min = MAX_VALUE;
		cnt = 1;
		for(int i=0; i<N; i++) 
		{
			min = Math.min(min, num[i]);
			max = Math.max(max, num[i]);
			if(Math.abs(max - min) > mid) 
			{
				cnt ++;
				max = min = num[i];
			}
		}
		return cnt <= M;
	}
	public static void main(String[] args)throws Exception{
		N 	= read();
		M 	= read();
		num = new int[N];

		for(int i=0; i<N; i++)
			num[i] = read();
		
		right = MAX_VALUE;
		
		while(left < right) 
		{
			mid = (left + right)/2;
			if(check(mid))	right	= mid;
			else			left	= mid + 1;
		}
		System.out.print(right);
	}
}