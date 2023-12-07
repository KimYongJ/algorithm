// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int T, N, cnt, num, base_arr[];
	static boolean visit[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T 			= Integer.parseInt(br.readLine());
		while(T-->0) {
			N	 	= Integer.parseInt(br.readLine());
			cnt 	= 0;
			base_arr= new int[N+1];

			visit 	= new boolean[N+1];

			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				num = Integer.parseInt(st.nextToken());
				base_arr[i] = num; 			// 인덱스에 값 바인딩
			}
			
			for(int i=1; i<=N; i++)
				if(!visit[i]) {
					DFS(i); 				// 인덱스 전달
					cnt++;
				}
			
			sb.append(cnt)
			  .append('\n');
		}
		System.out.println(sb);
	}
	public static void DFS(int idx) {  	// DFS함수
		if(visit[idx]) return; 			// 방문한 인덱스는 종료
		visit[idx] = true; 				// 해당 idx 방문처리
		DFS( base_arr[idx] );
		
	}
    private static int read() throws Exception { // 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}