// https://github.com/kimyongj/algorithm
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Node{
	int y, x;
	Node(int y, int x){this.y=y; this.x=x;}
	@Override
	public int hashCode() {return Objects.hash(y,x);}
	@Override
	public boolean equals(Object o) {
		Node node = (Node)o;
		return node.y == y && node.x == x;
	}
}

class Main{
	
	static final int 	dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 			Y,X, nextY, nextX, map[][];
	static boolean 		flag;
	static Set<Node> 	set;
	static Set<Integer> compare;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int base, int y, int x) {
		if(map[y][x] == 0) 
			return;
		
		map[y][x] = 0;
		
		if(!set.remove(new Node(y,x))) 
		{
			flag = false;
			return;
		}
		
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(flag && map[nextY][nextX] == base)
				DFS(base, nextY, nextX);
		}
		
	}
	public static void main(String[] args)throws Exception{
		Y 		= read();
		X 		= read();
		flag 	= true;
		map 	= new int[Y+2][X+2];
		set 	= new HashSet<>();
		compare = new HashSet<>();
		
		for(int y=1; y<=Y; y++) 
			for(int x=1; x<=X; x++)
				map[y][x] = read();
		
		int num;
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++) 
			{
				num = read();
				if(map[y][x] != num) 
				{
					set.add(new Node(y,x));
					compare.add(num);
				}
			}
		
		if(compare.size() > 1) 
		{
			System.out.print("NO");
			return;
		}
		
		if(set.size() > 0) 
		{
			Node start = set.iterator().next();
			DFS(map[start.y][start.x],start.y, start.x);
		}
		
		
		String str = "YES";
		if(!flag || set.size() > 0)
			str = "NO";
		
		System.out.print(str);
	}
}