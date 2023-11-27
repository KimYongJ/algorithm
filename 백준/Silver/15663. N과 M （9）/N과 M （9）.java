// https://github.com/KimYongJ/algorithm
import java.util.Arrays;

class Main{
	
	static int N, M, arr[], result[];
	static boolean visit[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args)throws Exception{

		N 	  	= read();
		M	  	= read();
		arr		= new int[N];
		result 	= new int[M];
		visit	= new boolean[N];

		
		for(int i=0; i<N; i++) 
			arr[i] = read();
		
		
		Arrays.sort(arr);
		
		DFS(0);

		
		System.out.println(sb);
	}
	public static void DFS(int depth) {
		if(depth == M) {
			for(int r : result) sb.append(r).append(' ');
			sb.append('\n');
			return;
		}
		int temp = 0;
		for(int i=0; i<N; i++) {
			if(temp == arr[i]) continue;
			if(!visit[i]) {
				visit[i] = true;
				result[depth] = arr[i];
				temp = arr[i];
				DFS(depth+1);
				visit[i] = false;
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