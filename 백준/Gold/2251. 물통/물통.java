// https://github.com/kimyongj/algorithm
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
class Main{
	static int MAX_WATER[];
	static boolean visit[][][];
	static HashSet<Integer> set;
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void DFS(int a, int b, int c) {
		if(visit[a][b][c]) return;
		visit[a][b][c] = true;
		if(a == 0)
			set.add(c);
		
		if(a+c > MAX_WATER[0])// c -> a
			 DFS(MAX_WATER[0], b, c - (MAX_WATER[0]-a));
		else DFS(a+c, b, 0);
		
		if(b+c > MAX_WATER[1])// c -> b
			 DFS(a, MAX_WATER[1], c - (MAX_WATER[1] - b));
		else DFS(a, b+c, 0);

		if(a+b > MAX_WATER[0])// b -> a
			 DFS(MAX_WATER[0], b - (MAX_WATER[0]-a),c);
		else DFS(a+b, 0, c);

		if(b+c > MAX_WATER[2])// b -> c
			 DFS(a, b-(MAX_WATER[2]-c), MAX_WATER[2]);
		else DFS(a, 0, b+c);

		if(a+b > MAX_WATER[1])// a -> b
			 DFS(a -(MAX_WATER[1]-b), MAX_WATER[1], c);
		else DFS(0, a+b, c);

		if(a+c > MAX_WATER[2])// a- > c
			 DFS(a - (MAX_WATER[2]-c), b, MAX_WATER[2]);
		else DFS(0, b, a+c);

	}
	public static void main(String[] args)throws Exception{
		int a = read();
		int b = read();
		int c = read();
		set   = new HashSet<>();
		visit = new boolean[a+1][b+1][c+1];
		MAX_WATER = new int[] {a,b,c};
		DFS(0,0,c);
		
		// 이하 출력
		ArrayList<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int i : list)
			sb.append(i).append(' ');
		System.out.print(sb);
	}
}


class Node{
	int node;
	Node adNode;
	Node(int node, Node adNode){
		this.node	= node;
		this.adNode = adNode;
	}
}