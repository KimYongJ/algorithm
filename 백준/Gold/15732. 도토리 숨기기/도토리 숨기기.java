//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15732
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 상자개수(1<=백만)
		int K		= read();		// 규칙개수(1<=만)
		int D		= read();		// 도토리개수(1<=십억)
		int arr[][]	= new int[K][3];// 규칙을 담을 배열
		
		for(int i=0; i<K; i++)
		{
			arr[i][0] = read();		// 시작상자
			arr[i][1] = read();		// 종료상자
			arr[i][2] = read();		// 간격
		}

		int s	= 1;
		int e	= N;
		int idx = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;	// 상자 인덱스
			int d	= 0;			// 도토리개수
			
			for(int i=0; i<K && d < D; i++)
				if(mid >= arr[i][0])// 탐색할 상자인덱스가 규칙의 시작 인덱스보다 작다면 스킵, 아니면 연산함
					d += ((Math.min(mid,arr[i][1]) - arr[i][0]) / arr[i][2]) + 1;
			
			if(D <= d)
			{
				e = mid - 1;
				idx = mid;
			}
			else
			{
				s = mid + 1;
			}
		}
		System.out.print(idx);
	}
}