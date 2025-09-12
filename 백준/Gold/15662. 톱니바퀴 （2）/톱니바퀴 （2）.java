//https://www.acmicpc.net/problem/15662
//2초 512MB
//4 // 톱니바퀴 수(1<=1000)
//10101111// 톱니바퀴의 상태로 12시 방향부터 시계방향 순서대로 주어집니다. 0은 N, 1은 S극 입니다.
//01111101
//11001110
//00000010
//2 // 회전 횟수(1<=1000)
//3 -1 // 회전시킨 톱니바퀴 번호, 방향( 시계:1, 반시계:-1)
//1 1
//회전이 끝난 후 12시방향이 S극인 톱니의 개수를 출력 : 3

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static final int LEFT = 6;
	static final int RIGHT = 2;
	static int T, K;
	static int wheel[][];// 현재 톱니의 모양
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		wheel = new int[T][8];
		
		for(int i=0; i<T; i++)
		{
			String str = br.readLine();
			for(int j=0; j<8; j++)
				wheel[i][j] = str.charAt(j) - '0';
		}
		
		K = Integer.parseInt(br.readLine());
		while(K-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			
			checkLeft(idx, dir);
			checkRight(idx, dir);
			turn(idx, dir);
		}
		
		int cnt = 0;
		
		for(int i = 0; i < T; i++)
			if(wheel[i][0] == 1)
				++cnt;
		
		System.out.print(cnt);
	}
	static void checkRight(int idx, int dir) {
		int rightIdx = idx + 1;
		
		if(rightIdx == T)
			return;
		
		if(wheel[idx][RIGHT] != wheel[rightIdx][LEFT])
		{
			checkRight(rightIdx, -dir);
			turn(rightIdx, -dir);
		}
	}
	static void checkLeft(int idx, int dir) {
		if(idx == 0)
			return;
		
		int leftIdx = idx - 1;
		
		if(wheel[leftIdx][RIGHT] != wheel[idx][LEFT]) {
			checkLeft(leftIdx, -dir);
			turn(leftIdx, -dir);
		}
	}
	static void turn(int idx, int dir) {
		if(dir == 1)// 시계방향
			turnRight(wheel[idx]);
		else if(dir < 0)// 반시계 방향
			turnLeft(wheel[idx]);
	}
	static void turnRight(int[] arr) {
		int last = arr[7];
		for(int i=6; i>=0; i--)
			arr[i + 1] = arr[i];
		arr[0] = last;
	}
	static void turnLeft(int[] arr) {
		int first = arr[0];
		for(int i=0; i<7; i++)
			arr[i] = arr[i + 1];
		arr[7] = first;
	}
}