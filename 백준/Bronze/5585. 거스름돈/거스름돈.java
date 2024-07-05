// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		final int arr[]		= {500, 100, 50, 10, 5, 1};
		int num				= 1000 - Integer.parseInt(br.readLine());
		int result 			= 0;
		
		for(int i=0; i<arr.length && num != 0; i++) 
		{
			result += num / arr[i];
			num %= arr[i];
		}
		
		System.out.print(result);
	}
}