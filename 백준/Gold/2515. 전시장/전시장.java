//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2515
import java.util.Arrays;
class Node{
	int H, C;Node(int H, int C){this.H=H; this.C=C;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N = read();	// 그림 개수(1<=삼십만)
		int S = read();	// 판매 가능 그림길이(1<=이천만 & S<=H)
		Node list[] = new Node[N];
		for(int i=0; i<N; i++)
		{
			int h = read();	// 높이(1<=이천만)
			int c = read();	// 가격(1<=천)
			list[i] = new Node(h,c);
		}
		
		Arrays.sort(list,(a,b)-> a.H != b.H ? a.H-b.H : a.C - b.C);// 높이 기준 오름차순 정렬,가격 기준 오름차순 정렬 
		
		int dp[][] = new int[N][2];	// 각 그림을 설치했을 때와, 설치하지 않았을 때 최댓값을 담는다.
		dp[0][0] = 0;				// 0번째 그림을 설치하지 않았을 때 최댓값
		dp[0][1] = list[0].C;		// 0번째 그림을 설치했을 때 최댓값 
		
		for(int i=1; i<N; i++)
		{
			// 그림i를 설치하지 않았을 때 최대값
			dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);	
			// 그림i를 설치했을 때 최대값
			dp[i][1] = list[i].C; 
			
			int targetHeight = list[i].H - S; // 해당 높이를 갖는 값이 가장큰 것을 찾음
			int s	= 0;
			int e	= i-1;
			int idx = -1;
			while(s <= e)
			{
				int mid = (s + e) >> 1;
				if(list[mid].H <= targetHeight)
				{
					s	= mid + 1;
					idx = mid;
				}
				else
					e = mid - 1;
			}
			if(idx != -1)	// targetHeight와 가장 가까운 인덱스를 찾으면 더해줌
				dp[i][1] += Math.max(dp[idx][0], dp[idx][1]);
		}
		
		System.out.print(Math.max(dp[N-1][0],dp[N-1][1]));
	}
}