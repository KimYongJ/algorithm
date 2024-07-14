// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		long sum	= 0;
		int arr[]	= new int[N];
		
		for(int i=0; i<N; i++) 
		{
			arr[i] = Integer.parseInt(br.readLine()) + 1;
		}
		
		Arrays.sort(arr);
		
		for(int i=N-1, j = 1; i>=0; i--, j++) 
		{
			if(arr[i] - j > 0)
			{
				sum += arr[i] - j;
			}
			else break;
		}
		
		System.out.print(sum);
	}
}