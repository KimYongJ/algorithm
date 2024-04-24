// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main{
	static int MAX_WATER[];
	static boolean visit[][][];
	static HashSet<Integer> set;
	public static void DFS(int a, int b, int c) {
		if(!visit[a][b][c]) {
			visit[a][b][c] = true;
			if(a == 0)
				set.add(c);
			
			int a1,b1,c1;
			
			a1 = a + c; // c -> a
			if(a1 > MAX_WATER[0]) {
				c1 = a1 - MAX_WATER[0];
				a1 = MAX_WATER[0];
			}else c1 = 0;
			DFS(a1,b,c1);
			
			b1 = b + c; // c -> b
			if(b1 > MAX_WATER[1]) {
				c1 = b1 - MAX_WATER[1];
				b1 = MAX_WATER[1];
			}else c1 = 0;
			DFS(a, b1, c1);
			
			a1 = a + b; // b -> a
			if(a1 > MAX_WATER[0]) {
				b1 = a1 - MAX_WATER[0];
				a1 = MAX_WATER[0];
			}else b1 = 0;
			DFS(a1,b1,c);
			
			c1 = b + c; // b -> c
			if(c1 > MAX_WATER[2]) {
				b1 = c1 - MAX_WATER[2];
				c1 = MAX_WATER[2];
			}else b1 = 0;
			DFS(a,b1,c1);
			
			b1 = a + b; // a -> b
			if(b1 > MAX_WATER[1]) {
				a1 = b1 - MAX_WATER[1];
				b1 = MAX_WATER[1];
			}else a1 = 0;
			DFS(a1,b1,c);
			
			c1 = a + c; // a -> c
			if(c1 > MAX_WATER[2]) {
				a1 = c1 - MAX_WATER[2];
				c1 = MAX_WATER[2];
			}else a1 = 0;
			DFS(a1,b,c1);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		set   = new HashSet<>();
		visit = new boolean[a+1][b+1][c+1];
		MAX_WATER = new int[] {a,b,c};
		DFS(0,0,c);
		
		// 이하 출력
		ArrayList<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int i : list)
			sb.append(i).append(' ');
		
		System.out.print(sb);
	}
}
