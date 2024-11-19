//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14658
import java.util.ArrayList;
import java.util.Collections;
class Node{
	int x, y;
	Node(int x, int y){this.x=x; this.y=y;}
}
class Main{
	
	static ArrayList<Node> star = new ArrayList<>();
	static int L, K;
	
	public static void main(String[] args)throws Exception{
		read();read();	// 안쓰는것
		L = read();		// 트램펄린 한변길이(1<=십만)
		K = read();		// 별똥별수(1<=백만)
		
		for(int i=0; i<K; i++)
			star.add(new Node(read(),read()));
	
		
		Collections.sort(star,(a,b)->a.x!=b.x ? a.x-b.x : a.y-b.y);
		
		int res = 0;
		for(int i=0; i<star.size(); i++)
			for(int j=0; j<star.size(); j++)
				res = Math.max(res, get(star.get(i).x, star.get(j).y));
		
		System.out.print(K - res);
	}
	public static int get(int x, int y) {
		int res = 0;
		
		for(Node now : star)
			if(x<=now.x && now.x<=x+L && now.y<=y && y-L<=now.y)
				++res;
		
		return res;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}