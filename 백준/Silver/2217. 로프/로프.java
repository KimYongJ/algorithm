// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int result	= 0;
		int cnt		= 1;
		int arr[]	= new int[N];
		
		for(int i=0; i<N; i++) 
		{
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		for(int i=N-1; i>=0; i--) 
		{
			result = Math.max(result, arr[i] * cnt++);
		}
		System.out.print(result);
	}
}