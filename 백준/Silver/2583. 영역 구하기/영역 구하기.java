import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}};
	static int Y, X, K;
	static boolean visit[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Y = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visit = new boolean[X][Y];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Y-Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Y-Integer.parseInt(st.nextToken());
			
			for(int x=x1; x< x2; x++)
				for(int y=y2; y<y1; y++)
					visit[x][y] = true;
		}
		ArrayList<Integer> list = new ArrayList<>();
		int cnt = 0;
		for(int x=0; x<X; x++)
			for(int y=0; y<Y; y++)
				if(!visit[x][y]) {
					cnt++;
					list.add( DFS(x, y) );
				}

		
		Collections.sort(list);
		
		sb.append(cnt).append('\n');
		for(int i=0; i<list.size(); i++) 
			sb.append(list.get(i)).append(' ');
		
		System.out.println(sb);
	}
	public static int DFS(int x, int y) {
		visit[x][y] = true;
		int cnt = 0;
		for(int xy[] : dxy) {
			int newX = x+xy[0];
			int newY = y+xy[1];
			if( newX>=0 && newY>=0 && newX<X && newY<Y && !visit[newX][newY])
				cnt += DFS(newX, newY);

		}
		return cnt+1;
	}
}