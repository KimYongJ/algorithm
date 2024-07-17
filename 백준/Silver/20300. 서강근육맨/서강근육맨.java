// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		long arr[]	= new long[N];
		int left	= 0;
		int right	= N-1;
		long max	= 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		if(N%2 == 1)	// 홀수인 경우 마지막을 max로 놓고, 나머지들을 비교연산한다.
		{
			right --;
			max = arr[N-1];
		}
		
		while(left < right) 
		{
			max = Math.max(max, arr[left++] + arr[right--]);
		}
		
		System.out.print(max);
	}
}