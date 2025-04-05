//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6208
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Node
{
	int w,f,c;
	Node(int w,int f, int c)
	{
		this.w=w;// 부품길이
		this.f=f;// 재미점수
		this.c=c;// 비용
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L	= Integer.parseInt(st.nextToken());// 롤러코스터 길이L(1<=1,000)
		int N	= Integer.parseInt(st.nextToken());// 부품의 개수N(1<=10,000)
		int B	= Integer.parseInt(st.nextToken());// 쓸수있는 예산B(1<=1,000)
		ArrayList<Node> list[] = new ArrayList[L + 1];
		
		for(int i=0; i<=L; i++)
			list[i] = new ArrayList<>();
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x	= Integer.parseInt(st.nextToken());// 시작위치X(0<=L-W)
			int w	= Integer.parseInt(st.nextToken());// 부품길이W(1<=L)
			int f	= Integer.parseInt(st.nextToken());// 재미점수F(1<=1,000,000)
			int c	= Integer.parseInt(st.nextToken());// 비용C(1<=1,000)
			if(x + w <= L)
				list[x].add(new Node(w, f, c));
		}
		
		// dp[위치][비용] = 최대 재미
		int dp[][] = new int[L + 1][B + 1];
		
		dp[0][0] = 1;// 유효한 값을 마킹하여 0초과인 경우만 연산을 하도록 하기 위한 조치
		
		for(int l=0; l<=L; l++)
		{
			for(int b=0; b<=B; b++)
			{
				
				if(dp[l][b] == 0)
					continue;
				
				for(Node now : list[l])
				{
					int nextLeng = l + now.w;
					int nextCost = b + now.c;
					if(nextCost <= B)
						dp[nextLeng][nextCost] = Math.max(dp[nextLeng][nextCost], dp[l][b] + now.f);
				}
			}
		}
		
		int res = -1;
		
		for(int c=0; c<=B; c++)
			res = Math.max(dp[L][c], res);
		
		System.out.print(res - 1);
	}
}