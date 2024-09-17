//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3020
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();	// 길이 N(2<=이십만) 항상짝수
		int M		= read();	// 높이 H(2<=오십만)
		int arr[]	= new int[M+1];// 종유석을 담을 배열
		
		for(int i=0; i<N; i++)
		{
			if(i % 2 == 0)
			{
				arr[0]			+= 1;
				arr[read()] 	+= -1;
			}else
				arr[M - read()] += 1;
		}
		
		int cnt	= 0;
		int min	= arr[0];
		
		for(int i=1; i<M; i++)
		{
			arr[i] += arr[i-1];
			if(min > arr[i])
			{
				min = arr[i];
				cnt = 1;
			}
			else if(min == arr[i])
				cnt++;
		}
		if(min == arr[0])
			cnt++;
		
		System.out.print(new StringBuilder().append(min).append(' ').append(cnt));
	}
}