//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15565
//1초 / 256MB
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();		//숫자의 개수(1<=10의6승)
		int K		= read();		//1의최소개수(1<=N)
		int arr[]	= new int[N];	// 슬라이딩 윈도우 활용, arr에는 1의 인덱스만 담는다.
		int len		= 0;
		int res		= 1<<30;
		
		for(int i=0; i<N; i++)
			if(read() == 1)
				arr[len++] = i;
		
		// 슬라이딩 윈도우, 0부터 K만큼 옮겨가며 길이 계산
		for(int i=K-1,j=0; i<len; i++,j++)
			res = Math.min(res, arr[i] - arr[j] + 1);
		
		System.out.print(res == (1<<30) ? -1 : res);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
