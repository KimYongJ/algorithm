//https://www.acmicpc.net/problem/16463
//1초 512MB
//2021 // 목표년도(2019<=100,000)
//누적되는 13일 금요일 개수 : 5
//윤년 : 년도가 400의 배수이거나, 100의 배수가 아니면서 4의 배수인 연도는 윤년이다.
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int days[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int N = Integer.parseInt(br.readLine());
		int res = 0;
		int week = 1; // 초기 화요일(1)
		
		for(int year=2019; year<=N; year++)
		{
			for(int i=0; i<days.length; i++)
			{
				int day = days[i];
				if(i == 1 && (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)))
					day += 1;
				
				for(int j=1; j<=day; j++)
				{
					if(week == 4 && j == 13)
						++res;
					week = (week + 1) % 7;
				}
			}
			
		}
		
		System.out.print(res);
	}
}