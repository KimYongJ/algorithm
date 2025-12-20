//https://www.acmicpc.net/problem/32290
//2초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		boolean visit[] = new boolean[10001];
		while(L<=R)
			visit[X | L++] = true;

		for(int i=0; i<visit.length; i++) {
			if(!visit[i]) {
				System.out.print(i);
				return;
			}
		}
		
	}
}