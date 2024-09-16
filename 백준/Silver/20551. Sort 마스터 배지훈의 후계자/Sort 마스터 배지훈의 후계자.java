//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20551
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());	// 배열 A의 원소 개수 (1<=이십만)
		int M = Integer.parseInt(st.nextToken());	// 질문의 개수 (1<=이십만)
		int arr[] = new int[N];						// 원소를 담을 배열(-십억<=+십억)
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		while(M-->0)
		{
			int targetNumber = Integer.parseInt(br.readLine());
			int s = 0;
			int e = N-1;
			int idx = -1;
			while(s <= e)
			{
				int mid = (s + e) >> 1;
				if(arr[mid] >= targetNumber)
				{
					e = mid -1;
					if(arr[mid] == targetNumber)
						idx = mid;
				}else
					s = mid + 1;

			}
			sb.append(idx)
				.append('\n');
		}
		System.out.print(sb.toString());
	}
}