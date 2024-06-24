// https://github.com/kimyongj/algorithm

class Main{

	static int result;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int[][] up(int[][]map, int N){
		int newMap[][] = new int[N][N];
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
		return newMap;
	}
	public static int[][] down(int[][] map, int N){
		int newMap[][] = new int[N][N];
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
		return newMap;
	}
	public static int[][] left(int[][] map, int N){
		int newMap[][] = new int[N][N];
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
		return newMap;
	}
	public static int[][] right(int[][] map, int N){
		int newMap[][] = new int[N][N];
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
		return newMap;
	}

	public static void backtracking(int depth, int map[][], int N) {
		if(depth < 0) return;
		backtracking(depth - 1, up(map,N),N);
		backtracking(depth - 1, down(map,N),N);
		backtracking(depth - 1, left(map,N),N);
		backtracking(depth - 1, right(map,N),N);
		
	}
	public static void main(String[] args)throws Exception{
		int N				= read();
		int original[][]	= new int[N][N];
		
		for(int y=0; y<N; y++) 
		{
			for(int x=0; x<N; x++) 
			{
				if(result < (original[y][x] = read())) 
				{
					result = original[y][x];
				}
			}
		}

		backtracking(4, original, N);
		
		System.out.print(result);
	}
}