// https://github.com/KimYongJ/algorithm
import java.util.Arrays;

class Main{
	
	static int N, M, arr[], result[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args)throws Exception{
		N 		= read(); // N개의 숫자 중
		M 		= read(); // M개를 고른다.
		arr		= new int[N];
		result	= new int[N];

		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		DFS(0,0);
		
		System.out.println(sb);
	}
	public static void DFS(int depth,int start) {
		if(depth == M) {
			for(int i=0; i<depth; i++) 
				sb.append(result[i]).append(' ');
			sb.append('\n');
			return;
		}
		int tmp = -1;
		for(int i=start; i<N; i++) {
			
			if(arr[i]==tmp) continue;
			
			result[depth] = arr[i];
			tmp = arr[i];
			DFS(depth+1,i);
		}
	}
	// 빠른 입력을 위해 만듦
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}