//https://www.acmicpc.net/problem/11606
//2초 256MB
//2 10 // 떨군횟수(1<=100), 최대 층(3<=100)
//4 SAFE // 떨어뜨린 층, 결과
//7 BROKEN
//답 : 5 6 // 가장 낮은층, 가장 높은 층

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int min = 1;
		int max = Integer.parseInt(st.nextToken());
		
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			if('S' == st.nextToken().charAt(0))
				min = Math.max(min, f);
			else
				max = Math.min(max, f);
		}
		
		System.out.printf("%d %d", min+1, max-1);
	}
}