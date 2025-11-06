//https://www.acmicpc.net/problem/14646
// 2초 256MB
//3 // 메뉴의 수(1<=100,000)
//1 3 3 2 1 2 // 뽑은 순서대로 칸에 적힌 메뉴번호가 주어짐
//답 : 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		int max = 0;
		boolean visit[] = new boolean[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N*2; i++)
		{
			int n = Integer.parseInt(st.nextToken());
			
			if(!visit[n])
			{
				++cnt;
				visit[n] = true;
				max = Math.max(max,cnt);
			}
			else
			{
				--cnt;
				visit[n] = false;
			}
		}
		System.out.print(max);
	}
}