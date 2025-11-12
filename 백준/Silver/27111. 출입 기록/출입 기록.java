//https://www.acmicpc.net/problem/27111
//1초 1024MB
//8 // 출입 기록수 N(1<=200,000)
//1 1 // 사람번호(1<=200,000), 명령어 1은 in, 0은 out
//2 1
//1 1
//4 1
//3 0
//5 1
//4 0
//1 0
//누락 기록의 최소개수 : 4
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int res = 0;
		boolean visit[] = new boolean[200_001];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int o = Integer.parseInt(st.nextToken());
			
			if((visit[n] && o == 1) || (!visit[n]&& o == 0))
				++res;
			else
				visit[n] = o == 1;
		}
		for(int i=1; i<=200_000; i++)
			if(visit[i])
				++res;
		
		System.out.print(res);
	}
}