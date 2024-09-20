//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3066
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main{
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
			if(target == list.get(mid)) {
				return mid;
			}
			if(list.get(mid) < target) {
				s = mid + 1;
				idx = mid + 1;
			}
			else if(target <= list.get(mid))
				e = mid - 1;
		}
		return idx;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			int N		= Integer.parseInt(br.readLine());	// 포트의개수(1<=사만)
			int arr[][] = new int[N][2];
			for(int i=0; i<N; i++)
			{
				arr[i][0] = i + 1;
				arr[i][1] = Integer.parseInt(br.readLine());
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
