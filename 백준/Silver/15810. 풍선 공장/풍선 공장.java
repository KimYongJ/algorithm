//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15810
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static boolean check(int arr[], long maxTime, long targetNumber){
		for(int a : arr)
			targetNumber -= maxTime / a;
		return targetNumber <= 0;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 스테프 수(1<=백만)
		int M = Integer.parseInt(st.nextToken());	// 목표 풍선 개수(1<=백만)
		int arr[] = new int[N];						// 각 스태프들이 풍선을 만드는데 걸리는 시간(1<=백만)
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		long s = 1;
		long e = 1_000_000_000_001L; // 1명이 백만개의 풍선을 만드는데, 하나만들 때 마다 백만시간이 걸린다는 가정하에
		long minTime = 0;
		while(s <= e)
		{
			long mid = (s + e) >> 1;	// 풍선을 만들 시간
 			if(check(arr, mid, M))
			{
				e = mid -1;
				minTime = mid;
			}else
				s = mid + 1;
		}
		System.out.print(minTime);
	}
}