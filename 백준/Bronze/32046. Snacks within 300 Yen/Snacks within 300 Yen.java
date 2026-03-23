//https://www.acmicpc.net/problem/32046
//8초 1024MB
//5// 간식 수 (1<=100)
//100 50 200 120 60 // 가격(1<=1000)
//4
//120 240 180 1
//2
//500 1000
//6
//2 3 5 7 11 13
//0 // 입력 끝에 0 출력
//답
//270
//300
//0
//41
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int N = Integer.parseInt(br.readLine());
			
			if(N == 0) break;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int money = 300;
			
			for(int i=0; i<N; i++)
			{
				int now = Integer.parseInt(st.nextToken());
				if(money - now >= 0)
					money -= now;
			}
			
			sb.append(300 - money).append('\n');
		}
		System.out.print(sb);
	}
}