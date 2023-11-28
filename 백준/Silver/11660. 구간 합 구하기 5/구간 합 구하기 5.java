// https://github.com/KimYongJ/algorithm

class Main{
	
	public static void main(String[] args)throws Exception{
		StringBuilder   sb 	= new StringBuilder();
		int N 				= read();
		int M 				= read();
		int arr[][] 		= new int[N+1][N+1];
		int sum[][]			= new int[N+1][N+1];
		int find[][]		= new int[M][4];
		
		// 배열 insert
		for(int i=1; i<=N; i++) {
			// arr배열에 원 값을 넣음 과 동시에 구간합을 구합니다. 
			for(int j=1; j<=N; j++) {
				arr[i][j] = read();
				sum[i][j] = arr[i][j] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];// 해당 좌표의 구간합 구하는 공식
			}
		}
		// 구해야하는 좌표 값 insert
		for(int i=0; i<M; i++) {
			find[i][0] = read();
			find[i][1] = read();
			find[i][2] = read();
			find[i][3] = read();
		}
		

		for(int i=0; i<M; i++) {
			int total	= 0;
			int x1 		= find[i][0];
			int y1 		= find[i][1];
			int x2 		= find[i][2];
			int y2 		= find[i][3];
			
			// 왼쪽 사각형 : x2 , y1-1
			// 위쪽 사각형 : x1-1 , y2
			// 겹치는 사각형 : x1-1, y1-1
			total += sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1];
			
			sb.append(total).append('\n');
		}
		System.out.println(sb);
	}
	// 빠른 입력을 위해 만듦
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}