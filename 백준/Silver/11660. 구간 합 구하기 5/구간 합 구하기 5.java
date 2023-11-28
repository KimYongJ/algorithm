// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader  br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		StringBuilder   sb 	= new StringBuilder();
		int N 				= Integer.parseInt(st.nextToken());
		int M 				= Integer.parseInt(st.nextToken());
		int arr[][] 		= new int[N][N];
		int sum[][]			= new int[N][N];
		int find[][]		= new int[M][4];
		
		// 배열 insert
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			
			// 가로 구간합을 미리 구해 놓습니다.
			sum[i][0] = arr[i][0];
			for(int j=1; j<N; j++) {
				sum[i][j] = sum[i][j-1] + arr[i][j];
			}
			
		}
		// 구해야하는 좌표 값 insert
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			find[i][0] = Integer.parseInt(st.nextToken());
			find[i][1] = Integer.parseInt(st.nextToken());
			find[i][2] = Integer.parseInt(st.nextToken());
			find[i][3] = Integer.parseInt(st.nextToken());
		}
		

		for(int i=0; i<M; i++) {//x1-1부터 x2까지 반복
			int total	= 0;
			int x1 		= find[i][0];
			int y1 		= find[i][1];
			int x2 		= find[i][2];
			int y2 		= find[i][3];
			
			for(int x=x1-1; x<x2; x++) { // 가로 구간합 구하기 
				total += sum[x][y2-1]; // y2-1까지의 구간합을 미리 더한다.
				if(y1-2 >= 0) { // 왼쪽 좌표가 0이상이면 해당 구간을 빼준다. 
					total -= sum[x][y1-2];
				}
			}
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