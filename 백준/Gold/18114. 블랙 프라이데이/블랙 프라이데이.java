//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18114
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 물건의 개수(1<=오천)
		int C		= read();		// 무게(1<=억)
		int arr[]	= new int[N];	// 무게(1<=억)
		for(int i=0; i<N; i++)
		{
			arr[i] = read();
			if(arr[i] == C)
			{
				System.out.print(1);
				return;
			}
		}
		
		Arrays.sort(arr);
		
		int s = 0;
		int e = N - 1;
		while(s < e)
		{
			int sum = arr[s] + arr[e];
			if(sum == C)
			{
				System.out.print(1);
				return;
			}
			if(sum > C)
				e--;
			else
			{
				int target = C - sum;
				int ss = s + 1;
				int ee = e - 1;
				while(ss <= ee)						// 가운데 숫자도 이분탐색으로 찾음
				{
					int mid = (ss + ee) >> 1;
					if(arr[mid] == target)
					{
						System.out.print(1);
						return;
					}
					if(arr[mid] < target)
						ss = mid + 1;
					else
						ee = mid - 1;
				}
				
				s++;
				
			}
		}
		
		System.out.print(0);
	}
}