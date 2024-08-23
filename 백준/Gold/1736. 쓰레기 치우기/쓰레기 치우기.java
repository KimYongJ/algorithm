// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int Y		= read();
		int X		= read();
		int cnt		= 0;
		int map[][]	= new int[Y][X+1];
		PriorityQueue<Integer>[] pq = new PriorityQueue[X];
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++) 
				map[y][x] = read();
		
		for(int x=0; x<X; x++) 
		{
			pq[x] = new PriorityQueue<>();
			for(int y=0; y<Y; y++)
				if(map[y][x] == 1)
					pq[x].add(y);
		}
		
		for(int x=X-1; x>=0; x--) 
			if(pq[x].size() != 0) 
			{
				cnt++;
				int now = pq[x].peek();
				for(int x1=x-1; x1>=0; x1--) 
				{
					if(pq[x1].isEmpty()) 
						continue;
					
					int min = Math.min(now,pq[x1].peek());
					
					while(!pq[x1].isEmpty() && now >= pq[x1].peek()) 
						pq[x1].poll();
					
					now = min;
				}
			}
		System.out.print(cnt);
	}
}
