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
			if(arr[i] > 0)
				break;
			
			int left	= i + 1;
			int right	= N - 1;
			while(left < right)
			{
				int sum = arr[i] + arr[left] + arr[right];
				if(0 < sum)
					right--;
				else if(sum < 0)
					left++;
				else	// 원하는 값을 찾았다면 중복된 값 체크
				{
					if(arr[left] == arr[right])
					{
						int len = right - left + 1;
						int add = len * (len - 1) / 2;
						cnt += (long)add;
						break;
					}
					int leftCount = 1;
					while(left + 1< right && arr[left + 1] == arr[left]) {
						leftCount++;
						left++;
					}
					
					int rightCount = 1;
					while(left < right - 1 && arr[right - 1] == arr[right]) {
						rightCount++;
						right--;
					}
					
					cnt += (long)leftCount * rightCount;
					
					left++;
				}
			}
			
		}
		System.out.print(cnt);
	}
}