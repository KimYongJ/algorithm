// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N];
		int res		= 0;
		
		for(int i=0; i<N; i++) 
		{
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		for(int i=0; i<N/2; i++) 
		{
			int tmp = arr[i];
			arr[i] = arr[N-i-1];
			arr[N-i-1] = tmp;
		}
		
		for(int i=0; i<N; i++) 
		{
			if((i+1)%3 == 0) 
			{
				continue;
			}
			res += arr[i];
		}
		System.out.print(res);
	}
}