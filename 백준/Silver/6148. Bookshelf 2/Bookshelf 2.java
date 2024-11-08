//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/6148
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, H, min, arr[];
	static boolean visit[];
	public static void DFS(int idx, int depth, int sum) {
		if(H <= sum)
			min = Math.min(min, sum-H);
		if(depth == N)
			return;
		
		for(int i=idx; i<N; i++)
			if(!visit[i])
			{
				visit[i] = true;
				DFS(i+1,depth + 1, sum + arr[i]);
				visit[i] = false;
			}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());
		H		= Integer.parseInt(st.nextToken());
		arr		= new int[N];
		visit	= new boolean[N];
		min		= 1<<30;
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		DFS(0, 0, 0);
		
		System.out.print(min);
	}
}