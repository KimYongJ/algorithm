//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/30804
//2초 / 1024MB
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	//개수(1<=이십만)
		int arr[]	= new int[N];
		int dp[]	= new int[10];
		int max		= 0;
		
		for(int s=0, e=0, cnt = 0; e<N; e++)
		{
			arr[e] = read();	// 1<=9사이 정수
			if(dp[arr[e]]++ == 0)
				++cnt;
			
			while(cnt == 3)
				if(--dp[arr[s++]] == 0)
					--cnt;

			max = Math.max(max, e - s + 1);
		}
		
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}