//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/21921
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 총일수(1<=25만)
		int X		= read();	// 기간(1<=25만)
		int sum		= 0;		// K기간동안 합계
		int max		= 0;		// 최대방문자 수
		int cnt		= 1;		// 최대 방문자 수와 같은 날들의 수
		int arr[]	= new int[N];

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		for(int i=0; i<X; i++)
			sum += arr[i];
		
		max = sum;
		
		for(int i=X,j=0; i<N; i++,j++)
		{
			sum += arr[i] - arr[j];
			if(sum == max)
				++cnt;
			else if(max < sum)
			{
				cnt = 1;
				max = sum;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if(max == 0)
			sb.append("SAD");
		else
			sb.append(max).append('\n').append(cnt);
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}