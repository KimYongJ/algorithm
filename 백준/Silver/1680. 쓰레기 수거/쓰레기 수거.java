//https://www.acmicpc.net/problem/1680
//1초 128MB
//3	// 테스트 케이스 수
//2 2	// 쓰레기 차의 용량 W, 지점개수 N(1 <= W <= 1000, 1 <= N <= 1000)
//1 1	// N개의 줄에 i번째 지점의 쓰레기장으로 부터의 거리 xi, 쓰레기양 wi가 주어짐, xi(0<=100000)는 다르고 오름차순으로주어짐
//2 2
//6 3
//1 1
//2 2
//3 3
//3 3
//1 2
//2 2
//3 1
//답
//8
//6
//10
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());// 쓰레기 차의 용량(1 <= W <= 1000)
			int N = Integer.parseInt(st.nextToken());// 지점개수 N(1 <= N <= 1000)
			int I[][] = new int[N+1][2];
			
			for(int i=1; i<=N; i++)
			{
				st = new StringTokenizer(br.readLine());
				I[i][0] = Integer.parseInt(st.nextToken()); // 쓰레기장으로 부터의 거리(0<=100,000)
				I[i][1] = Integer.parseInt(st.nextToken()); // 쓰레기 무게
			}
			
			int weight = 0; // 현재까지 추가한 쓰레기의 무게
			int dist = 0;// 이동 거리
			
			int prev = 0;
			for(int i=1; i<=N; i++)
			{
				dist += I[i][0] - prev; // 이전 정거장에서 현재까지 이동 거리
				
				prev = I[i][0];
				
				if(weight + I[i][1] > W) // 쓰레기차의 용량을 채우거나 초과시
				{
					dist += I[i][0] * 2;// 쓰레기장을 갖다오는 거리
					weight = 0;// 무게는 0
				}
				
				weight += I[i][1];// 현재 지점에서 쓰레기 수거
				
				if(weight == W) // 쓰레기가 꽉찬경우
				{
					dist += I[i][0];// 쓰레기장을 갖다오는 거리
					weight = 0;// 무게는 0
					prev = 0;
				}
			}
			if(weight > 0)
				dist += prev; // 마지막에서 쓰레기장까지 추가해줌
			
			sb.append(dist).append('\n');
		}
		System.out.print(sb);
	}
}