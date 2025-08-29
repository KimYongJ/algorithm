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
	static final int RIGHT = 1;// 시계 방향
	static final int S = 1;// S극은 1로 입력
	static int sawTooth[][];// 톱니바퀴 마다의 극 저장
	static int changeDir[];// 각 톱니바퀴의 회전 방향 저장
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sawTooth = new int[5][8];
		changeDir = new int[5];
		
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
			changeDir[idx] = Integer.parseInt(st.nextToken());
			
			dirMark(idx);// 초기 입력을 기준으로 회전해야할 모든 톱니바퀴의 방향 세팅
			change();// 그대로 회전
		}
		
		print();// 결과 출력
	}
	static void dirMark(int idx) {
		// 왼쪽 마킹
		for(int i=idx - 1, dir = changeDir[idx]; i>=1; i--)
		{
			int l = sawTooth[i][2];// 왼쪽 톱니바퀴
			int r = sawTooth[i + 1][6];// 오른쪽 톱니바퀴
			
			dir = -dir;// 방향은 항상 반전
			
			if(l != r)// 다르면 방향 입력 및 연산 지속
			{
				changeDir[i] = dir;
				continue;
			}
			break;
		}
		// 오른쪽 마킹
		for(int i=idx + 1, dir = changeDir[idx]; i<=4; i++)
		{
			int l = sawTooth[i - 1][2];
			int r = sawTooth[i][6];
			
			dir = -dir;// 방향은 항상 반전
			
			if(l != r)// 다르면 방향 입력 및 연산 지속
			{
				changeDir[i] = dir;
				continue;
			}
			break;
		}
	}
	static void change() {
		for(int i=1; i<=4; i++)
		{
			if(changeDir[i] == LEFT)
				turnLeft(i);
			else if(changeDir[i] == RIGHT)
				turnRight(i);
			
			changeDir[i] = 0;// 이동 방향 초기화
		}
	}
	static void turnLeft(int idx) {
		int arr[] = sawTooth[idx];
		int first = arr[0];
		
		for(int i=1; i<=7; i++)
			arr[i-1] = arr[i];
		
		arr[7] = first;
	}
	static void turnRight(int idx) {
		int arr[] = sawTooth[idx];
		int last = arr[7];
		
		for(int i=7; i>=1; i--)
			arr[i] = arr[i-1];
		
		arr[0] = last;
	}
	static void print() {
		int res = 0;
		
		for(int i=1; i<=4; i++)
			if(sawTooth[i][0] == S)
			{
				res += i;
				if(i == 3) res += 1;
				if(i == 4) res += 4;
			}
		
		System.out.print(res);
	}
}
