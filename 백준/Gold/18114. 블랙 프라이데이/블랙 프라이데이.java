//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18114
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static boolean findTargetNumber(int target, int arr[], int s, int e) {
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(arr[mid] == target)
				return true;
			if(arr[mid] < target)
				s = mid + 1;
			else
				e = mid - 1;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 물건의 개수(1<=오천)
		int C		= Integer.parseInt(st.nextToken());	// 무게(1<=억)
		int arr[]	= new int[N];						// 무게(1<=억)
		int res		= 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] == C)
			{
				System.out.print(1);
				return;
			}
		}
		
		Arrays.sort(arr);
		
		int s = 0;
		int e = N - 1;
		while(s < e)
		{
			int sum = arr[s] + arr[e];
			if(sum == C)
			{
				res = 1;
				break;
			}
			if(sum > C)
				e--;
			else
			{
				int target = C - sum;
				if(findTargetNumber(target, arr,s + 1, e - 1))
				{
					res = 1;
					break;
				}
				s++;
			}
		}
		System.out.print(res);
	}
}