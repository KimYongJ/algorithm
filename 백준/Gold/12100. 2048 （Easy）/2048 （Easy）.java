// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	static int result;
	static int N;
	static int map[][];
	static int original[][];
	static int order[];
	
	public static int[][] sum(int m) {
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
	public static void cal() {
		map = new int[N][N];
		for(int y=0; y<N; y++)
			for(int x=0; x<N; x++)
				map[y][x] = original[y][x];

		for(int m : order) 
			map = sum(m);		// 붙어있는 것들에 대해 하나로 합치며 연산한다. 
		
			// 이하 결과 계산
		for(int y=0; y<N; y++) 
			for(int x=0; x<N; x++)
				result = Math.max(map[y][x], result);
	}
	public static void backtracking(int depth) {
		if(depth < 0) {
			cal();
			return;
		}
		for(int i=0; i<4; i++) // 순서 : 내리고, 올리고, 왼쪽, 오른쪽 
		{
			order[depth] = i;
			backtracking(depth - 1);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N			= Integer.parseInt(br.readLine());
		order		= new int[5];
		original	= new int[N][N];
		
		for(int y=0; y<N; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) 
			{
				original[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		backtracking(4);
		
		System.out.print(result);
	}
}