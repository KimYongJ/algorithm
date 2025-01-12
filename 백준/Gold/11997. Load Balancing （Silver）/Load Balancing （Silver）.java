//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11997
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;
class Main{
	
	static TreeSet<Integer> setY = new TreeSet<>();
	static TreeSet<Integer> setX = new TreeSet<>();
	static HashMap<Integer, Integer> mapY;
	static HashMap<Integer, Integer> mapX;
	static int map[][];
	
	public static void main(String[] args)throws Exception{
		TreeSet<Integer> setY = new TreeSet<>();
		TreeSet<Integer> setX = new TreeSet<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int pos[][] = new int[N][2];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
			setY.add(pos[i][0]);
			setX.add(pos[i][1]);
		}
		mapY = new HashMap<>();
		mapX = new HashMap<>();
		int idx = 0;
		for(int y : setY)
			mapY.put(y, ++idx);
		idx = 0;
		for(int x : setX)
			mapX.put(x, ++idx);
		
		int Y	= setY.size();
		int X	= setX.size();
		map		= new int[Y+2][X+2];
		
		for(int i=0; i<N; i++)
		{
			int y = mapY.get(pos[i][0]);
			int x = mapX.get(pos[i][1]);
			map[y][x] = 1;
		}
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] += map[y-1][x] + map[y][x-1] - map[y-1][x-1];
		
		int min = 1<<30;
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
			{
				int max = 0;
				max = Math.max(max, getMin(1,1,y,x));		// 2사분면
				max = Math.max(max, getMin(1,x+1,y,X));		// 1사분면
				max = Math.max(max, getMin(y+1,1,Y,x));		// 3사분면
				max = Math.max(max, getMin(y+1,x+1,Y,X));	// 4사분면
				min = Math.min(max, min);
			}
		
		System.out.print(min);
	}
	public static int getMin(int y1, int x1, int y2, int x2) {
		return map[y2][x2] - map[y2][x1-1] - map[y1-1][x2] + map[y1-1][x1-1];
	}
}