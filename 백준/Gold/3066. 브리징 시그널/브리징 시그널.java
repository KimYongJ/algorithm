//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3066
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int getIdx(int LIS[], int target, int len)
	{
		int s	= 0;
		int e	= len - 1;
		int idx	= 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(target == LIS[mid])
				return mid;
		    else if(LIS[mid] < target)
            {
				s = mid + 1;
				idx = mid + 1;
			}
			else
				e = mid - 1;
		}
		return idx;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			int N		= read();			// 포트의개수(1<=사만)
			int arr[]	= new int[N];
			
			for(int i=0; i<N; i++)
				arr[i] = read();			// 입력이 이미 정렬되어있기 때문에 입력된 부분에 대해서 가장 긴 부분수열(Longest Increasing Subsequence)만 구하면된다.

			int len		= 1;
			int LIS[]	= new int[N];		// 최장 증가하는 부분 수열을 담을 배열
			LIS[0]		= arr[0];			// 초기값을 arr[0]번째 값으로 넣는다.
			
			for(int i=1; i<N; i++)
			{
				if(arr[i] > LIS[len-1])		// 찾을값인 arr[i]가 LIS의 마지막 값보다 크다면 바로 LIS에 넣음 
					LIS[len++] = arr[i];
				else if(LIS[0] > arr[i])	// LIS의 가장 앞부분이 arr[i]보다 크다면 LIS[0]을 작은 값으로 대입함
					LIS[0] = arr[i];
				else
					// LIS에서 arr[i]값 보다 작은 값중 가장 큰 IDX의 +1을 구해온다.
					LIS[getIdx(LIS, arr[i], len)] = arr[i];		// IDX + 1 위치에 값 덮어씌우기
			}
			sb.append(len).append('\n');
		}
		System.out.print(sb.toString());
	}
}
