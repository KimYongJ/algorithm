// https://github.com/KimYongJ/algorithm

class Main{
	
	static int n, m, d, N, M, clean_room, map[][];

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32)
	        n = (n << 3) + (n << 1) + (c & 15);
	    if (c == 13) System.in.read();
	    return n;
	}
	public static boolean validate(int n, int m) {
		return n>=0 && m>=0 && n<N && m<M;
	}
	public static boolean all_clean(int n, int m) {
		return (validate(n+1, m) && map[n+1][m] == 0) ||
				(validate(n, m+1) && map[n][m+1] == 0) ||
				(validate(n-1, m) && map[n-1][m] == 0) ||
				(validate(n, m-1) && map[n][m-1] == 0);
	}
	public static void getReverse() {
		if(d==0) { 			// 북
			if(validate(n+1,m) && map[n+1][m] != 1) n+=1;
			else n = -1;
		}else if(d==1) { 	// 동
			if(validate(n,m-1) && map[n][m-1] != 1) m-=1;
			else n = -1;
		}else if(d==2) { 	// 남
			if(validate(n-1,m) && map[n-1][m] != 1) n-=1;
			else n = -1;
		}else { 			// 서
			if(validate(n,m+1) && map[n][m+1] != 1) m+=1;
			else n = -1;
		}
	}
	public static void getFront() {
		if(d==0) { 			// 북
			if(validate(n-1,m) && map[n-1][m] == 0) n-=1;
			else n = -1;
		}else if(d==1) { 	// 동
			if(validate(n,m+1) && map[n][m+1] == 0) m+=1;
			else n = -1;
		}else if(d==2) { 	// 남
			if(validate(n+1,m) && map[n+1][m] == 0) n+=1;
			else n = -1;
		}else { 			// 서
			if(validate(n,m-1) && map[n][m-1] == 0) m-=1;
			else n = -1;
		}
	}

	public static void main(String[] args)throws Exception{
		N 					= read();
		M 					= read();
		n					= read(); // 현재 좌표
		m					= read(); // 현재 좌표
		d					= read(); // 현재 바라보는 방향
		map 				= new int[N][M];
		
		for(int i=0; i<N; i++) 
			for(int j=0; j<M; j++)
				map[i][j] = read();
		
		while(true) {
			if(map[n][m] == 0) 
			{
				map[n][m] = 2; // 청소 후 값
				clean_room ++; // 청소한 방 추가
			}
			int fn = n;
			int fm = m;
			if(!all_clean(n,m)) 
			{ // 청소할 곳이 없다면 
				getReverse();
				if(n == -1) { // 후진이 불가능 하다면
					break;
				}
			}
			else 
			{// 청소할 곳이 있다면
				d = d-1 == -1 ? 3 : d-1;
				getFront();
				if(n == -1) {
					n = fn;
					m = fm;
				}
			}
		}

		System.out.println(clean_room);
	}
}