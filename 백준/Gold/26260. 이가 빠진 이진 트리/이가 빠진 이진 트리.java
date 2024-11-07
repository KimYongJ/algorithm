//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/26260
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int N, idx, map[];
	static StringBuilder sb = new StringBuilder();
	
	public static void postOrderDFS(int start, int end) {
		if(start==end)
		{
			sb.append(map[start]).append(' ');
			return;
		}
		
		int mid = (start + end) >> 1;
		
		postOrderDFS(start, mid - 1);
		postOrderDFS(mid + 1, end);
		
		sb.append(map[mid]).append(' ');
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());
		map = new int[N];
		idx = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			if((map[i] = Integer.parseInt(st.nextToken()))==-1)
				idx = i;

		map[idx] = Integer.parseInt(br.readLine());
		
		Arrays.sort(map);
		
		postOrderDFS(0, N-1);
		
		System.out.print(sb.toString());
	}
}