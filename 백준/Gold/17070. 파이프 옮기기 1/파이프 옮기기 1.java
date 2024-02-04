// https://github.com/KimYongJ/algorithm

class Main{
	
	static int len, result, arr[][];

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
    
	public static void horizontal(int y, int x) { 		// 가로로 놓기
		if(arr[y][x]!=1) DFS(y,x,0);
	}
	public static void vertical(int y, int x) {			// 세로로 놓기
		if(arr[y][x]!=1) DFS(y,x,2);
	}
	public static void diagonal(int y, int x) { 		// 대각선 놓기 
		if(arr[y+1][x+1] !=1 && arr[y+1][x] != 1 && arr[y][x+1] != 1)
			DFS(y+1,x+1,1);
	}
	
	
	public static void DFS(int y, int x, int dir) {
		if(x==len && y == len) 
		{
			result ++;
			return;
		}
		if(dir==0)							// 현재 놓인 방향이 가로일 경우 
		{ 
			horizontal(y,x+1);				// 가로로 놓기
			diagonal(y,x); 					// 대각선 놓기 
		}
		else if(dir==1)						// 현재 놓인 방향이 대각선일 경우 
		{ 
			horizontal(y,x+1);				// 가로로 놓기
			vertical(y+1, x);				// 세로로 놓기
			diagonal(y,x); 					// 대각선 놓기 
		}
		else if(dir==2)						// 현재 놓인 방향이 세로일 경우 
		{ 
			vertical(y+1, x);				// 세로로 놓기
			diagonal(y,x); 					// 대각선 놓기 
		}
	}
	
	public static void main(String[] args)throws Exception{
		len 	= read();
		arr 	= new int[len+2][len+2];
		// 외부를 1로 패딩
		for(int i=0; i<len+2; i++) 
			arr[0][i] = 
			arr[i][0] =
			arr[len+1][i] = 
			arr[i][len+1] = 1;
		
		// 숫자 입력 받기
		for(int i=1; i<len+1; i++) 
			for(int j=1; j<len+1; j++) 
				arr[i][j] = read();
		
		
		DFS(1,2,0);
		
		System.out.println(result);
	}

}