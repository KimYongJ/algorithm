// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int ans		= 0;
		int arr[]	= new int[N];
		
		for(int i=0; i<N; i++){
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		
		for(int i=N-2; i>=0; i--) 
		{
			if(arr[i] >= arr[i+1]) 
			{
				ans += arr[i] - (arr[i+1] - 1);
				arr[i] = (arr[i+1] - 1);
			}
		}
		System.out.print(ans);
	}
}