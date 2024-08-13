// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			int N = Integer.parseInt(br.readLine());
			int arr[] = new int[N];
			int base[] = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				base[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(base);
			
			int left	= 0;
			int right	= N-1;
			boolean flag = true;
			
			for(int i=0; i<N; i++) 
				if(flag = !flag)arr[left++] = base[i];
				else arr[right--] = base[i];
			
			int MAX = 0;
			for(int i=1; i<N; i++)
			{
				int diff = Math.abs(arr[i] - arr[i-1]);
				if(MAX < diff)
				{
					MAX = diff;
				}
			}
			sb.append(MAX).append('\n');
		}
		System.out.print(sb.toString());
	}
}