//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11046
//1초, 256MB
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		int len		= (N<<1) + 1;
		int arr[]	= new int[len];
		int A[]		= new int[len];
		
		for(int i=0,j = 0; i<N; i++,j += 2)
		{
			arr[j]	= -1;
			arr[j+1]= read();
		}
		arr[len-1] = -1;
		
		
		for(int i=0,r=0,p=0; i<len; i++)
		{
			if(i<=r)
				A[i] = Math.min(r-i, A[2*p-i]);
			
			while(0<=i-A[i]-1 && i+A[i]+1<len && arr[i-A[i]-1] == arr[i+A[i]+1])
				++A[i];
			
			if(r < i + A[i])
			{
				r = i + A[i];
				p = i;
			}
		}
		
		StringBuilder res = new StringBuilder();
		N = read();
		for(int i=0; i<N; i++)
		{
			int s = read();
			int e = read();
			int r = e - s + 1;
			res.append(A[s + e - 1] >= r ? 1 : 0).append('\n');
		}
		System.out.print(res.toString());
	}
}