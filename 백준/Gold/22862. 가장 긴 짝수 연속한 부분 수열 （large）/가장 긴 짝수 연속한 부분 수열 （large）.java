//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/22862
// 1초 / 1024MB
// 수열에서 읨의로 K번 수를 삭제한 후 짝수로 이뤄진 연속한 부분 수열 중 가장 긴 길이 출력
// 비슷한 백준 문제 : 22857
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();// 수열길이(1<=백만)
		int K		= read();// 최대 삭제 횟수(1<=십만)
		int arr[]	= new int[N];

		for(int i=0; i<N; i++)
			arr[i] = read() % 2;
		
		int s	= 0;
		int e	= 0;
		int k	= 0;
		int max = 0;
		
		while(e < N)
		{
			if(arr[e++] == 1)
				++k;
			
			max = Math.max(max, e - s - k);
			
			while(K<k)
				if(arr[s++] == 1)
					--k;
		}
		System.out.print(max);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
