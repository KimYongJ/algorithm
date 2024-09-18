//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2295
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 5<=1000
		int arr[]	= new int[N];						// 1<=이억
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		for(int i=N-1; i>=0; i--)
			for(int j=i; j>=0; j--)
				for(int k=j; k>=0; k--)
				{
					int target = arr[i] - (arr[j] + arr[k]);
					if(target <= 0) break;
					
					if(Arrays.binarySearch(arr, target) >= 0)
					{
						System.out.print(arr[i]);
						return;
					}
				}
		
	}
}