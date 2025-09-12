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
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{

	static int T, K;
	static int dir[];// 회전 반향( 시계:1, 반시계 : -1)
	static int wheel[][];// 현재 톱니의 모양
	static int wheelTop[];// 각 톱니마다의 12시 방향의 인덱스
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		dir = new int[T];
		wheel = new int[T][8];
		wheelTop = new int[T];
		
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
			Arrays.fill(dir, 0);
			int idx = Integer.parseInt(st.nextToken()) - 1;
			dir[idx] = Integer.parseInt(st.nextToken());
			
			checkLeft(idx);
			checkRight(idx);
			turnAll();
		}
		
		int cnt = 0;
		
		for(int i = 0; i < T; i++)
			if(wheel[i][wheelTop[i]] == 1)
				++cnt;
		
		System.out.print(cnt);
	}
	static void checkRight(int idx) {
		int nextIdx = idx + 1;
		
		if(nextIdx == T)
			return;
		
		int right = (wheelTop[idx] + 2) % 8;
		int left = (wheelTop[nextIdx] - 2 + 8) % 8;
		
		if(wheel[idx][right] != wheel[nextIdx][left])
		{
			dir[nextIdx] = -dir[idx];
			checkRight(nextIdx);
		}
	}
	static void checkLeft(int idx) {
		if(idx == 0)
			return;
		
		int nextIdx = idx - 1;
		
		int right = (wheelTop[nextIdx] + 2) % 8;
		int left = (wheelTop[idx] - 2 + 8) % 8;
		
		if(wheel[nextIdx][right] != wheel[idx][left])
		{
			dir[nextIdx] = -dir[idx];
			checkLeft(nextIdx);
		}
	}
	static void turnAll() {
		for(int i=0; i<T; i++)
			if(dir[i] == 1)// 시계방향
				wheelTop[i] = (wheelTop[i] - 1 + 8) % 8;
			else if(dir[i] < 0)// 반시계 방향
				wheelTop[i] = (wheelTop[i] + 1) % 8;
	}
}