//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18353
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());//1<=이천
		int arr[]	= new int[N+1];
		int idx		= 0;
		
		arr[0] = 10_000_001;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			int num = Integer.parseInt(st.nextToken());//1<=천만
			if(arr[idx] > num)
				arr[++idx] = num;
			else
			{
				// arr에서 num보다 작은 가장 큰 수 인덱스
				arr[binarySearch(arr, num, 1, idx)] = num;
			}
		}
		System.out.print(N - idx);
	}
	public static int binarySearch(int[] arr, int target, int s, int e) {
		int res = 0;
		while(s <= e) {
			int mid = (s + e) >> 1;
			if(arr[mid] < target) {
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		return res;
	}
}