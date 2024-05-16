// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
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
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		flag 	= true;
		map 	= new int[Y+2][X+2];
		set 	= new HashSet<>();
		compare = new HashSet<>();
		for(int y=1; y<=Y; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		for(int y=1; y<=Y; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++) {
				int num = Integer.parseInt(st.nextToken());
				if(map[y][x] != num) {
					set.add(new Node(y,x));
					compare.add(num);
				}
			}
		}
		if(compare.size() > 1) {
			System.out.print("NO");
			return;
		}
		
		Iterator<Node> ite = set.iterator();
		
		if(ite.hasNext()) 
		{
			Node start = ite.next();
			DFS(map[start.y][start.x],start.y, start.x);
		}
		
		String str = "YES";
		
		if(!flag || set.size() > 0)
			str = "NO";
		
		System.out.print(str);
	}
}