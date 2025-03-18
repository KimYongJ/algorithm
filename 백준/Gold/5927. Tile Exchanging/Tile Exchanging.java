//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5927
import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
      final int MAX = 1<<30;
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N		= Integer.parseInt(st.nextToken()); 	// 갖고있는 타일 개수(1<=10)
      int M		= Integer.parseInt(st.nextToken()); 	// 목표면적(1<=만)
      int len[] = new int[N + 1];						// 갖고있는 한변의 길이
      int dp[][]= new int[N + 1][M + 1];				// 해당 면적을 만들기 위한 최소 비용

      Arrays.fill(dp[0], MAX);

      for(int i=1; i<=N; i++)
      {
        len[i] = Integer.parseInt(br.readLine());		// 현재 갖고 있는 타일의 한변 길이 1<=100
        Arrays.fill(dp[i], MAX);
      }

      dp[0][0] = 0;										// 0번째 타일을 사용해 넓이0을 만드는데 드는 비용 0

      for(int i=1; i<=N; i++)							// 현재 갖고있는 타일 반복
      {
    	  for(int l=1; l*l<=M; l++)						// 현재 i번째 타일을 통해 만들, 한변이 길이 L
    	  {
			  int newArea = l * l;						// L을 통해 넓이를 구함
			  int cost = (len[i] - l) * (len[i] - l);	// i번째 타일로 L을 만드는데 드는 비용
			  
			  for(int a=newArea; a <= M; a++)
				  // MAX( i번째 타일을 사용해 a만큼의 넓이를 만들 때 비용,  이전 타일의 a-newArea 넓이를 만들 때 드는 비용 + 현재타일을 통해 newArea넓이를 만드는 비용 ) 
				  dp[i][a] = Math.min(dp[i][a], dp[i-1][a-newArea] + cost);
    	  }
      }
      
      System.out.print(dp[N][M] == MAX ? -1 : dp[N][M] );
    }
}