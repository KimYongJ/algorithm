//https://www.acmicpc.net/problem/25826
//3초 512MB
//4 3//  사각형크기(1<=1,000), 질의 수(1<=300,000)
//1 1 1 1// 크기만큼 사각형의 기본값이 주어짐(1<=1,000,000)
//1 1 1 1
//1 1 1 1
//1 1 1 1
//1 0 0 1 1 1// 1번 쿼리 : 왼쪽위, 오른쪽아래가 주어지며 행, 열 순으로 주어짐
//1 0 0 2 2 2
//2 0 0 2 2// 2번 쿼리 : 2번쿼리는 딱 한번만 나오며 해당 격자안의 값만 더해서 출력

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 사각형크기(1<=1,000)
		int M = Integer.parseInt(st.nextToken());// 질의 수(1<=300,000)
		long origin[][] = new long[N + 2][N + 2];
		long diff[][] = new long[N + 2][N + 2]; // 차분 배열
		
		for(int y=1; y<=N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
				origin[y][x] = Integer.parseInt(st.nextToken());
		}
		
		// 구간 질의를 입력받으며 차분 배열 4점에 마킹한다.
		for(int i=1; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int y1 = Integer.parseInt(st.nextToken()) + 1;
			int x1 = Integer.parseInt(st.nextToken()) + 1;
			int y2 = Integer.parseInt(st.nextToken()) + 1;
			int x2 = Integer.parseInt(st.nextToken()) + 1;
			int k = Integer.parseInt(st.nextToken());
			// 차분배열의 네점에 마킹
			diff[y1][x1] += k;
			diff[y1][x2 + 1] -= k;
			diff[y2 + 1][x1] += -k;
			diff[y2 + 1][x2 + 1] += k;
		}
		// 차분배열을 계산하기 위해 가로만 먼저 돌면서 다더함
		for(int y=1; y<=N; y++)
			for(int x=1; x<=N; x++)
				diff[y][x] += diff[y][x-1];
		// 차분 배열의 세로를 돈다.
		for(int x=1; x<=N; x++)
			for(int y=1; y<=N; y++)
				diff[y][x] += diff[y-1][x];

		// 마지막 목표 값을 입력받아 출력한다.
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		int y1 = Integer.parseInt(st.nextToken()) + 1;
		int x1 = Integer.parseInt(st.nextToken()) + 1;
		int y2 = Integer.parseInt(st.nextToken()) + 1;
		int x2 = Integer.parseInt(st.nextToken()) + 1;
		long result = 0;
		
		for(int y=y1; y<=y2; y++)
			for(int x=x1; x<=x2; x++)
				result += origin[y][x] + diff[y][x];
		
		System.out.print(result);
	}
}