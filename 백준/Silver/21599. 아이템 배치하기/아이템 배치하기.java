// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		boolean visit[] = new boolean[N];
		int cnt = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			if(0 != Integer.parseInt(st.nextToken()))
				cnt++;
		}
//		Arrays.sort(arr);
//		for(int i=N-1; i>=0; i--) 
//		{
//			int idx = i;
//			while(arr[i]-->0 && idx >= 0) 
//			{
//				if(!visit[idx])
//				{
//					visit[idx] = true;
//					cnt++;
//				}
//				--idx;
//			}
//		}
		System.out.print(cnt);
	}
}