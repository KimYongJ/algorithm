//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20667
//1초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int cpu, memory, priority;
	Node(int c, int m, int p){
		cpu = c;
		memory = m;
		priority = p;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());// 크롭탭수(1<=100)
		int C		= Integer.parseInt(st.nextToken());// 목표CPU사용량(1<=천
		int M		= Integer.parseInt(st.nextToken()) + 1;// 목표메모리할당량(1<=십만)
		int psum	= 0;
		Node node[] = new Node[N];
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());// CPU(1<=목표CPU할당량)
			int m = Integer.parseInt(st.nextToken());// 메모리(1<=목표메모리할당량)
			int p = Integer.parseInt(st.nextToken());// 우선순위(1<=5)
			node[i] = new Node(c,m,p);
			psum += p;
		}
		// cpu 사용량 : 우선순위 = 메모리할당량의 최대
		int dp[][] = new int[C + 1][psum + 1];
		dp[0][0] = 1;
		for(Node now : node)
		{
			for(int c=C; c>=0; c--)
				for(int p=psum; p>=0; p--)
				{
					int nextC = Math.min(C,c + now.cpu);
					int nextP = p + now.priority;
					if(dp[c][p] != 0)
					{
						dp[nextC][nextP] = Math.max(dp[nextC][nextP], dp[c][p] + now.memory);
					}		
				}
		}
		
		for(int p = 1; p<=psum; p++)
			if(dp[C][p] >= M)
			{
				System.out.print(p);
				return;
			}
			
		System.out.print(-1);
	}
}