// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int 		dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 		Y, X, K, cnt, gY, gX;
	static boolean	map[][],	// false는 T로 가지못한다. 
					visit[][];
	public static void DFS(int y, int x, int depth) {
		if(y==gY && x==gX) {
			if(depth==K)
				cnt++; 
			return;
		}
		if(depth >= K) return;
		int nY, nX;
		for(int xy[] : dxy) {
			nY = y + xy[0];
			nX = x + xy[1];
			if(nY>=0 && nX>=0 && nY<Y && nX<X && !visit[nY][nX] && map[nY][nX]) {
				visit[nY][nX] = true;
				DFS(nY, nX, depth + 1);
				visit[nY][nX] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		K 		= Integer.parseInt(st.nextToken());
		gX 		= X-1;
		map 	= new boolean[Y][X];
		visit	= new boolean[Y][X];
		String str;
		for(int y=0; y<Y; y++) {
			str = br.readLine();
			for(int x=0; x<X; x++)
				map[y][x] = str.charAt(x) == '.';
		}
		visit[Y-1][0] = true;
		DFS(Y-1, 0, 1);
		System.out.print(cnt);
	}
}
