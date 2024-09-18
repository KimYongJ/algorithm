//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3151
class Main{
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();						// 학생수 (1<=만)
		int arr[]	= new int[N];					// 학생들의 코딩 실력(-만<=만)
		long result	= 0;
		int count[] = new int[40001];				// 두 학생의 합을 담아 놓을 배열 (인덱스 범위: -20000 ~ 20000, 0을 기준으로 함)
		
		for (int i = 0; i < N; i++) 
			arr[i] = read();
		
		for (int i = 0; i < N; i++)
		{
			result += count[20000 - arr[i]];		// 세 번째 학생이 선택된 상태에서, 두 학생의 합과 더해서 0이 되는 경우를 찾음
			
			// 현재 학생 이전의 학생들과 두 학생의 합을 미리 count 배열에 기록
			for (int j = 0; j < i; j++) {			// 첫 번째와 두 번째 학생 선택
				count[20000 + arr[i] + arr[j]]++;	// 두 학생의 합을 0을 기준으로 인덱싱하여 count 배열에 기록
			}
		}

		System.out.print(result);
	}
}