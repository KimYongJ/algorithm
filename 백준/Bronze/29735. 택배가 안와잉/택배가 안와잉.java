//https://www.acmicpc.net/problem/29735
//1초 1024MB
//09:00 16:00 // 출근 시간, 퇴근시간 HH:MM 형태(0≤HH≤23; 0≤MM59)
//3 15 // 먼저 배송해야할 택배 수(0<=10,000), 한건 배달에 걸리는 시간(1<=60)
//답
//0 // 며칠 후 도착하는지 출력
//10:00 // 몇시 몇분에 도착하는지 HH:MM형태로 출력

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String start[] = st.nextToken().split(":");
		String end[] = st.nextToken().split(":");
		
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());// 먼저 배송해야할 택배 수(0<=10,000)
		int t = Integer.parseInt(st.nextToken());// 한건 배달에 걸리는 시간(1<=60)
		
		int s = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]); // 출근 시간
		int e = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);// 퇴근 시간
		
		int cap = (e - s - 1) / t; // 하루 배송 가능 건수
		int day = n / cap; // 배송 일자
		int remain = n % cap + 1;// 당일 남은 개수
		
		int time = s + remain * t;
		
		System.out.printf("%d\n%02d:%02d", day, time / 60, time % 60);
	}
}