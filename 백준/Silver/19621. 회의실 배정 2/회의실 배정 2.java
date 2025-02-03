//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19621
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Node{
	int s, e, cnt;
	Node(int s, int e, int cnt){
		this.s=s; this.e=e; this.cnt=cnt;
	}
}
class Main{
	
	static int N;
	static int[] dp,endTime;
	static Node[] node;
	
	public static void main(String[]args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());	//회의 수(1<=25)
		dp		= new int[N+1];
		endTime	= new int[N+1];
		node	= new Node[N+1];
		
		node[0] = new Node(0,0,0);
		
		for(int i=1; i<=N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());	// 0<=int최대값, 회의시간은 모두 다름
			int e = Integer.parseInt(st.nextToken());	// 0<=int최대값, 회의시간은 모두 다름
			int c = Integer.parseInt(st.nextToken());	// 회의 인원(1<=천)
			node[i] = new Node(s,e,c);
		}
		
		Arrays.sort(node, (a,b)->a.e-b.e);
		
		for(int i=1; i<=N; i++)
		{
			if(endTime[i-1] <= node[i].s)
			{
				dp[i] = dp[i-1] + node[i].cnt;
				endTime[i] = node[i].e;
			}
			else
			{
				int idx = getBeforeMaxCnt(i, node[i].s);
				if(dp[i-1] < dp[idx] + node[i].cnt)
				{
					dp[i] = dp[idx] + node[i].cnt;
					endTime[i] = node[i].e;
				}
				else if(dp[i-1] > dp[idx] + node[i].cnt)
				{
					dp[i] = dp[i-1];
					endTime[i] = endTime[i-1];
				}
				else
				{
					dp[i] = dp[i-1];
					endTime[i] = Math.min(endTime[i-1], node[i].e);
				}
			}
		}
		System.out.print(dp[N]);
	}
	public static int getBeforeMaxCnt(int idx, int targetTime) {
		while(--idx >=0)
			if(endTime[idx] <= targetTime)
				return idx;
		return 0;
	}
}
