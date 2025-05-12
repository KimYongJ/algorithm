//https://www.acmicpc.net/problem/18138
//1초 256MB
//티셔츠 넓이 W라할 때, 붙일 수 있는 카라는
//W/2 <= W*3/4 혹은 W<= W*5/4 이하인 카라는 붙일 수 있다. 
//5 5	// 티셔츠 수(1<=200), 카라 수(1<=200)
//1		// N개 줄에 각티셔츠 들의 넓이 W(1<=1,000)
//3
//5
//7
//10
//2		// M개 줄에 카라 넓이 W(1<=1,000)
//4
//8
//5
//6
//답 : 4

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int []match;// idx : 카라 , value : 티셔츠
	static int []tarea;// 티셔츠 넓이 
	static boolean[] visit;
	static ArrayList<Integer>[] adList;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 티셔츠 수(1<=200)
		M = Integer.parseInt(st.nextToken());// 카라 수(1<=200)
		tarea = new int[N + 1];
		match = new int[M + 1];
		adList = new ArrayList[N + 1];// 티셔츠마다 연결가능한 카라를 저장한다.
		// 티셔츠 넓이 입력 및 list초기화
		for(int i=1; i<=N; i++)
		{
			tarea[i] = Integer.parseInt(br.readLine());
			adList[i] = new ArrayList<>();
		}
		// 카라를 입력 받으며 입력 받은 카라가 어느 티셔츠에 붙일 수 있는지 저장
		for(int i=1; i<=M; i++)
		{
			float collar = Float.parseFloat(br.readLine());
			
			for(int j=1; j<=N; j++)
			{
				if(
					(tarea[j] / 2.0 <= collar && collar <= tarea[j]*3/4.0)
					|| (tarea[j] <= collar && collar <= tarea[j]*5/4.0)
				  )
				{
					adList[j].add(i);
				}
			}
		}
		
		int cnt = 0;
		for(int i=1; i<=N; i++)
		{
			visit = new boolean[M + 1];
			if(dfs(i))
				++cnt;
		}
		System.out.print(cnt);		
	}
	static boolean dfs(int tshirt)
	{
		for(int collar : adList[tshirt])
		{
			if(visit[collar])
				continue;
			
			visit[collar] = true;
			
			if(match[collar] == 0 || dfs(match[collar]))
			{
				match[collar] = tshirt;
				return true;
			}
		}
		
		return false;
	}
}