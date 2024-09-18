//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2295
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
class Main
{
	public static void main(String[] args)throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashSet<Integer> set = new HashSet<>();
		int N		= Integer.parseInt(br.readLine());	// 5<=1000
		int arr[]	= new int[N];						// 1<=이억
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		// 두수의 합 미리 저장
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				set.add(arr[i] + arr[j]);
		
		// 가장 큰수부터 확인하기
		for(int i=N-1; i>=0; i--)
		{
			for(int j=0; j<N; j++) {
				if(set.contains(arr[i] - arr[j])) {
					System.out.print(arr[i]);
					return;
				}
			}
		}
		
	}
}