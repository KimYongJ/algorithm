// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
class Main{

	static StringBuilder sb = new StringBuilder();
	static int N, M, arr[], number[];
	static boolean visit[];
	
	public static void main(String[] args)throws Exception{
		//1부터 N까지 자연수 중에서 M개를 고른 수열
		N 		= read();
		M 		= read();
		arr		= new int[N];
		number 	= new int[N];
		visit 	= new boolean[N];

		for(int i=0; i<N; i++)
			number[i] = read();
		
		Arrays.sort(number); // 오름차순 출력해야 하므로 정렬처리.
		
		DFS(0); 	

		System.out.println(sb);
	}
	public static void DFS(int depth) {
		if(depth == M) {// M개를 고른 경우 출력 처리.
			for(int i=0; i<M; i++)
				sb.append(arr[i]).append(' ');
			sb.append('\n');
			return;
		}
		for(int i=0; i<N; i++) { 		// 0부터 N까지 반복
			if(!visit[i]) { 			// 주어진 값들 중 방문하지 않은 값이라면 이하 실행  
				arr[depth] = number[i];	// arr에 값을 하나 담는다.
				visit[i] = true; 		// 담은 값 방문처리 
				DFS(depth+1);			// DFS실행
				visit[i] = false;		// 백트래킹 : 담은 값에 대해 방문처리를 풀어 다음에 또 담을 수 있게 한다. 
			}
		}
	}
	// 빠른 입력을 위해 만듦
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}