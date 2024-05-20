// https://github.com/kimyongj/algorithm

class Node{
	int y,x;
	Node(int y, int x){this.y=y; this.x=x;}
}
class Main{

	static int  result, select[], dist[][];
	static int	N, M;
	static int  hIdx, cIdx;
	static Node	house[], chicken[];
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void distSum() {
		int sum = 0;
		
		for(int i=0; i<hIdx; i++)  // 모든 1을 순회 
		{
			int min = 101;
			
			for(int m=0; m<M; m++) // 구한 치킨 집을 모두 순회한다.
				min = Math.min(min, dist[i][select[m]]); // 1에서 가장 가까운 치킨 집을 찾아 min에 대입
			
			sum += min;
		}
		
		result = Math.min(result, sum);
	}
	public static void Back(int idx, int depth) {
		if(depth == M) 
		{
			distSum(); // 구해진 치킨 집으로 부터 일반 집까지의 거리의 합을 구한다.
			return;
		}
		for(int i=idx; i<cIdx; i++) 
		{
			select[depth] = i; // 치킨집을 넣는다.
			Back(i+1, depth+1);
		}
	}
	public static void main(String[] args)throws Exception{
		N 			= read();
		M 			= read();
		dist		= new int[100][13]; // 집 : 치킨집
		result 		= Integer.MAX_VALUE;
		house		= new Node[100];
		chicken 	= new Node[13];
		select		= new int[M];
		
		for(int y=0; y<N; y++) 
			for(int x=0; x<N; x++) 
			{
				int n = read();
				if(n == 1)
					house[hIdx++] = new Node(y,x);
				else if(n == 2)
					chicken[cIdx++] = new Node(y,x);
			}
		
		
		// 각 노드까지의 거리를 미리 구해 놓는다.
		for(int i=0; i<hIdx; i++)
			for(int j=0; j<cIdx; j++)
				dist[i][j] = Math.abs(house[i].y - chicken[j].y) + Math.abs(house[i].x - chicken[j].x);

		Back(0,0);
		
		System.out.print(result);
	}
}