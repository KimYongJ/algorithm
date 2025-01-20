//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12892
//2초 / 512MB
//요약 : 배열에서 가격의 최소 최대 격아가 D미만이면서, 만족도가 가장 높은 만족도 값 출력
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 친구수 (1<=십만)
		long D		= Integer.parseInt(st.nextToken());	// 선물의 최대 가격차 (1<=십억)
		long sum	= 0;								// 가장큰 만족도 값
		long result = 0;
		long arr[][]= new long[N][2];	//[0] : 가격, [1] : 만족도
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());// 선물가격(0<=십억)
			arr[i][1] = Integer.parseInt(st.nextToken());// 만족도(0<=1억1)
		}
		// 가격 기준으로 오름차순 정렬
		Arrays.sort(arr, (a,b)->Long.compare(a[0], b[0]));
		
		int s = 0;
		int e = 0;
		while(e<N)
		{
			while(D <= arr[e][0] - arr[s][0])
			{
				sum -= arr[s++][1];
			}
			
			sum += arr[e++][1];	// 만족도 추가
			
			result = Math.max(result, sum);
		}
		System.out.print(result);
	}
}
