// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		int res = -1;
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		Arrays.sort(arr);
		
		for(int i=N-1; i>=2; i--) {
			int sum = arr[i-1] + arr[i-2];
			if(arr[i] < sum) {
				res = sum + arr[i];
				break;
			}
		}
		
		System.out.print(res);
	}
}