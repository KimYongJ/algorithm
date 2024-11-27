//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2492
class Node{int x,y;Node(int x,int y){this.x=x;this.y=y;}}

class Main{

	public static void main(String[] args)throws Exception{
		int N		= read();	// 지도 넓이(1<=백만)
		int M		= read();	// 지도 높이(1<=백만)
		int T		= read();	// 금강석의 개수(1<=100)
		int K		= read();	// 정사각형의 크기(1<=백만)
		Node[]node	= new Node[T];
		
		for(int i=0; i<T; i++)
			node[i] = new Node(read(),read());
		
		int CNT = 0;
		int Y	= 0;
		int X	= 0;
		
		for(Node n1 : node)
			for(Node n2 : node)
			{
				int x	= n1.x + K > N ? N - K : n1.x;	// 시작x , x+K가 범위초과시 x를 N-K로 세팅
				int y	= n2.y + K > M ? M - K : n2.y;	// 시작y , x와 마찬가지로 범위 초과시 값세팅
				int cnt	= 0;
				
				for(Node n : node) 
					if(x<=n.x && n.x<=x+K && y<=n.y && n.y<=y+K)
						++cnt;
				
				if(CNT < cnt)
				{
					CNT	= cnt;
					Y	= y + K;
					X	= x;
				}
			}
		
		StringBuilder sb = new StringBuilder();
		sb.append(X).append(' ').append(Y).append('\n').append(CNT);
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}