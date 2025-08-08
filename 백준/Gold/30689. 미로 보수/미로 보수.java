//https://www.acmicpc.net/problem/30689
//2초 1024MB
//3 3 // 세로, 가로 
//LLL // 미로의 방향이 먼저 주어짐
//DLU
//RUU
//3 1 5 // 각칸의 탈출 비용이 주어짐
//2 9 6
//8 7 1
//답 : 2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{-1,0},{0,1},{1,0},{0,-1}};
	static int min;
	static int Y, X;
	static int value[][];
	static List<Integer> adList[];
	static List<Integer> cycle;
	
	static int time;
	static int visitTime[];
	
	public static void main(String[] args)throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.parseInt(st.nextToken()); // 1<=2,000
		X = Integer.parseInt(st.nextToken()); // 1<=2,000
		value = new int[Y][X];
		cycle = new ArrayList<>();
		adList = new ArrayList[Y * X];
		
		for(int i=0; i<adList.length; i++)
			adList[i] = new ArrayList<>();
		
		for(int y=0; y<Y; y++) // 방향 입력 받음
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
			{
				int nextIdx = getNext(y,x, str.charAt(x));
				if(nextIdx < 0)
					continue;
				int nowIdx = getIdx(y, x);
				
				adList[nowIdx].add(nextIdx);
			}
		}
		for(int y=0; y<Y; y++) // 값 입력 받음
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++)
				value[y][x] = Integer.parseInt(st.nextToken());// 1<=500
		}
		
		visitTime = new int[adList.length];
		for(int i=0; i<adList.length; i++)
		{
			if(visitTime[i] != 0)
				continue;
			
			visitTime[i] = ++time;
			searchCycle(i);
		 }
		
		int sum = 0;
		
		
		for(int c : cycle)
		{
			min = 1<<10;
			
			dfs(c, c);
			
			sum += min;
		}
		
		System.out.print(sum);
	}
	static void dfs(int now, int end) {
		for(int next : adList[now])
		{
			min = Math.min(min, getValue(next));
			
			if(next == end)
				return;
			
			dfs(next, end);
		}
	}
	static boolean searchCycle(int now) {
		for(int next : adList[now])
		{
			if(visitTime[next]== 0)
			{
				visitTime[next] = time;
				if(searchCycle(next))
					return true;
			}
			else if(visitTime[next] == time)
			{
				cycle.add(now);
				return true;
			}
		}
		return false;
	}
	static int getNext(int y, int x, char c) {
		int idx = 0;
		switch(c)
		{
		case 'R' : idx = 1;break;
		case 'D' : idx = 2;break;
		case 'L' : idx = 3;break;
		}
		
		y += dxy[idx][0];
		x += dxy[idx][1];
		
		if(y < 0 || x < 0 || Y == y || X == x)
			return -1;
		
		return getIdx(y,x);
	}
	static int getIdx(int y, int x) {
		return y*X + x;
	}
	static int getValue(int n) {
		int y = n / X;
		int x = n % X;
		
		return value[y][x];
	}
}