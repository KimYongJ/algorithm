//https://www.acmicpc.net/problem/1063
//2초 128MB
//A1 A2 2//킹의 위치, 돌의 위치, 움직이는 횟수
//T
//R
//킹과 돌의 위치 출력
//B2
//A3
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int offset = 'A' - 1;
	static final int dxy[][] = {{0,1},{0,-1},{-1,0},{1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
	static int ky, kx, ry, rx;
	static int N;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String king = st.nextToken();
		String rock = st.nextToken();
		N = Integer.parseInt(st.nextToken());
		ky = king.charAt(1) - '0';
		kx = king.charAt(0) - offset;
		ry = rock.charAt(1) - '0';
		rx = rock.charAt(0) - offset;
		
		while(N-->0)
		{
			int dir = getDir(br.readLine());
			
			int nky = ky + dxy[dir][0];
			int nkx = kx + dxy[dir][1];
			int nry = ry;
			int nrx = rx;
			
			if(!validate(nky, nkx))
				continue;
			
			boolean bump = nky == ry && nkx == rx;
			
			if(bump)
			{
				nry += dxy[dir][0];
				nrx += dxy[dir][1];
				
				if(!validate(nry, nrx))
					continue;
			}
			
			ky = nky;
			kx = nkx;
			ry = nry;
			rx = nrx;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append((char)(kx + offset)).append(ky).append('\n');
		sb.append((char)(rx + offset)).append(ry).append('\n');
		
		System.out.print(sb);
	}
	static boolean validate(int y, int x) {
		return 1<=y && 1<=x && y<=8 && x<=8;
	}
	static int getDir(String str) {
		switch(str) {
		case "R": return 0;
		case "L": return 1;
		case "B": return 2;
		case "T": return 3;
		case "RT": return 4;
		case "LT": return 5;
		case "RB": return 6;
		default : return 7;
		}
		
	}
}