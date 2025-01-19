//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13144
//1초 / 32MB
//요약 : 수열에서 연속한 1개 이상 수를 뽑을 때 같은 수가 여러번 등장하지 않는 모든 경우의 수 출력
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();		// 1<=십만 
		int arr[]	= new int[N];	// 1<=십만
		int cnt[]	= new int[100_001];

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		long result	= 0;
		int s		= 0;
		int e		= 0;
		while(e<N)
		{
			int num = arr[e++];
			
			if(cnt[num]++ > 0)
			{
				while(arr[s] != num)
				{
					cnt[arr[s++]]--;
				}
				cnt[arr[s++]]--;
			}
			
			result += e - s;
			
		}
		System.out.print(result);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}