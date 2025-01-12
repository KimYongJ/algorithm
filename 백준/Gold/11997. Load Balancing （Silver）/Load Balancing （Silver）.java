//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11997
import java.util.HashMap;
import java.util.TreeSet;
class Main{
	
	static HashMap<Integer, Integer> mapY = new HashMap<>();;
	static HashMap<Integer, Integer> mapX = new HashMap<>();;
	static TreeSet<Integer> setY = new TreeSet<>();
	static TreeSet<Integer> setX = new TreeSet<>();
	static int map[][];
	
	public static void main(String[] args)throws Exception{
		int N		= read();
		int pos[][] = new int[N][2];
		int yIdx	= 0;
		int xIdx	= 0;
		int min		= 1<<30;
		
		for(int i=0; i<N; i++)
		{
			pos[i][0] = read();
			pos[i][1] = read();
			setY.add(pos[i][0]);
			setX.add(pos[i][1]);
		}
		
		for(int y : setY) mapY.put(y, ++yIdx);
		for(int x : setX) mapX.put(x, ++xIdx);
		
		map	= new int[yIdx+2][xIdx+2];
		
		for(int i=0; i<N; i++)
			map[mapY.get(pos[i][0])][mapX.get(pos[i][1])] = 1;
		
		for(int y=1; y<=yIdx; y++)
			for(int x=1; x<=xIdx; x++)
				map[y][x] += map[y-1][x] + map[y][x-1] - map[y-1][x-1];
		
		for(int y=1; y<=yIdx; y++)
			for(int x=1; x<=xIdx; x++)
			{
				// 2,1,3,4 분면 순서
				int max = getMax(
								getSum(1,1,y,x), getSum(1,x+1,y,xIdx)
								,getSum(y+1,1,yIdx,x), getSum(y+1,x+1,yIdx,xIdx)
						);
				min = Math.min(max, min);
			}
		
		System.out.print(min);
	}
	public static int getMax(int a, int b, int c, int d) {
		return Math.max(a, Math.max(b, Math.max(c,d)));
	}
	public static int getSum(int y1, int x1, int y2, int x2) {
		return map[y2][x2] - map[y2][x1-1] - map[y1-1][x2] + map[y1-1][x1-1];
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}