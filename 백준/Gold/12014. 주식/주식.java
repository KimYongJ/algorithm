//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12014
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static int getIdx(int LIS[], int target, int e, int s) {
		int idx = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(LIS[mid] == target)
				return mid;
			if(LIS[mid] < target) {
				s = mid + 1;
			}else {
				e = mid - 1;
				idx = mid;
			}
		}
		return idx;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // test case 2<=100
		for(int t=1; t<=T; t++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N		= Integer.parseInt(st.nextToken());	// 1<=만
			int K		= Integer.parseInt(st.nextToken());	// 1<=만
			int arr[]	= new int[N];
			int LIS[]	= new int[N];
			int len		= 1;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			LIS[0] = arr[0];
			for(int i=1; i<N; i++)
			{
				if(LIS[len - 1] < arr[i])
					LIS[len++] = arr[i];
				else
					LIS[getIdx(LIS, arr[i], len - 1, 0)] = arr[i];
			}
			
			sb.append("Case #").append(t).append('\n')
				.append(len >= K ? 1 : 0).append('\n');
		}
		System.out.print(sb.toString());
	}
}