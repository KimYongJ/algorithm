// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	static final int			dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 					N, M, len, num, result, nextY, nextX, map[][];
	static boolean 				visit[][];
	static ArrayDeque<int[]> 	q;
	static ArrayList<int[]> 	houseList, chickenList;
	
	public static int getDistSum() {
		int sum = 0, p[];
		for(int i=0; i<houseList.size(); i++) {
			p = houseList.get(i);
			sum += getDist(p[0],p[1]);
		}
		return sum;
	}
	
	public static int getDist(int y, int x) {
		q 			= new ArrayDeque<>();
		visit 		= new boolean[N][N];
		visit[y][x] = true;	
		
		q.add(new int[] {y,x});
		
		while(!q.isEmpty()) 
		{
			int[] n = q.poll();
			for(int xy[] : dxy) 
			{
				nextY = n[0] + xy[0];
				nextX = n[1] + xy[1];
				if(nextY>=0 && nextX>=0 && nextY<N && nextX<N && !visit[nextY][nextX]) 
				{
					visit[nextY][nextX] = true;
					if(map[nextY][nextX]==2) 
					{
						return Math.abs(y-nextY) + Math.abs(x-nextX);
					}
					q.add(new int[] {nextY, nextX});
				}
			}
			
		}
		return 0;
	}
	public static void Back(int idx, int depth) {
		if(depth == M) {
			result = Math.min(result, getDistSum());
			return;
		}
		int p[];
		for(int i=idx; i<len; i++) {
			p = chickenList.get(i);
			map[p[0]][p[1]] = 2;
			Back(i+1, depth+1);
			map[p[0]][p[1]] = 0;
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());
		N 			= Integer.parseInt(st.nextToken());
		M 			= Integer.parseInt(st.nextToken());
		map			= new int[N][N];
		result 		= Integer.MAX_VALUE;
		houseList	= new ArrayList<>();
		chickenList = new ArrayList<>();
		
		for(int y=0; y<N; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				num = Integer.parseInt(st.nextToken());
				if(num == 1)
					houseList.add(new int[] {y,x});
				else if(num == 2)
					chickenList.add(new int[] {y,x});
			}
		}
		
		len = chickenList.size(); //len개 중 M개를 고르는 것
		
		Back(0,0);
		
		System.out.print(result);
	}
}