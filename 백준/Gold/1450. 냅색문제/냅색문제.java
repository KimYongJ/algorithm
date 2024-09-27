//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1450
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	
	static int N, C, arr[];

	public static void getSum(int s, int e, int sum, ArrayList<Integer> list) {
		if(s == e)
		{
			if(sum <= C)
				list.add(sum);
			return;
		}
		getSum(s + 1, e, sum, list);
		if(sum+arr[s] <= C)
			getSum(s + 1, e, sum+arr[s], list);
	}
	public static int getUpperBound(ArrayList<Integer> list, int target) {
		int s = 0;
		int e = list.size() - 1;
		int idx = -1;
		while(s <= e) {
			int mid = (s + e) >> 1;
			if(list.get(mid) <= target)
			{
				s = mid + 1;
				idx = mid;
			}
			else
				e = mid - 1;
		}
		return idx;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N	= Integer.parseInt(st.nextToken());	// 물건개수(1<=30)
		C	= Integer.parseInt(st.nextToken());	// 총무게(0<=10억)
		arr = new int[N];						// 주어지는 무게들(1<=10억)
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		if(C == 0)
		{
			System.out.print(1);
			return;
		}
		
		ArrayList<Integer> left	= new ArrayList<>();
		ArrayList<Integer> right= new ArrayList<>();
		
		getSum(0, N/2, 0, left);	// arr의 0번째 인덱스부터 N/2인덱스까지 부분합을 모두 구해 left 리스트에 담는다.
		getSum(N/2, N, 0, right);	// arr의 N/2번째 인덱스부터 마지막까지 부분합을 모두 구해 right 리스트에 담는다. 
		
		Collections.sort(left);
		Collections.sort(right);
		
		long cnt	= 0;
		int len		= left.size();
		int s		= 0;
		
		while(s < len)
			cnt += getUpperBound(right, C - left.get(s++)) + 1;
		
		System.out.print(cnt);
	
	}
}
