//https://www.acmicpc.net/problem/30648
//0 2 // 좌표 a,b
//6 // 기준 값(1≤R≤10^3)
//답 : 7
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(br.readLine());
		boolean visit[][] = new boolean[r][r];
		
		visit[a][b] = true;
		
		int time = 0;
		
		while(true)
		{
			++time;
			if(a + b + 2 < r)
			{
				++a;
				++b;
			}
			else
			{
				a >>= 1;
				b >>= 1;
			}
			
			if(visit[a][b])
				break;
			
			visit[a][b] = true;
		}
		System.out.print(time);
	}
}
