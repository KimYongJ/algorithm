//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3066
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
class Main{
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
				
				int idx = Collections.binarySearch(list, now);
				
				if(idx < 0)
					idx = -(idx+1); // 찾으려는 now가 없는 경우 음수를 반환하는데, 이 때 now가 들어가야할 위치를 원래위치 + 1을 음수로 변환해서 반환한다.
				
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