//https://www.acmicpc.net/problem/17225
//1초 512MB
//주문 시간이 0이란 것은, 주문과 동시에 모든 선물 포장이 끝남을 의미
//2 3 4 // (파랑) 시간(0<=300), (빨강)시간(0<=300), 손님수(1<=1000)
//1 B 3 // 주문시간(1<=86,400), 색깔, 선물 개수(1<=100)
//4 R 2
//6 B 2
//12 R 1
//답
//5// (파랑) 포장한 선물 개수 출력
//1 2 4 5 7// 포장한 선물 번호를 오른차순으로 공백으로 구분 출력
//3// (빨강) 포장한 선물 개수 출력
//3 6 8// 포장한 선물 번호를 오른차순으로 공백으로 구분 출력

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	
	static int number;
	static StringBuilder redSb, blueSb;
	static ArrayDeque<Node> red, blue;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		blue = new ArrayDeque<>();
		red = new ArrayDeque<>();
		redSb = new StringBuilder();
		blueSb = new StringBuilder();
		
		int Btime = Integer.parseInt(st.nextToken());
		int Rtime = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int Bcnt = 0;
		int Rcnt = 0;
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char color = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			
			if(color == 'B')
			{
				Bcnt += cnt;
				blue.add(new Node(time, cnt));
			}
			else
			{
				Rcnt += cnt;
				red.add(new Node(time, cnt));
			}
		}
		
		int workTimeB = 0;
		int workTimeR = 0;
		
		while(!blue.isEmpty() || !red.isEmpty())
		{
			int tb = nextTime(blue, workTimeB);
			int tr = nextTime(red, workTimeR);
			if(tb <= tr)
				workTimeB = cal(blue, blueSb, Btime, workTimeB);
			else
				workTimeR = cal(red, redSb, Rtime, workTimeR);

		}

		StringBuilder sb = new StringBuilder();

		sb.append(Bcnt).append('\n')
			.append(blueSb.toString()).append('\n')
			.append(Rcnt).append('\n')
			.append(redSb.toString());

		System.out.print(sb);
	}
	static int nextTime(ArrayDeque<Node> q, int workTime) {
		return q.isEmpty() ? Integer.MAX_VALUE : Math.max(q.peekFirst().targetTime, workTime);
	}
	static int cal(ArrayDeque<Node> q, StringBuilder sb, int plusTime, int workTime) {

		Node n = q.peekFirst();

		sb.append(++number).append(' ');
		
		if(--n.count == 0) q.pollFirst();
		
		return Math.max(n.targetTime, workTime) + plusTime;
	}
	static class Node{
		int targetTime, count;
		Node(int t, int c){
			targetTime = t;
			count = c;
		}
	}
}