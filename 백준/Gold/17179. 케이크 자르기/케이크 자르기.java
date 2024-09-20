//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17179
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static boolean check(int arr[], int mid , int cut, int len) {
		int start	= 0;
		int cutCnt	= 0;
		int lastIdx = 0;
		for(int i=0; i<arr.length && cutCnt < cut; i++)
		{
			if(arr[i] - start >= mid)
			{
				lastIdx = i;
				cutCnt++;
				start = arr[i];
			}
		}
		return cutCnt >= cut && len - arr[lastIdx] >= mid;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N		= Integer.parseInt(st.nextToken());	// 자르는 횟수(1<=천)
		int M		= Integer.parseInt(st.nextToken());	// 자르는 지점(1<=천)
		int L		= Integer.parseInt(st.nextToken());	// 롤케익길이(1<x<=사백만)
		int arr[]	= new int[M];						// 자를 수 있는 지점
		
		for(int i=0; i<M; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		while(N-->0)
		{
			int cut = Integer.parseInt(br.readLine()); // 자르는 횟수
			int s	= 1;
			int e	= L / 2;
			int res	= 0;
			
			while(s <= e)
			{
				int mid = (s + e) >> 1;	// 가장 작은 조각의 최대 길이
				if(check(arr, mid, cut, L))
				{
					s = mid + 1;
					res = mid;
				}
				else e = mid - 1;
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}