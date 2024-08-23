// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y		= Integer.parseInt(st.nextToken());
		int X		= Integer.parseInt(st.nextToken());
		int map[][]	= new int[Y][X+1];
		PriorityQueue<Integer>[] pq = new PriorityQueue[X];
		for(int y=0; y<Y; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<X; x++) 
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		for(int x=0; x<X; x++) {
			pq[x] = new PriorityQueue<>();
			for(int y=0; y<Y; y++) {
				if(map[y][x] == 1) {
					pq[x].add(y);
				}
			}
		}
		int cnt = 0;
		for(int x=X-1; x>=0; x--) {
			if(pq[x].size() != 0) {
				cnt++;
				int now = pq[x].peek();
				for(int x1=x-1; x1>=0; x1--) {
					int min = 100;
					while(!pq[x1].isEmpty() && now >= pq[x1].peek()) {
						min = Math.min(min,pq[x1].poll());
					}
					if(min < now)
						now = min;
				}
			}
		}
		System.out.print(cnt);
	}
}