// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int msum = Integer.parseInt(st.nextToken());	// 비용을 모두 더할 변수에 도우가격을 먼저 바인딩한다.
		int mTop = Integer.parseInt(st.nextToken());	// 도우가격
		int csum = Integer.parseInt(br.readLine());		// 칼로리를 모두 더할 변수에 도우의 칼로리를 먼저 바인딩 한다.
		int cTop[] = new int[N];						// 토핑들의 열량
		int res = csum / msum;							// 토핑을 안 고를 수도 있기 때문에 최초 값을 바인딩
		
		for(int i=0; i<N; i++)
		{
			cTop[i] = Integer.parseInt(br.readLine()); 	// 각 토핑의 열량 입력
		}
		
		Arrays.sort(cTop);								// 토핑 열량 정렬

		
		for(int i=N-1; i>=0; i--) 
		{
			csum += cTop[i];
			msum += mTop;
			res = Math.max(res, csum / msum);
		}
		System.out.print(res);
	}
}