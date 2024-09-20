//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6209
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int D		= read();		// 총거리(1<=십억)
		int N		= read()+2;		// 돌섬수(0<=오만)
		int M		= read();		// 제거 할 돌섬수(0<=N)
		int arr[]	= new int[N];	// 시작으로부터 떨어진 섬의 위치
		arr[N-1]	= D;			// 마지막거리 삽입
		
		for(int i=1; i<N - 1; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		int res	= 0;
		int s	= 0;
		int e	= D;
		
		while(s <= e)
		{
			int mid			= (s + e) >> 1; // 돌과 돌섬사이 최소 거리
			int deleteCnt	= 0;
			int startIdx	= 0;
			
			for(int i=1; i<N; i++)
			{
				if(arr[i] - arr[startIdx] < mid)
					deleteCnt++;
				else
					startIdx = i;
			}
			
			if(deleteCnt <= M) // 해당 최소거리로 갈 수 있다면
			{
				s = mid + 1;
				res = mid;
			}else
				e = mid - 1;
		}
		
		System.out.print(res);
	}
}
/*
24 3 2
5
10
15
답 : 10
 * */
