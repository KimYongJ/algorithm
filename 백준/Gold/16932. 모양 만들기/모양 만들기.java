// https://github.com/kimyongj/algorithm
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

class Node{
	int y,x;
	Node(int y, int x)
	{this.y=y;this.x=x;}
}
class Main{
	static int 				dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 				Y, X, nextY, nextX, max, size, groupIdx;
	static int 				map[][], group[][];
	static HashSet<Integer> same;
	static ArrayList<Node> list;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void BFS(int y, int x) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(y,x));
		while(!q.isEmpty()) 
		{
			Node n = q.poll();
			
			if(map[n.y][n.x] == 0)
				continue;
			
			map[n.y][n.x] = 0;
			list.add(new Node(n.y, n.x));
			
			for(int xy[] : dxy) 
			{
				nextY = n.y + xy[0];
				nextX = n.x + xy[1];
				if(map[nextY][nextX] == 1)
					q.add(new Node(nextY, nextX));
			}
		}
	}
	public static void main(String[] args)throws Exception{
		Y 			= read();
		X 			= read();
		map 		= new int[Y+2][X+2];
		group		= new int[Y+2][X+2];
		same		= new HashSet<>();
		
		
		for(int y=1; y<=Y; y++) 
			for(int x=1; x<=X; x++)
				map[y][x] = read();
		
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
						Node p = list.get(i);
						map[p.y][p.x]	= size;		// 하나의 연결에 1이 총 몇개가있는지 넣음
						group[p.y][p.x] = groupIdx; // 하나의 연결을 그룹으로 체크
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