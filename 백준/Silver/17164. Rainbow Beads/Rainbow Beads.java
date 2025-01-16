//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/17164
// 1초 / 1024MB
// 요약 : R,B,V가 주어지고 RBV모두구별하는사람과 V를B로보는사람, V를R로보는 사람들이 보는 아름다운 구슬의 최대길이 출력
// 아름다운 구슬이란? 같은색이 인접하지 않은 구간
// 해설 : vr이붙어있거나, vb가 붙어있으면안된다, 가능한 경우는 r과b만 붙어있을 경우 뿐이다.
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[]args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int result	= 0;
		char[] o	= br.readLine().toCharArray();
		
		int cnt = 1;
		for(int i=1; i<N; i++)
		{
			if((o[i] == 'R' && o[i-1] == 'B') || (o[i] == 'B' && o[i-1] == 'R'))
				++cnt;
			else
			{
				result = Math.max(result, cnt);
				cnt = 1;
			}
		}
		System.out.print(Math.max(result, cnt));
	}
}
