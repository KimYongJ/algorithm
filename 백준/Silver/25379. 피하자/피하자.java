//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25379
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> even = new ArrayList<>();
		for(int i=0; i<N; i++){
			int n = Integer.parseInt(st.nextToken());
			if(n % 2 == 0) {
				even.add(i);
			}
		}
		int len = even.size() - 1;
		int res1 = 0, res2 = 0;
		if(len >= 0) {
			res1 = even.get(0); // 왼쪽에서 오른쪽 탐색
			res2 = N-1-even.get(len); // 오른쪽에서 왼쪽 탐색
			int first1 = 1;
			int first2 = N-2;
			for(int i=1,j=len-1; i<=len; i++,j--,first1++, first2--) 
			{
				res1 += even.get(i) - first1;
				res2 += first2 - even.get(j);
			}
		}
		System.out.print(Math.min(res1, res2));
	}
}