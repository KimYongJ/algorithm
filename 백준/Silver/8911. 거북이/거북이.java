//https://www.acmicpc.net/problem/8911
//1초 128MB
//3 // 테스트 케이스 개수
//FFLF // 길이는 500을 넘지 않는 명령어가 주어짐
//FFRRFF // F(전진), B(후진), L(왼쪽방향회전), R(오른쪽방향회전)
//FFFBBBRFFFBBB
//거북이가 이동한 영역을 모두 포함하는 가장 작은 직사각형 넓이 출력
//2
//0
//9

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static final int dxy[][] = {{-1,0},{0,1},{1,0},{0,-1}};
	static int dir;
	static int y,x;
	static int maxY, maxX, minY, minX;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			dir = y = x = maxY = maxX = minY = minX = 0;
			
			String str = br.readLine();
			
			for(char c : str.toCharArray())
			{
				if(c == 'L')
				{
					dir = dir - 1 < 0 ? 3 : dir - 1;
					continue;
				}
				else if(c == 'R')
				{
					dir = dir + 1 == 4 ? 0 : dir + 1;
					continue;
				}
				
				int flag = c == 'B' ? -1 : 1;
				
				y += dxy[dir][0] * flag;
				x += dxy[dir][1] * flag;
				
				if(y < minY) minY = y;
				if(y > maxY) maxY = y;
				if(x < minX) minX = x;
				if(x > maxX) maxX = x;
			}
			sb.append((maxY - minY) * (maxX - minX)).append('\n');
		}
		System.out.print(sb);
	}
}