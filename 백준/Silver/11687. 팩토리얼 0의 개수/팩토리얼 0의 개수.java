//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11687

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static int getZeroCnt(int N) {
		int cnt = 0;
		for(int i=5; i<=N; i*=5)
			cnt += N/i;
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());	// 1<=억
		int s = 1;
		int e = 1_000_000_000;
		int res = -1;
		while(s <= e)
		{
			int mid = (s + e) >> 1; // N의 값을 이분 탐색으로 찾음
			int zeroCnt = getZeroCnt(mid); // N값을 전달하여 N!일때, 5의 개수가 몇개인지 찾음
			if(M <= zeroCnt)
			{
				e = mid - 1;
				if(M == zeroCnt)
					res = mid;
			}else {
				s = mid + 1;
			}
		}
		System.out.print(res);
	}
}