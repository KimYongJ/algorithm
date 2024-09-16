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
			
			for(int i=0; i<N-2; i++)
				for(int j=i+1; j<N-1; j++)
				{
					int next = (arr[j] << 1) - arr[i];
					for(int k=j+1; k<N; k++)
					{
						if(arr[k] == next)
							res ++;
						if(next < arr[k])
							break;
					}
				}
			
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}