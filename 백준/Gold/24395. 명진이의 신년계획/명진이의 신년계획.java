//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/24395
//1초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Node implements Comparable<Node>{
	int idx, risk;
	Node(int idx, int risk){
		this.idx = idx;
		this.risk = risk;
	}
	@Override
	public int compareTo(Node o) {
		if(risk == o.risk)
			return idx - o.idx;
		return risk - o.risk;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());// 학생수 N(1<=십만)
		int M		= Integer.parseInt(st.nextToken());// 질병종류 M(1<=백)
		int dp[][]	= new int[51][51];// 빨, 파 약에 따른 위험도의 최대 합

		for(int i=0; i<51; i++)
			Arrays.fill(dp[i], -1);
		
		dp[0][0] = 0;
		
		for(int i=1; i<=M; i++)
		{
			st		= new StringTokenizer(br.readLine());
			int red = Integer.parseInt(st.nextToken());// 빨강약수(0<=50)(1<=빨+파약수)
			int blue= Integer.parseInt(st.nextToken());// 파랑약수(0<=50), (1<=빨+파약수)
			int risk= Integer.parseInt(st.nextToken());// 위험도(0<=100)
			
			for(int y=50; y>=0; y--)
			{
				for(int z=50; z>=0; z--)
				{
					if(y<red || z<blue || dp[y-red][z-blue] == -1)
						continue;
					
					dp[y][z] = Math.max(dp[y][z], dp[y-red][z-blue] + risk);
				}
			}
		}
		
		Node[] node = new Node[N];
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			node[i] = new Node(i+1, Math.max(0,dp[r][b]));
		}
		
		Arrays.sort(node);
		
		StringBuilder sb = new StringBuilder();
		
		for(Node n : node)
			sb.append(n.idx).append(' ')
				.append(n.risk).append('\n');
		
		System.out.print(sb);
	}
}