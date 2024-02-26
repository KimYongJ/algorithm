// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;


class Main
{
	
	static int N, K, mid, endIdx, point[][];
	static boolean visit[];
	static ArrayDeque<Node> q;
	// 빠른 입력을 위한 함수
    static int read() throws Exception 
    {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static int getFuel(int idx1, int idx2) {
		return (int)(Math.pow(point[idx1][0]-point[idx2][0], 2) + Math.pow(point[idx1][1]-point[idx2][1], 2));
	}
	public static boolean BFS() {
		visit		= new boolean[N];
		q 			= new ArrayDeque<>();
		visit[0] 	= true;
		q.add(new Node(0,0));
		
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
				
			if(getFuel(now.idx,endIdx) <= mid) 
				return true;
			
			if(now.k == K) 
				continue;
			
			for(int i=1; i<N; i++) 
			{
				if(!visit[i] && getFuel(i,now.idx) <= mid)
				{
					q.add(new Node(i, now.k+1));
					visit[i] = true;
				}
			}
			
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		N 					= read()+2;
		K 					= read();
		endIdx				= N-1;
		point 				= new int[N][2];
		point[0][0] 		= point[0][1] 		= 0; 		// 초기좌표 셋팅
		point[endIdx][0]	= point[endIdx][1] 	= 10000; 	// 마지막 좌표 셋팅
		for(int i=1; i<endIdx; i++) 
		{
			point[i][0] = read();
			point[i][1] = read();
		}
		
		int left 	= 0, 
			right 	= getFuel(0, endIdx);
		
		while(left < right) {
			mid = (right+left) / 2;
			if( BFS() )
				right = mid;
			else 	
				left = mid+1;
		}
		System.out.println((int)Math.ceil(Math.sqrt(left)/10.0));
		
	}
}
class Node
{
	int idx, k;
	Node(int idx, int k)
	{
		this.idx = idx;
		this.k = k;
	}
}