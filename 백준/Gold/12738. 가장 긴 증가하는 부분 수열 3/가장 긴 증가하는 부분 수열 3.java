//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12738
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static int getIdx(int LIS[], int target, int e) {
		int s	= 0;
		int res = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(target <= LIS[mid])
			{
				e = mid - 1;
				res = mid;
			}
			else
				s = mid + 1;
		}
		return res;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int len = 1;
		int LIS[] = new int[N];
		LIS[0] = arr[0];
		
		for(int i=1; i<N; i++)
		{
			if(LIS[len-1] < arr[i])
				LIS[len++] = arr[i];
			else {
				int idx = getIdx(LIS, arr[i], len - 1);
				LIS[idx] = arr[i];
			}
		}
		System.out.print(len);
	}
}