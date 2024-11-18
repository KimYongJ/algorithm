//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2075
import java.io.DataInputStream;
import java.util.PriorityQueue;
class Node{
	int cur, y, x;
	Node(int c, int y, int x){this.y=y; this.x=x; cur=c;}
}
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq	= new PriorityQueue<>((a,b)->b.cur-a.cur);
		int N		= readInt();
		int map[][] = new int[N][N];
		
		
		for(int y=0; y<N-1; y++) 
			for(int x=0; x<N; x++)
				map[y][x] = readInt();

		for(int x=0; x<N; x++)
			pq.add(new Node(map[N-1][x] = readInt(),N-1, x));
		
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
	
    static final int DEFAULT_BUFFER_SIZE = 1 << 16;
    static DataInputStream inputStream = new DataInputStream(System.in);;
    static byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    static int curIdx, maxIdx;

    static int readInt() throws Exception {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }

    static byte read() throws Exception {
        if (curIdx == maxIdx) {
            maxIdx = inputStream.read(buffer, curIdx = 0, DEFAULT_BUFFER_SIZE);
            if (maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}
