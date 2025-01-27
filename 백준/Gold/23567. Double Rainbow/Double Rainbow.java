//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/23567
//1초 / 1024MB

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	//점의 수 1<=만
		int K		= read();	//점에 입력될 숫자의 수 1<=N
		int arr[]	= new int[N];
		int cnt[]	= new int[K+1];
		int origin[]= new int[K+1];
		
		for(int i=0; i<N; i++)
			++origin[arr[i] = read()];
		
		int s		= 0;
		int e		= -1;
		int cntK	= 0;
		int minLen	= Integer.MAX_VALUE;
		
		while(++e < N)
		{
			if(cnt[arr[e]]++ == 0)
				++cntK;
			
			while(K == cntK)
			{
				if(validate(origin, cnt))
					minLen = Math.min(minLen, e - s + 1);
				
				if(--cnt[arr[s++]] == 0)
					--cntK;
			}
		}
		
		if(minLen == Integer.MAX_VALUE)
			minLen = 0;
		
		System.out.print(minLen);
	}
	public static boolean validate(int o[], int c[]) {
		for(int i=1; i<o.length; i++)
			if(o[i] == c[i])
				return false;
		return true;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}