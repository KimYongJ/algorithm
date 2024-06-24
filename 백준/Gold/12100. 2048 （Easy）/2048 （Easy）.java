// https://github.com/kimyongj/algorithm

class Main{

	static int result;
	static int N;
	static int original[][];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int[][] sum(int[][]map, int m) {
		int newMap[][] = new int[N][N];
		// 블록을 내렸을 때 
		if(m == 0) { 
            for(int c=0; c<N; c++) {
                int nextIdx = N-1, baseBlock = -1;
                for(int r=N-1; r>=0; r--) {
                    if(map[r][c]==0) {
                        continue;
                    }
                    if(map[r][c]==baseBlock) {
                        newMap[nextIdx+1][c]  <<=1;
                        baseBlock=-1;
                        result = Math.max(result, newMap[nextIdx+1][c]);
                    }else {
                        newMap[nextIdx--][c] =baseBlock = map[r][c];
                    }
                }
            }
		}
		// 블록을 올렸을 때 
		else if(m == 1) {
			for(int c=0; c<N; c++) {
                int nextIdx = 0, baseBlock = -1;
                for(int r=0; r<N; r++) {
                    if(map[r][c]==0) {
                        continue;
                    }
                    if(map[r][c]==baseBlock) {
                        newMap[nextIdx-1][c] <<=1; 
                        baseBlock=-1;
                        result = Math.max(result, newMap[nextIdx-1][c]); 
                    }else {
                        newMap[nextIdx++][c] =baseBlock = map[r][c];
                       
                    }
                }
			}
		}
		// 블록을 왼쪽으로 보냈을 때 
		else if(m==2) {
            for(int r=0; r<N; r++) {
                int nextIdx = 0, baseBlock = -1;
                for(int c=0; c<N; c++) {
                    if(map[r][c]==0) {
                        continue;
                    }
                    if(map[r][c]==baseBlock) {
                        newMap[r][nextIdx-1]  <<=1;
                        baseBlock=-1;
                        result = Math.max(result, newMap[r][nextIdx-1]);
                    }else {
                        newMap[r][nextIdx++] =baseBlock = map[r][c];
                    }
                }
            }
		}
		// 블록을 오른쪽으로 보냈을 때 
		else {
            for(int r=0; r<N; r++) {
                int nextIdx =N-1, baseBlock = -1;
                for(int c=N-1; c>=0; c--) {
                    if(map[r][c]==0) {
                        continue;
                    }
                    if(map[r][c]==baseBlock) {
                        newMap[r][nextIdx+1]  <<=1;
                        baseBlock=-1;
                        result = Math.max(result, newMap[r][nextIdx+1]);
                    }else {
                        newMap[r][nextIdx--] =baseBlock = map[r][c];
                    }
                }
            }
		}
		return newMap;
	}

	public static void backtracking(int depth, int map[][]) {
		if(depth < 0) return;
		backtracking(depth - 1, sum(map, 0));
		backtracking(depth - 1, sum(map, 1));
		backtracking(depth - 1, sum(map, 2));
		backtracking(depth - 1, sum(map, 3));
		
	}
	public static void main(String[] args)throws Exception{
		N			= read();
		original	= new int[N][N];
		
		for(int y=0; y<N; y++) 
			for(int x=0; x<N; x++) 
				result = Math.max(result, original[y][x] = read());

		backtracking(4, original);
		
		System.out.print(result);
	}
}