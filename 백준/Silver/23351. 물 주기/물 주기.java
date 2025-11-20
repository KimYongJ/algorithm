//https://www.acmicpc.net/problem/23351
//1초 512MB
//6 3 2 2 / N(2<=100), K(1<=100), A,B (1<=A*B<=N, A는 N의 약수)
//첫 캣닢이 죽는날 : 5
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int N, K, A, B;
	static int [] arr;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());// N(2<=100) 화분 개수
		K = Integer.parseInt(st.nextToken());// K(1<=100) 초기 값
		A = Integer.parseInt(st.nextToken());// A (1<=A*B<=N, A는 N의 약수) 물을줄 화분의 개수
		B = Integer.parseInt(st.nextToken());// B (1<=A*B<=N) 늘어나는 수분의 양
		arr = new int[N];

		Arrays.fill(arr, K);
		
		int day = 1;
		int idx = 0;
		
		while(true)
		{
			
			for(int i=0; i<A; i++)
			{
				arr[idx] += B;
				idx = (idx + 1) % N;
			}
			
			for(int i=0; i<N; i++)
			{
				if(--arr[i] == 0)
				{
					System.out.print(day);
					return;
				}
			}
			++day;
		}
	}
}