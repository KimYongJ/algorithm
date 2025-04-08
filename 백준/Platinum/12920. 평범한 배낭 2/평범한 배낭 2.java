//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/12920
//1초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 물건 종류의수 N(1<=100)
		int M = Integer.parseInt(st.nextToken());// 가방의 최대 무게 M(1<=10,000)
		List<Integer> W = new ArrayList<>();// 무게
		List<Integer> V = new ArrayList<>();// 가치
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());// 물건 무게 V(1<=M)
			int c = Integer.parseInt(st.nextToken());// 만족도 C(1<=10,000)
			int k = Integer.parseInt(st.nextToken());// 물건 개수 K(1<=10,000) / 1<= V*K<= 10,000)
			
			int cnt = 1;	// 이진 분할 플레그
			// 남은 선택개수(K)가 이진 분할 플레그보다 작거나 같을 때 계속 반복
			while(cnt <= k)
			{
				W.add(cnt * v);// 무게
				V.add(cnt * c);// 가치
				k -= cnt;// 선택개수 감소
				cnt <<= 1;// 이준 분할 플레그 2배 증가
			}
			// 이진 분할을 하지 못하는 나머지도 하나의 묶음으로 만듬
			if(k != 0)
			{
				W.add(k * v);// 무게
				V.add(k * c);// 가치
			}
		}
		
		int dp[] = new int[M + 1];
		
		for(int i=0; i<W.size(); i++)
		{
			int nowWeight = W.get(i);
			int nowValue = V.get(i);
			
			for(int j=M; j>=nowWeight; j--)
			{
				dp[j] = Math.max(dp[j], dp[j-nowWeight] + nowValue);
			}
		}
		System.out.print(dp[M]);
	}
}
//최대 무게를 넘기지 않게 물건을 담을 때 최대 만족도 출력
//2 3		// 물건 종류의수 N(1<=100),가방의 최대 무게 M(1<=10,000)
//2 7 1	// 물건 무게 V(1<=M), 만족도 C(1<=10,000),물건 개수 K(1<=10,000) / 1<= V*K<= 10,000)
//1 9 3
//답 : 27