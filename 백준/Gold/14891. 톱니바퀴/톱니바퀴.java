//https://www.acmicpc.net/problem/14891
//2초 512MB
//10101111// 1번 톱니의 상태(8개의 정수로 이뤄져있으며 12시 방향부터 시계방향 순으로 주어짐)
//01111101// 2번 톱니의 상태(N극은 0, S극은 1)
//11001110// 3번 톱니의 상태
//00000010// 4번 톱니의 상태
//2 // 회전 횟수(1<=100)
//3 -1// 회전시킨 톱니바퀴의 번호 : 방향(1은 시계, -1은 반시계)
//1 1
//K번 회전시킨 후 네 톱니바퀴의 점수의 합을 출력한다 : 7
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static final int LEFT = -1;// 반시계 방향
	static final int S = 1;// S극은 1로 입력
	static int sawTooth[][];// 톱니바퀴 마다의 극 저장
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sawTooth = new int[5][8];
		
		for(int i=1; i<=4; i++)
		{
			String str = br.readLine();
			for(int j=0; j<str.length(); j++)
				sawTooth[i][j] = str.charAt(j) - '0';
		}
		
		int K = Integer.parseInt(br.readLine());
		
		while(K-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			leftCheck(idx - 1, dir);// dfs로 왼쪽 톱니바퀴 탐색 및 회전
			rightCheck(idx + 1, dir);// dfs로 오른쪽 톱니바퀴 탐색 및 회전
			turn(idx, dir == LEFT);// 현재 톱니 회전
		}
		
		print();// 결과 출력
	}
	static void rightCheck(int idx, int dir) {
		int nowDir = -dir;
		
		if(idx == 5)return;
		if(sawTooth[idx - 1][2] == sawTooth[idx][6])return;
		
		rightCheck(idx + 1, nowDir);
		
		turn(idx, nowDir == LEFT);
	}
	static void leftCheck(int idx, int dir) {
		int nowDir = -dir;
		
		if(idx == 0)return;
		
		if(sawTooth[idx][2] == sawTooth[idx + 1][6])return;
		
		leftCheck(idx - 1, nowDir);
		
		turn(idx, nowDir == LEFT);
	}
	static void turn(int idx, boolean isLeft) {
		int arr[] = sawTooth[idx];
		if(isLeft)
		{
			int first = arr[0];
			
			for(int i=1; i<=7; i++)
				arr[i-1] = arr[i];
			
			arr[7] = first;
			return;
		}
		
		int last = arr[7];
		
		for(int i=7; i>=1; i--)
			arr[i] = arr[i-1];
		
		arr[0] = last;
	}
	static void print() {
		int res = 0;
		
		for(int i=1; i<=4; i++)
		{
			if(sawTooth[i][0] == S)
			{
				res += i;
				if(i == 3) res += 1;
				if(i == 4) res += 4;
			}
		}
		System.out.print(res);
	}
}
