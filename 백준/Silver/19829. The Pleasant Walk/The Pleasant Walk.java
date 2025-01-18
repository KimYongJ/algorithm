//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19829
//1초 / 512MB
//요약 : 배열의 인접한 숫자가 서로 다른 가장긴 연속된 부분수열 찾기
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 집의 수(1<=십만)
		int K		= read();	// 색상 수(1<=십만)
		int max		= 1;
		int arr[]	= new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		int s = 0;
		int e = 1;
		while(e<N)
		{
			if(arr[e] != arr[e-1])
				max = Math.max(max, e-s+1);
			else
				s = e;
			
			++e;
		}
		
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}