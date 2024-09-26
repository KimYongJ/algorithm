//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1208
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	public static void getSum(int arr[], int s, int e, ArrayList<Integer> list, int sum) {
		if(s == e)
		{
			list.add(sum);
			return;
		}
		getSum(arr, s+1, e, list, sum);
		getSum(arr, s+1, e, list, sum+arr[s]);
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 정수개수(1<=40)
		int S		= Integer.parseInt(st.nextToken());	// 목표값(-백만<=백만)
		int arr[]	= new int[N];						// 원소들(-십만<=십만)
		long res	= 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> left	= new ArrayList<>();
		ArrayList<Integer> right= new ArrayList<>();
		
		getSum(arr, 0, N/2, left, 0);	// 배열을 둘로 쪼개어 left와 right에 각각 나올 수 있는 모든 부분수열의 합을 구함
		getSum(arr, N/2, N, right,0);	// 배열을 둘로 쪼개어 left와 right에 각각 나올 수 있는 모든 부분수열의 합을 구함
		
		Collections.sort(left);
		Collections.sort(right);
		
		int len	= left.size();
		int s	= 0;
		int e	= right.size() - 1;
		
		while(s < len && 0 <= e)
		{
			int lnum	= left.get(s);
			int rnum	= right.get(e);
			int sum		= lnum + rnum;
			if(sum == S)
			{
				long leftCnt = 0;
				long rightCnt= 0;
				while(s < len  && left.get(s) == lnum)
				{
					++leftCnt;
					++s;
				}
				
				while(0 <= e && right.get(e) == rnum)
				{
					++rightCnt;
					--e;
				}
				
				res += leftCnt * rightCnt;
			}
			else if(sum < S)
				s++;
			else
				e--;
		}
		if(S == 0)	 // 0 + 0 일 때를 제거함
			--res;
		System.out.print(res);
	}
}
