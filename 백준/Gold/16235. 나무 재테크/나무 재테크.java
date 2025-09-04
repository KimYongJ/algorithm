//https://www.acmicpc.net/problem/16235
//0.3초 512MB
//5 2 1// 맵크기(1<=10), 나무개수(1<=N^2), 목표년수(1<=1000)
//2 3 2 3 2 // N개 줄에 배열 A의 값이 주어진다.
//2 3 2 3 2
//2 3 2 3 2
//2 3 2 3 2
//2 3 2 3 2
//2 1 3 // 나무 개수 만큼 나무정보가 주어지며 y, x, 나무의 나이가 주어진다.(1<=100)
//3 2 3
//목표년수가 지난 후 살아남은 나무의 수를 출력 : 2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
	static int N, T, K;
	static int[][] plus, food, addTree;
	static ArrayList<Integer>[][] init;
	static ArrayDeque<Integer>[][] map;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 맵크기(1<=10)
		T = Integer.parseInt(st.nextToken());// 나무개수(1<=N^2)
		K = Integer.parseInt(st.nextToken());// 목표년수(1<=1000)
		plus = new int[N][N];// 매년 추가될 양분
		food = new int[N][N];// 현재 양분
		addTree = new int[N][N];// 가을에 번식하는 나무를 담음
		map = new ArrayDeque[N][N];// 현재나무의 나이를 담음
		init = new ArrayList[N][N];// 최초 나무를 담음
		
		for(int y=0; y<N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
			{
				plus[y][x] = Integer.parseInt(st.nextToken());
				food[y][x] = 5;
				map[y][x] = new ArrayDeque<>();
				init[y][x] = new ArrayList<>();
			}
		}
		
		for(int i=0; i<T; i++)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());// 나무 나이
			init[y][x].add(z);// 최초 나무 나이를 담음
		}
		
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
			{
				if(init[y][x].size() > 1)// 나무나이 정렬
					Collections.sort(init[y][x]);
				
				for(int age : init[y][x])
					map[y][x].addLast(age);// 나무 나이를 오름차순으로 map에 담음
			}
		}
		
		while(K-->0)
		{
			springAndSummer();
			fallAndWinter();
		}
		
		System.out.print( count() );
	}
	static void fallAndWinter()
	{
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
			{
				int size = map[y][x].size();
				while(size-->0)
				{
					int age = map[y][x].pollFirst();
					
					if(age % 5 == 0)
						treeSet(y, x);
					
					map[y][x].addLast(age);
				}
			}
		}
		
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
			{
				while(addTree[y][x]-- > 0)// 추가된 나무만큼 그곳에 추가한다.
					map[y][x].addFirst(1);
				
				addTree[y][x] = 0;
				// winter 로직
				food[y][x] += plus[y][x];
			}

	}
	static void treeSet(int y, int x) {
		for(int xy[] : dxy)
		{
			int ny = y + xy[0];
			int nx = x + xy[1];
			if(0<=ny && 0<=nx && ny<N && nx<N)
				addTree[ny][nx]++;
		}
	}
	static void springAndSummer() {
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
			{
				boolean isSummer = false;
				int size = map[y][x].size();
				
				while(size-->0)
				{
					int age = map[y][x].pollFirst();
					if(isSummer || food[y][x] < age)// 나이만큼 양분을 먹지 못하면 죽음
					{
						food[y][x] += age / 2; // 나이의 절반을 양분으로 만듦
						isSummer = true;// 한번 summer 로직을 타면 계속 탄다. 오름차순이기 때문
						continue;
					}
					
					food[y][x] -= age;
					map[y][x].addLast(age + 1);
				}
			}
		}
	}
	static int count() {
		int cnt = 0;
		
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				cnt += map[y][x].size();
		
		return cnt;
	}
}