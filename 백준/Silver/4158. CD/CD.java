//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/4158
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N==0 && M == 0)
				break;
            
			int res = 0;
			int arr[] = new int[N];
            
			for(int i=0; i<N; i++)
				arr[i] = Integer.parseInt(br.readLine());
			
			for(int i=0; i<N; i++)
			{
				int g = Integer.parseInt(br.readLine());
				int s = 0;
				int e = N-1;
				while(s <= e)
				{
					int mid = (s + e) / 2;
					if(arr[mid] == g) {
						res++;
						break;
					}else if(arr[mid] < g) {
						s = mid+1;
					}else {
						e = mid-1;
					}
				}
			}
			sb.append(res).append('\n');
		}

		System.out.print(sb.toString());
	}
}