//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1300
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N		= Integer.parseInt(br.readLine());	// 배열크기 N (1<=십만)
		long K		= Integer.parseInt(br.readLine());	// k(십억 <= 백억)
		long res	= 0;								// 결과
		long s		= 1;								// 탐색 시작값
		long e		= K;								// 탐색 종료값, 특정 수 X는 K를 넘을 수 없다.
		
		while(s <= e)
		{
			long mid = (s + e) >> 1;	// mid보다 작은 숫자의 개수를 찾는다.
			long cnt = 0;
			for(int i=1; i<=N; i++)		// 1부터 N단까지 mid로 나누었을 때 개수
				cnt += Math.min(mid / i, N);
			
			if(K <= cnt)
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		
		System.out.print(res);
	}
}
