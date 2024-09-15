//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2776
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int res = 0;
			int arr1[] = new int[N];
			int arr2[] = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				arr1[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++)
				arr2[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(arr2);
            
			for(int n : arr1)
			{
				int s = 0;
				int e = M-1;
				int idx = -1;
				while(s <= e)
				{
					// 숫자n이 arr2에서 몇번째 서열인지
					int mid = (s + e) / 2;
					if(arr2[mid] < n)
					{
						idx = mid;
						s = mid + 1;
					}
					else
						e = mid - 1;
				}
				if(idx != -1)
					res += idx + 1;
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}