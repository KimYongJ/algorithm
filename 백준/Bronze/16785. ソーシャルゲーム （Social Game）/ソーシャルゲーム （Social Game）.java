//https://www.acmicpc.net/problem/16785
//2초 512MB
//3 0 10 // 로그인시 얻는 코인, 7일 연속 로그인시 획득 코인, 목표 코인
//답 : 4
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());// 로그인시 얻는 코인
		int B = Integer.parseInt(st.nextToken());// 7일 연속 로그인시 획득 코인
		int C = Integer.parseInt(st.nextToken());// 목표 코인
		int now = 0;
		int res = 0;
		
		while(now < C)
		{
			now += A;
			
			if(++res % 7 == 0)
				now += B;
		}
		System.out.print(res);
	}
}