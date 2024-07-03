// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	// 도미노에 들어갈 숫자에 대한 조합
	static final int pos[][] = {
			{1,2},{1,3},{1,4},{1,5},{1,6},{1,7},{1,8},{1,9},	// 0~7	인덱스
			{2,3},{2,4},{2,5},{2,6},{2,7},{2,8},{2,9},			// 8~14 인덱스
			{3,4},{3,5},{3,6},{3,7},{3,8},{3,9},				// 15~20 인덱스
			{4,5},{4,6},{4,7},{4,8},{4,9},						// 21~25 인덱스
			{5,6},{5,7},{5,8},{5,9},							// 26~29 인덱스
			{6,7},{6,8},{6,9},									// 30~32 인덱스
			{7,8},{7,9},										// 33~34 인덱스
			{8,9}												// 35인덱스
	};
	static boolean visit[];		// 사용한 도미노 종류를 담을 것
	static int map[][];
	static int rowValidation[]; // 가로행 체크
	static int colValidation[]; // 세로행 체크
	static int squValidation[]; // 사각형 체크
	
	public static int getIdx(int a, int b) {
		if(a==1)		{return b-2;}
		else if(a==2)	{return b+5;}
		else if(a==3)	{return b+11;}
		else if(a==4)	{return b+16;}
		else if(a==5)	{return b+20;}
		else if(a==6)	{return b+23;}
		else if(a==7)	{return b+25;}
		return b+26;
	}
	public static boolean validate1(int num1, int num2, int y, int x, int nextY,int nextX, int depth) {
		int flag1 = 1<<num1;
		int flag2 = 1<<num2;
		if(
				(rowValidation[y] & flag1) == 0 && 
				(colValidation[x] & flag1) == 0 && 
				(squValidation[(x/3) + (y/3)*3] & flag1) == 0 &&
				
				(rowValidation[nextY] & flag2) == 0 && 
				(colValidation[x] & flag2) == 0 && 
				(squValidation[(x/3) + (nextY/3)*3] & flag2) == 0
			)
		{
			map[y][x] 							= num1;
			map[nextY][x] 						= num2;
			
			rowValidation[y] 					|= flag1;
			colValidation[x] 					|= flag1;
			squValidation[(x/3) + (y/3)*3] 		|= flag1;
			rowValidation[nextY] 				|= flag2;
			colValidation[x] 					|= flag2;
			squValidation[(x/3) + (nextY/3)*3] 	|= flag2;
			if(backtracking(depth - 1,y, x+1)) 
			{
				return true;
			}
			rowValidation[y] 					^= flag1;
			colValidation[x] 					^= flag1;
			squValidation[(x/3) + (y/3)*3] 		^= flag1;
			rowValidation[nextY] 				^= flag2;
			colValidation[x] 					^= flag2;
			squValidation[(x/3) + (nextY/3)*3] 	^= flag2;
			
			map[y][x] 							= 0;
			map[nextY][x] 						= 0;
		}
		return false;
	}
	public static boolean validate2(int num1, int num2, int y, int x, int nextY,int nextX, int depth) {
		int flag1 = 1<<num1;
		int flag2 = 1<<num2;
		if(
				(rowValidation[y] & flag1) == 0 && 
				(colValidation[x] & flag1) == 0 && 
				(squValidation[(x/3) + (y/3)*3] & flag1) == 0 &&
				
				(rowValidation[y] & flag2) == 0 && 
				(colValidation[nextX] & flag2) == 0 && 
				(squValidation[(nextX/3) + (y/3)*3] & flag2) == 0
			)
		{
			map[y][x] 							= num1;
			map[y][nextX] 						= num2;
			
			rowValidation[y] 					|= flag1;
			colValidation[x] 					|= flag1;
			squValidation[(x/3) + (y/3)*3] 		|= flag1;
			rowValidation[y] 					|= flag2;
			colValidation[nextX] 				|= flag2;
			squValidation[(nextX/3) + (y/3)*3] 	|= flag2;
			if(backtracking(depth - 1,y, x+1)) 
			{
				return true;
			}
			rowValidation[y] 					^= flag1;
			colValidation[x] 					^= flag1;
			squValidation[(x/3) + (y/3)*3] 		^= flag1;
			rowValidation[y] 					^= flag2;
			colValidation[nextX] 				^= flag2;
			squValidation[(nextX/3) + (y/3)*3] 	^= flag2;
			
			map[y][x] 							= 0;
			map[y][nextX] 						= 0;
		}
		return false;
	}
	public static boolean backtracking(int depth, int y, int x) {
		if(depth == 0)
		{
			return true;
		}
		if(x==9) 
		{
			x=0;
			y++;
		}
		if(y==9)
		{
			return false;
		}
		if(map[y][x]!=0) 
		{
			return backtracking(depth, y, x+1);
		}
		
		int nextY, nextX, dxy[];
		for(int i=0; i<36; i ++) 
		{
			if(!visit[i])
			{
				visit[i] = true;
				dxy = pos[i];
				nextX = x + 1;
				nextY = y + 1;
				if(nextY < 9 && map[nextY][x] == 0) 
				{
					if(validate1(dxy[0], dxy[1],y,x, nextY, nextX, depth)) {
						return true;
					}
					
					if(validate1(dxy[1], dxy[0],y,x, nextY, nextX, depth)) {
						return true;
					}
				}
				if(nextX < 9 && map[y][nextX] == 0) 
				{
					if(validate2(dxy[0], dxy[1], y,x, nextY, nextX, depth)) {
						return true;
					}
					if(validate2(dxy[1], dxy[0], y,x, nextY, nextX, depth)) {
						return true;
					}
				}
				visit[i] = false;
			}
		}
		
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;

		int N, num1, num2, y1, y2, x1, x2, flag, min, max, idx = 1;
		String str;
		while((N = Integer.parseInt(br.readLine())) != 0) 
		{
			map		= new int[9][9];
			visit	= new boolean[36];
			rowValidation = new int[9];
			colValidation = new int[9];
			squValidation = new int[9];
			
			int DEPTH = 36 - N;
			while(--N>=0) 
			{
				st		= new StringTokenizer(br.readLine());
				
				num1	= Integer.parseInt(st.nextToken());
				str		= st.nextToken();
				y1		= (int)(str.charAt(0) - 'A');
				x1		= (int)(str.charAt(1) - '0')-1;
				
				num2 	= Integer.parseInt(st.nextToken());
				str		= st.nextToken();
				y2		= (int)(str.charAt(0) - 'A');
				x2		= (int)(str.charAt(1) - '0')-1;
				
				map[y1][x1] = num1;
				map[y2][x2] = num2;
				
				flag = 1<<num1;
				rowValidation[y1] |= flag;
				colValidation[x1] |= flag;
				squValidation[(x1/3) + (y1/3)*3] |= flag;
				
				flag = 1<<num2;
				rowValidation[y2] |= flag;
				colValidation[x2] |= flag;
				squValidation[(x2/3) + (y2/3)*3] |= flag;
				
				// 사용한 도미노 체크
				min = Math.min(num1, num2);
				max = Math.max(num1, num2);
				visit[getIdx(min,max)] = true;
			}
			st = new StringTokenizer(br.readLine());
			N=0;
			while(++N <= 9) 		// 마지막에 1부터 9까지 주어지는 숫자의 위치
			{
				str	= st.nextToken();
				y1	= (int)(str.charAt(0) - 'A');
				x1	= (int)(str.charAt(1) - '0')-1;
				map[y1][x1] = N;
				
				flag = 1<<N;
				rowValidation[y1] |= flag;
				colValidation[x1] |= flag;
				squValidation[(x1/3) + (y1/3)*3] |= flag;
			}
			
			backtracking(DEPTH,0,0); // 백트레킹 : 특정 위치에서 쓸 수 있는 막대기를 가로와 세로로 넣어 백트레킹한다. 마지막 위치까지 간 경우 출력하고 종료한다.
			
			sb.append("Puzzle ").append(idx++).append('\n');
			for(int y=0; y<9; y++) 
			{
				for(int x=0; x<9; x++) 
				{
					sb.append(map[y][x]);
				}
				sb.append('\n');
			}
		}
		System.out.print(sb.toString());
	}
	
}