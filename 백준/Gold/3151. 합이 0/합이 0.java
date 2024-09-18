//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3151

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 학생수 (1<=만)
		int arr[]	= new int[N];						// 학생들의 코딩 실력(-만<=만)
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);

		long cnt = 0;
		for(int i=0; i<N-2; i++) 
		{
			for(int j=i+1; j<N-1; j++)
			{
				int target = -(arr[i] + arr[j]);
				int s = j + 1;
				int e = N - 1;
				int idx = 0;
				while(s <= e) // 가장 작은 target의 인덱스 찾기
				{
					int mid = (s + e) >> 1;
					if(arr[mid] >= target)
					{
						e = mid - 1;
						if(arr[mid] == target)
							idx = mid;
					}
					else
						s = mid + 1;
				}
				
				s = j + 1;
				e = N - 1;
				int idx1 = 0;
				while(s <= e) // 가장 큰 target의 인덱스 찾기
				{
					int mid = (s + e) >> 1;
					if(arr[mid] <= target)
					{
						s = mid + 1;
						if(arr[mid] == target)
							idx1 = mid;
					}else e = mid - 1;
				}
				
				if(idx != 0)
				{
					if(idx == idx1)
						cnt++;
					else cnt += (idx1 - idx) + 1;
				}
				
			}
		}
		System.out.print(cnt);
	}
}