// https://github.com/kimyongj/algorithm

class Main{
	static int N, M, num[], left , mid, right, ans, min, max, cnt;
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean check(int mid) {
		max = num[0];
		min = num[0];
		cnt = 1;
		for(int i=1; i<N; i++) 
		{
			if(min > num[i]) min = num[i];
			if(max < num[i]) max = num[i];
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

		for(int i=0; i<N; i++) {
			num[i] = read();
			if(right < num[i])
				right = num[i];
		}
		
		
		while(left <= right) 
		{
			mid = (left + right)/2;
			if(check(mid))	{
				ans	  = mid;
				right = mid-1;
			}
			else{
				left = mid + 1;
			}
		}
		System.out.print(ans);
	}
}