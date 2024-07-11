// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());
		long W		= Long.parseLong(st.nextToken());
		long coin	= 0;
		long arr[]	= new long[N+1];
		
		arr[0] = Integer.parseInt(br.readLine());
		
		for(int i=1; i<N; i++) 
		{
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i-1] < arr[i]) // 내일이 오를 경우 
			{
				if(W / arr[i-1] > 0) 
				{
					coin = W / arr[i-1];// 오늘 가격으로 코인을 산다.
					W %= arr[i-1];
				}
			}
			else // 내일이 떨어지거나 오늘과 같은 경우 
			{
				W += coin * arr[i-1];
				coin = 0;
			}
		}
		System.out.print(W + coin * arr[N-1]);
	}
}