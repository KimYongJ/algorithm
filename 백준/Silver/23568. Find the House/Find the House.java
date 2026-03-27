//https://www.acmicpc.net/problem/23568
//1초 1024MB
//4 // 삼중쌍 개수(1<=10,000)
//3 R 4 // 현 위치(-백만<=백만), 방향(L,R), 이동거리(1<=이백만)
//0 L 2
//7 L 5
//-2 R 5
//0 // 시작하는 현재 위치
//집위치 : 2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Integer, Integer> map = new HashMap<>();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			int dist = Integer.parseInt(st.nextToken());
			
			map.put(key, dir == 'L' ? -dist : dist);
		}
		
		int now = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++)
			now += map.get(now);
		
		System.out.print(now);
	}
}