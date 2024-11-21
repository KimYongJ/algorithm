//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7573
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 맵크기 2<=만
		int L		= Integer.parseInt(st.nextToken());	// 그물 길이 (4<=100, L≤4N-4만족하는짝수)
		int M		= Integer.parseInt(st.nextToken());	// 물고기 수 (1<=100)
		int[][]pos	= new int[M][2];
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			pos[i] = new int[] {y,x};
		}
		
		int max = 0;
		int len = L / 2;
		for(int p1[] : pos) {
			for(int p2[] : pos) {
				int y = p1[0];
				int x = p2[1];
				for(int i=1; i<len; i++) {
					int cnt = 0;
					for(int p[] : pos) {
						if(y<=p[0] && p[0]<=y+i &&
							x<=p[1] && p[1]<=x+len-i)
							++cnt;
					}
					max = Math.max(cnt, max);
				}
				
			}
		}

		System.out.print(max);
	}
}