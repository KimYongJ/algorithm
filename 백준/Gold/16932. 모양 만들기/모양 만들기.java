// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main{
	static int 				dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 				Y, X, nextY, nextX, max, size, groupIdx;
	static int 				map[][], group[][];
	static HashSet<Integer> same;
	static ArrayList<int[]> list;
	public static void BFS(int y, int x) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {y,x});
		while(!q.isEmpty()) 
		{
			int[] n = q.poll();
			
			if(map[n[0]][n[1]] == 0)
				continue;
			
			map[n[0]][n[1]] = 0;
			list.add(new int[] {n[0], n[1]});
			
			for(int xy[] : dxy) 
			{
				nextY = n[0] + xy[0];
				nextX = n[1] + xy[1];
				if(map[nextY][nextX] == 1)
					q.add(new int[] {nextY, nextX});
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 			= Integer.parseInt(st.nextToken());
		X 			= Integer.parseInt(st.nextToken());
		map 		= new int[Y+2][X+2];
		group		= new int[Y+2][X+2];
		same		= new HashSet<>();
		
		
		for(int y=1; y<=Y; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y][x] == 1) 
				{
					groupIdx++;
					list = new ArrayList<>();
					BFS(y,x);
					size = list.size();
					for(int i=0; i<size; i++) 
					{
						int[] p = list.get(i);
						map[p[0]][p[1]]		= size;		// 하나의 연결에 1이 총 몇개가있는지 넣음
						group[p[0]][p[1]] 	= groupIdx; // 하나의 연결을 그룹으로 체크
					}
				}

		int sum;
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				if(map[y][x] == 0) // 0인 곳을 돌면서 상하 좌우 size를 더한다. 이 때 같은 그룹인지 체크하여 같은 그룹이면 더하지 않음
				{
					same.clear();
					sum = 1;
					for(int xy[] : dxy) 
					{
						nextY = y + xy[0];
						nextX = x + xy[1];
						if(!same.contains(group[nextY][nextX]))
						{
							same.add(group[nextY][nextX]);
							sum += map[nextY][nextX];
						}
					}
					max = Math.max(max, sum);
				}
		
		System.out.print(max);
	}
}