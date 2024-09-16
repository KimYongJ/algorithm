//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11663
import java.util.Arrays;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int getFirst(int arr[], int len, int goal) { // arr에서 goal보다크거나 같은 가장 왼쪽 값 
		int res	= 0;
		int s	= 0;
		int e	= len - 1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
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
		int res	= 0;
		int s	= 0;
		int e	= len - 1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
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
		StringBuilder sb = new StringBuilder();
		int N		= read();		// 점의개수(1<=십만)
		int M		= read();		// 선분의 개수(1<=십만)
		int arr[]	= new int[N];	// 점의 좌표를 담을 배열(좌표는 1<=십억)
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		while(M-->0)
		{
			int f		= read();	// 선분의 시작
			int l		= read();	// 선분의 끝
			int first	= 0;
			int last	= 0;
			if(arr[N-1] >= f && l >= arr[0])	// 유효범위 안일 때만 연산
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