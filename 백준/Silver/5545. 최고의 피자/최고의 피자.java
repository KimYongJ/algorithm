// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 토핑 개수
		int msum	= read();		// 비용을 모두 더할 변수에 도우가격을 먼저 바인딩한다.
		int mTop	= read();		// 도우가격
		int csum	= read();		// 칼로리를 모두 더할 변수에 도우의 칼로리를 먼저 바인딩 한다.
		int cTop[]	= new int[N];	// 토핑들의 열량
		int res		= csum / msum;	// 토핑을 안 고를 수도 있기 때문에 최초 값을 바인딩
		int cal;
		
		for(int i=0; i<N; i++)
			cTop[i] = read(); 		// 각 토핑의 열량 입력
		
		Arrays.sort(cTop);			// 토핑 열량 정렬
		
		for(int i=N-1; i>=0; i--) 
		{
			csum += cTop[i];
			msum += mTop;
			cal = csum / msum;
			if(res <= cal) res = cal;
			else break;
		}
		System.out.print(res);
	}
}