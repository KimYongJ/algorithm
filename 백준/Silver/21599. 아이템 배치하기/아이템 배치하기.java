// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N];
		int minIdx	= N;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		if(arr[N-1] == 0)
		{
			System.out.print(0);
		}
		else 
		{
			for(int i=N-1; i>=0; i--) 
			{
				if(arr[i] == 0) break;
				minIdx = Math.min(minIdx, i - arr[i] + 1);
			}
			System.out.print(minIdx < 0 ? N : N - minIdx);
		}
	}
}