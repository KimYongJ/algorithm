//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2632
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int target		= read();// 목표 크기 1<=이백만
		int m			= read();// 3<=천
		int n			= read();// 3<=천
		int arr1[]		= new int[m << 1];
		int arr2[]		= new int[n << 1];
		int prevSum1[]	= new int[2_000_001];// 각 배열의 누적합을 카운팅 해줄 배열 ex) 누적합 결과 21 => prevSum1[21]++;
		int prevSum2[]	= new int[2_000_001];// 각 배열의 누적합을 카운팅 해줄 배열
		long cnt		= 0;
		int sum1		= 0;
		int sum2		= 0;
		
		for(int i=0; i<m; i++)
			sum1 += arr1[i] = arr1[i + m] = read();
		
		for(int i=0; i<n; i++)
			sum2 += arr2[i] = arr2[i + n] = read();


		prevSum1[sum1]	= 1;// 피자 A의 모든 조각을 다 사용했을 때의 합을 1로 설정(추후 곱하기를 하기 때문에 1처리)
		prevSum1[0]		= 1;// 부분합이 0인 경우(추후 곱하기를 하기 때문에 1처리)
		prevSum2[sum2]	= 1;// 피자 B의 모든 조각을 다 사용했을 때의 합을 1로 설정(추후 곱하기를 하기 때문에 1처리)
		prevSum2[0]		= 1;// 부분합이 0인 경우(추후 곱하기를 하기 때문에 1처리)
		
		for(int i=0; i<m; i++)	// 배열 arr1의 누적합
		{
			sum1 = 0;
			for(int j=i; j<i + m - 1; j++)
				prevSum1[sum1 += arr1[j]] ++;
		}
		for(int i=0; i<n; i++)	// 배열 arr2의 누적합
		{
			sum2 = 0;
			for(int j=i; j<i + n - 1; j++)
				prevSum2[sum2 += arr2[j]] ++;
		}
		
		for(int i=0; i<=target; i++)
			cnt += prevSum1[i] * prevSum2[target - i];
		
		System.out.print(cnt);
	}
}