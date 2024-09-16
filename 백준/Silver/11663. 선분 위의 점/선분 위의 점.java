//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11663
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static int getFirst(int arr[], int len, int goal) { // arr에서 goal보다크거나 같은 가장 왼쪽 값 
		int res = 0;
		long s = 0;
		long e = len - 1;
		while(s <= e)
		{
			int mid = (int)((s + e) >> 1);
			if(arr[mid] < goal)
				s = mid + 1;
			else
			{
				e = mid -1;
				res = mid;
			}
		}
		return res;
	}
	public static int getLast(int arr[], int len, int goal) { // arr에서 goal보다 작거나 같은 가장 오른쪽 값 
		int res = 0;
		long s = 0;
		long e = len - 1;
		while(s <= e)
		{
			int mid = (int)((s + e) >> 1);
			if(arr[mid] <= goal)
			{
				s = mid + 1;
				res = mid;
			}
			else
				e = mid - 1;
		}
		return res + 1;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N		= Integer.parseInt(st.nextToken());	// 점의개수(1<=십만)
		int M		= Integer.parseInt(st.nextToken());	// 선분의 개수(1<=십만)
		int arr[]	= new int[N];						// 점의 좌표를 담을 배열(좌표는 1<=십억)
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int f		= Integer.parseInt(st.nextToken());// 선분의 시작
			int l		= Integer.parseInt(st.nextToken());// 선분의 끝
			int first	= 0;
			int last	= 0;
			if(arr[N-1] >= f && l >= arr[0])
			{
				first	= getFirst(arr, N, f);
				last	= getLast(arr, N, l);
			}
			sb.append(last - first)
				.append('\n');
		}
		System.out.print(sb.toString());
	}
}