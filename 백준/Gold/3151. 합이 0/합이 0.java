//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3151
import java.util.Arrays;
class Main{
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 학생수 (1<=만)
		int arr[]	= new int[N];	// 학생들의 코딩 실력(-만<=만)
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
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
				
				if(sum == 0)// 원하는 값을 찾았다면 중복된 값 체크
				{
					if(arr[left] == arr[right])
					{
						long len = right - left + 1;// 양쪽이 같으면 조합을 구함
						cnt += len * (len - 1) / 2;
						break;
					}
					
					int leftCount = 1;
					while(left + 1< right && arr[left + 1] == arr[left])
					{
						leftCount++;
						left++;
					}
					
					int rightCount = 1;
					while(left < right - 1 && arr[right - 1] == arr[right])
					{
						rightCount++;
						right--;
					}
					
					cnt += (long)leftCount * rightCount;
				}
				
				if(0 < sum)
					right--;
				else
					left++;
			}
			
		}
		System.out.print(cnt);
	}
}