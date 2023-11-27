// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, arr[], result[];
	static boolean visit[];
	static LinkedHashSet<String> set = new LinkedHashSet<>();
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 	  	= Integer.parseInt(st.nextToken());
		M	  	= Integer.parseInt(st.nextToken());
		arr		= new int[N];
		result 	= new int[M];
		visit	= new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		DFS(0);

		StringBuilder sb = new StringBuilder();
		Iterator iter = set.iterator();
		while(iter.hasNext()) {
			sb.append(iter.next());
		}
		System.out.println(sb);
	}
	public static void DFS(int depth) {
		if(depth == M) {
			StringBuilder sb = new StringBuilder();
			for(int r : result) sb.append(r).append(' ');
			set.add(sb.toString());
			return;
		}
		for(int i=0; i<N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				result[depth] = arr[i];
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