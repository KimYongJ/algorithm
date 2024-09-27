//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14003
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static int binarySearch(int s, int e, int target, int LIS[]) {
		int idx = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(LIS[mid] < target)
				s = mid + 1;
			else
			{
				e = mid - 1;
				idx = mid;
			}
		}
		
		return idx;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N		= Integer.parseInt(br.readLine());	// 개수(1<=백만)
		int len		= 1;			// 부분수열의 시작 길이
		int arr[]	= new int[N];	// -십억<=+십억
		int LIS[]	= new int[N];	// 부분수열을 담을 배열
		int idxArr[]= new int[N];	// LIS에 들어갈 숫자들이 arr의 몇번째 인덱스에 있는지를 순서대로 담을 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		LIS[0]		= arr[0];
		idxArr[0]	= 0;
		
		for(int i=1; i<N; i++)
			if(LIS[len - 1] < arr[i])
			{
				LIS[len] = arr[i];
				idxArr[i] = len;
				len++;
			}
			else
			{
				int idx = binarySearch(0, len - 1, arr[i], LIS);
				LIS[idx] = arr[i];
				idxArr[i] = idx;
			}
		
		sb.append(len).append('\n');
		int result[] = new int[len];
		
		len -= 1;
		
		
		for(int i=N-1; i>=0; i--)
			if(idxArr[i] == len)
			{
				result[len] = arr[i];
				len--;
			}
		
		for(int l : result)
			sb.append(l).append(' ');
		
		System.out.print(sb.toString());
	}
}