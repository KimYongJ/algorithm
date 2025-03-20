//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11089
//2초 256MB
//목표 : 공집합빼고, 회로를 형성하는 부분집합의 개수 출력, 답은 100억 보다 작음이 보장됨, 집합원소내 순서는 상관없음
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int x, y;
	Node(int x, int y){
		this.x=x;
		this.y=y;
	}
}
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int offset = 400;
		int N		= Integer.parseInt(br.readLine());//벡터수(N<=40)
		Node[]node	= new Node[N + 1];
		long dp[][][]= new long[N + 1][801][801];
		
		for(int i=1; i<=N; i++)
		{
			// |x|,|y| <=10, 둘다0인경우는 없음, 주어진 모든 벡터들은 유일함
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			node[i] = new Node(x,y);
		}
		
		// 선택하지 않은 경우 dp[i][x][y] += dp[i-1][x][y]
		// 선택한 경우 dp[i][x + x1][y + y1] += dp[i-1][x][y];
		
		dp[0][offset][offset] = 1;
		
		for(int i=1; i<=N; i++)
		{
			int nx = node[i].x;
			int ny = node[i].y;
			for(int x=-400; x<=400; x++)
			{
				for(int y=-400; y<=400; y++)
				{
					dp[i][x + offset][y + offset] += dp[i-1][x + offset][y + offset];
					if(x+nx>= -400 && x+nx<=400 && y+ny>=-400 && y+ny<=400)
						dp[i][x + nx + offset][y + ny + offset] += dp[i-1][x + offset][y + offset];
				}
			}
		}
		System.out.print(dp[N][offset][offset] - 1);// 공집합 제거
	}

}