//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15732
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N			= read();	// 상자개수(1<=백만)
		int K			= read();	// 규칙개수(1<=만)
		int D			= read();	// 도토리개수(1<=십억)
		int counting[]	= new int[N+1];
		int prevSum[]	= new int[N+2];
		
		for(int i=0; i<K; i++)
		{
			int A = read();	// 시작상자
			int B = read();	// 종료상자
			int C = read();	// 간격
			if(C == 1) {
				prevSum[A] += 1;
				prevSum[B + 1] += -1;
			}
			else
				while(A <= B)
				{
					counting[A]++;
					A+=C;
				}
		}
		
		for(int i=1; i<=N; i++)
			prevSum[i] += prevSum[i - 1];
		
		int Dcnt	= 0;
		int idx		= 0;
		while(idx <= N)
		{
			Dcnt += counting[idx] + prevSum[idx];
			if(Dcnt >= D)
				break;
			++idx;
		}
		System.out.print(idx);
	}
}