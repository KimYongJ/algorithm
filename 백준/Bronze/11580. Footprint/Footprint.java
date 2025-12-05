//https://www.acmicpc.net/problem/11580
//2초 256MB
//8 // 명령어 길이 (1<=1000)
//SENWWSEN// E(동), W(서), S(남), N(북)
//답 : 6
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int dxy[][] = {{0,1},{0,-1},{1,0},{-1,0}};
		final int offset = 1_000;
		int N = Integer.parseInt(br.readLine());// 명령어 길이 1<=1000
		boolean map[][] = new boolean[2020][2020];
		
		int cnt = 1;
		int y = offset;
		int x = offset;
		map[y][x] = true;
		
		for(char c : br.readLine().toCharArray())
		{
			int idx = 0;
			if(c == 'W')idx = 1;
			else if(c == 'S')idx = 2;
			else if(c == 'N') idx = 3;
			
			y += dxy[idx][0];
			x += dxy[idx][1];
			
			if(!map[y][x]) ++cnt;
			
			map[y][x] = true;
		}
		
		System.out.print(cnt);
	}
}