//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14746
//1.5초 / 512MB
//요약 : P,Q배열에 x좌표를 주고, y좌표는 c1,c2로 고정해놓고 P,Q간 가장 가까운 좌표거리와, 그 거리를 갖는 모든 쌍의 개수를 구하는 것
//비슷한 문제 : 두 배열에서의 차이가 최솟값인 것을 빠르게 찾는 문제(17095, 27931)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N	= Integer.parseInt(st.nextToken());	// P집합 점 개수(1 ≤ 500,000)
		int M	= Integer.parseInt(st.nextToken());	// Q집합 점 개수(1 ≤ 500,000)
		int P[] = new int[N];
		int Q[] = new int[M];
		
		st = new StringTokenizer(br.readLine());
		int c1	= Integer.parseInt(st.nextToken());	// |억|, P의 모든 점은 y = c1인 점에 있음 
		int c2	= Integer.parseInt(st.nextToken());	// |억|, Q의 모든 점은 y = c2인 점에 있음
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			P[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			Q[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(P);
		Arrays.sort(Q);
		
		int idx1 = 0;
		int idx2 = 0;
		int minDist = 1<<30;
		int pairCnt = 0;
		while(idx1<N && idx2<M)
		{
			int diff = Math.abs(P[idx1] - Q[idx2]);
			
			if(diff == minDist)
				++pairCnt;
			else if(diff < minDist)
			{
				minDist = diff;
				pairCnt	= 1;
			}
			
			if(P[idx1] < Q[idx2])
				++idx1;
			else
				++idx2;
		}
		
		System.out.printf("%d %d",minDist + Math.abs(c1-c2),pairCnt);
	}
}