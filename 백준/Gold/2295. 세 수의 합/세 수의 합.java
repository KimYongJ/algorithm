//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2295
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
class Main
{
	public static void main(String[] args)throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 5<=1000
		int arr[]	= new int[N];						// 1<=이억
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		for(int i=N-1; i>=0; i--)
			for(int j=i; j>=0; j--)
			{
				int target = arr[i] - arr[j]; // 두 수를 합친 숫자
				if(target <= 0)
					continue;
				
				int s = 0;
				int e = j;
				while(s <= e)
				{
					int sum = arr[s] + arr[e];
					if(sum == target) {
						System.out.print(arr[i]);
						return;
					}
					if(sum < target)
						s++;
					else e--;
				}
				
			}
		
	}
}