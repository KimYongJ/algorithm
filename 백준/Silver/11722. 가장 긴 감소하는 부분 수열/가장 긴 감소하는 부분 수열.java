//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11722
//1초 / 256MB
//가장 긴 감소하는 부분수열의 길이 출력
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main{

	public static void main(String[]args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N+1];// 1<=천
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// 1<=천
		
		ArrayList<Integer> list = new ArrayList<>();
		
		list.add(arr[1]);
		
		for(int i=2; i<=N; i++)
		{
			int idx = binarySearch(list, arr[i]); // arr[i]보다 작은 숫자의 인덱스를 가져온다.
			if(idx < 0)
				list.add(0,arr[i]);	// 자기보다 작은 수가 없으면 맨 앞에 넣음
			else
			{
				list.remove(idx);
				list.add(idx, arr[i]);
			}
		}
		System.out.print(list.size());
	}
	public static int binarySearch(ArrayList<Integer> list, int target) {
		int s = 0;
		int e = list.size() - 1;
		int res = -1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(list.get(mid) <= target) {
				res = mid;
				s = mid + 1;
			}
			else
				e = mid - 1;
		}
		return res;
	}
}