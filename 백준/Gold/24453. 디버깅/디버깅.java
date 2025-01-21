//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/24453
//0.6초 / 512MB
//요약 : 연속된 숫자를 X개 이상 만들기 위해 필요한 최소 개수
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 총 코드줄수(1<=이천만)
		int M		= Integer.parseInt(st.nextToken());	// 오류가 있는 줄 수(1<=min(N,오십만))
		int ans		= Integer.MAX_VALUE;
		int errCnt	= 0;// 고친에러 개수
		int connect = 0;// 연속된 숫자 개수
		boolean v[]	= new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			v[Integer.parseInt(st.nextToken())] = true;// 고쳐야되는 것은 true

		st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());// 오류가 없는 X줄이상 존재시 나머지 해결(0≤N)
		int Y = Integer.parseInt(st.nextToken());// 최소로 찾을 오류 개수 Y(0<=M)
		
		if(N == 1)
		{
			System.out.print(M - Y);
			return;
		}
		int s = 1;
		int e = 1;
		while(true)
		{
			if(s == N)
				break;
			
			if(!(X <= connect && Y<= errCnt) && e <= N)	// 고쳐야 되는 경우
			{
				if(v[e++])
					errCnt++;
				connect++;
			}
			else
			{
				if(X <= connect && Y<= errCnt)
					ans = Math.min(ans, errCnt);
				if(v[s++])
					--errCnt;
				--connect;
			}
		}
		System.out.print(M - ans);
	}
}
