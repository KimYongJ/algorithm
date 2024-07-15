// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N		= Integer.parseInt(br.readLine()) * 2;
		int arr[]	= new int[N];
		int left	= 0;
		int right	= N-1;
		int sum		= 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int min	= arr[left] + arr[right];
		while(++left < --right) 
		{
			sum = arr[left] + arr[right];
			min = Math.min(min, sum);
		}
		System.out.print(min);
	}
}