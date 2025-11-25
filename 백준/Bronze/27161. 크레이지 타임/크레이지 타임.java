//https://www.acmicpc.net/problem/27161
//1초 1024MB
//7 // 카드 개수(1<=1000)
//WATCH 4 // 시계정보 + 적힌 시간(1<=12)
//CLOCK 2
//HOURGLASS 3 // 모래시계
//CLOCK 5
//HOURGLASS 3
//WATCH 2
//CLOCK 3
//각차례에 플레이어가 외치는 시각과 행동을 출력
//1 NO // 게임 중앙판을 내리쳐야 한다면 YES, 아니면 NO
//2 YES
//3 NO
//4 NO
//5 NO
//4 NO
//3 YES

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int time = 1;
		int plus = 1;
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean reverse = "HOURGLASS".equals(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			boolean yes = false;
			
			if(!(reverse && num == time))
			{
				if(time == num)
				{
					yes = true;
				}
				if(reverse)
				{
					plus = -plus;
				}
			}

			sb.append(time).append(' ').append(yes ? "YES" : "NO").append('\n');
			
			time += plus;
			
			if(time == 13) time = 1;
			else if(time == 0) time = 12;
		}
		System.out.print(sb);
	}
}