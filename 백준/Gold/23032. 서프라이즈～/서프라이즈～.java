//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/23032
//1초 / 512MB
//요약 : 배열에서 특정 범위 내에서 연속된 두그룹으로 나눠 그룹당 합을 뺏을 때 차가 최소가되면서 그 합은 최대가되는 두그룹의 합이 가장 큰 것 출력
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 2<=이천
		int psum[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			psum[i] += psum[i-1] + read();// 1~N번학생까지 스테이크 무게(1<=만)
		
		int minDiff = Integer.MAX_VALUE;
		int maxSum	= 0;
		
		// 모든 범위 bruteforce
		for(int m=1; m<N; m++)
		{
			int s = m;		// 가운데 부터 시작
			int e = m+1;	// 가운데 부터 시작
			while(0<s && e<=N)
			{
				int leftSum = psum[m] - psum[s-1];
				int rightSum= psum[e] - psum[m];
				int sum		= leftSum + rightSum;
				int absDiff = Math.abs(leftSum - rightSum);
				if(absDiff < minDiff)
				{
					minDiff = absDiff;
					maxSum = sum;
				}
				else if(absDiff == minDiff && maxSum < sum)
					maxSum = sum;
				
				if(leftSum < rightSum)
					--s;
				else
					++e;
			}
		}
		
		System.out.print(maxSum);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}