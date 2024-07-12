// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int result	= 0;
		int arr[]	= new int[10001];
		
		for(int i=0; i<N; i++) 
		{
			arr[Integer.parseInt(br.readLine())]++;
		}
		
		for(int i=10000, j = 0; i>=0; i--) {
			j += arr[i];
			result = Math.max(result, i * j);
		}
		System.out.print(result);
	}
}