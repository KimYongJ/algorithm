//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13423
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0)
		{
			int N		= Integer.parseInt(br.readLine());	// 점의 개수(3<=천)
			int arr[]	= new int[N];						// 점을 담을 배열(-1억<=1억)
			int res		= 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(arr);
			
			for(int i=1; i<N-1; i++)
			{
				int leftIdx	= i - 1;
				int rightIdx= i + 1;
				while(leftIdx >= 0 && rightIdx < N)
				{
					int leftInterval	= arr[i] - arr[leftIdx];
					int rightInterval	= arr[rightIdx] - arr[i];
					if(leftInterval == rightInterval)
					{
						res++;
						leftIdx--;
						rightIdx++;
					}else if(leftInterval < rightInterval)
						leftIdx--;
					else
						rightIdx++;
				}
				
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}