// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, arr[], result[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken()); // N개의 숫자 중
		M 		= Integer.parseInt(st.nextToken()); // M개를 고른다.
		arr		= new int[N];
		result	= new int[N];
		st 		= new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
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