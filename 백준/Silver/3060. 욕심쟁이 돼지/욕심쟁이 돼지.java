//https://www.acmicpc.net/problem/3060
//1초 128MB
//2 // 테스트케이스 개수
//21 // 하루 배달되는 사료의 양(1<=5억)
//1 2 3 4 5 6 // 첫날 제공된 사료의 양
//21
//1 2 3 4 5 7
//답
//2
//1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());// 하루 배달되는 사료의 양(1<=5억)
			int S = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<6; i++) S += Integer.parseInt(st.nextToken());
			
			int day = 1;
			
			while(S<=N)
			{
				S *= 4;
				day++;
			}
			sb.append(day).append('\n');
		}
		System.out.print(sb);
	}
}