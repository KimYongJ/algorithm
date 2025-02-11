//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11568
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());//1<=천
		int LIS[]	= new int[N+1];
		int idx		= 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			// 1<=억
			int num = Integer.parseInt(st.nextToken());
			if(LIS[idx] < num)
				LIS[++idx] = num;
			else
				// LIS에서 num보다 크거나 같은 가장 작은수
				LIS[binarySearch(LIS, num,1, idx)] = num;
		}
		System.out.print(idx);
	}
	public static int binarySearch(int[] arr, int target, int s, int e) {
		int res = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(arr[mid] >= target)
			{
				res = mid;
				e = mid - 1;
			}
			else s = mid + 1;
		}
		return res;
	}
}
