//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2492
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{int x,y;Node(int x,int y){this.x=x;this.y=y;}}

class Main{
	
	static int Y, X, CNT;
	static int N, M, T, K;
	static Node node[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N	= Integer.parseInt(st.nextToken());	// 지도 넓이(1<=백만)
		M	= Integer.parseInt(st.nextToken());	// 지도 높이(1<=백만)
		T	= Integer.parseInt(st.nextToken());	// 금강석의 개수(1<=100)
		K	= Integer.parseInt(st.nextToken());	// 정사각형의 크기(1<=백만)
		node= new Node[T];
		
		for(int i=0; i<T; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			node[i] = new Node(x,y);
		}
		
		for(Node n1 : node)
			for(Node n2 : node)
			{
				int x	= n1.x + K > N ? N - K : n1.x;
				int y	= n2.y + K > M ? M - K : n2.y;
				int cnt	= 0;
				for(Node n : node) 
					if(x<=n.x && n.x<=x+K && y<=n.y && n.y<=y+K)
						++cnt;
				
				if(CNT < cnt)
				{
					CNT = cnt;
					Y = y + K;
					X = x;
				}
			}
		
		StringBuilder sb = new StringBuilder();
		sb.append(X).append(' ').append(Y).append('\n').append(CNT);
		System.out.print(sb);
	}
}