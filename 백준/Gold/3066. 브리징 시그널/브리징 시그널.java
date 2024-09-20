//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3066
import java.util.ArrayList;
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int getIdx(ArrayList<Integer> list, int target) {
		int size = list.size();
		if(size == 0)
			return 0;
		if(list.get(size-1) < target)
			return size;
		
		int s	= 0;
		int e	= list.size() - 1;
		int idx	= 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(target == list.get(mid))
				return mid;
		    else if(list.get(mid) < target)
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
			int N		= read();	// 포트의개수(1<=사만)
			int arr[][] = new int[N][2];
			for(int i=0; i<N; i++)
			{
				arr[i][0] = i + 1;
				arr[i][1] = read();
			}
			
			Arrays.sort(arr,(a,b)->a[0] - b[0]);
			
			// list의 y값 중, 가장 긴 증가하는 부분수열의 개수를 구하면된다.
			ArrayList<Integer> list = new ArrayList<>();
			for(int a[] : arr)
			{
				int now = a[1];
				
				int idx = getIdx(list, now); // list에서 now가 들어갈 위치를 가져온다.
				
				if(idx < list.size())
					list.set(idx, now);
				else
					list.add(now);
			}
			
			sb.append(list.size()).append('\n');
		}
		System.out.print(sb.toString());
	}
}
