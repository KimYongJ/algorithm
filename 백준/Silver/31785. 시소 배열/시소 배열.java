//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/31785
//1초 / 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T		= Integer.parseInt(br.readLine());
		int arr[]	= new int[500_001];
		int s		= 0;
		int e		= 0;
		
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			if(type == 1)
				arr[e++] = Integer.parseInt(st.nextToken());
			else
			{
				int mid = s + (e - s)/2;
				
				int sum1 = getSum(arr, s, mid);
				int sum2 = getSum(arr, mid, e);
				if(sum1 <= sum2)
				{
					sb.append(sum1).append('\n');
					s = mid;
				}
				else
				{
					sb.append(sum2).append('\n');
					e = mid;
				}
			}
		}
		while(s<e)
			sb.append(arr[s++]).append(' ');
		System.out.print(sb);
	}
	public static int getSum(int arr[], int s, int e) {
		int sum = 0;
		while(s<e)
			sum += arr[s++];
		return sum;
	}
}