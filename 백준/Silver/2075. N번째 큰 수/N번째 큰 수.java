//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2075
import java.util.PriorityQueue;
class Node{
	int cur, y, x;
	Node(int c, int y, int x){this.y=y; this.x=x; cur=c;}
}
class Main{
    static int read() throws Exception {
        int r = 0, c = System.in.read();
        while (c <= ' ') c = System.in.read();
        boolean negative = false;
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }
        return negative ? -r : r;
    }
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq	= new PriorityQueue<>((a,b)->b.cur-a.cur);
		int N		= read();
		int map[][] = new int[N][N];
		
		
		for(int y=0; y<N-1; y++) 
			for(int x=0; x<N; x++)
				map[y][x] = read();

		for(int x=0; x<N; x++)
			pq.add(new Node(map[N-1][x] = read(),N-1, x));
		
		int ans = 0;
		
		while(--N>-1)
		{
			Node cur = pq.poll();
			ans = cur.cur;
			if(cur.y - 1 < 0)
				continue;
			pq.add(new Node(map[cur.y-1][cur.x],cur.y-1,cur.x));
		}
		
		System.out.print(ans);
	}
}