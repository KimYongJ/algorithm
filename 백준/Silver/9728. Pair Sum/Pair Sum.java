//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/9728
// 1초 / 128MB
// 요약 : 두 원소의 합이 정확히 M과 같은 것의 숫자를 센다, 배열은 오름차순정렬되있고 모두 다름
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();// 10만이하
		for(int i=1; i<=T; i++)
		{
			int N		= read();	// 2<=이만
			int M		= read();
			int arr[]	= new int[N];// 1<=십억

			for(int j=0; j<N; j++)
				arr[j] = read();
			
			int s = 0;
			int e = N-1;
			int c = 0;
			while(s<e)
			{
				int sum = arr[s] + arr[e];
				if(sum == M)// 원소가 모두 다르기 때문에 값이 같다면 s,e를 모두 변경
				{
					++c;
					++s;
					--e; 
				}
				else if(sum < M)
					++s;
				else
					--e;
			}
			sb.append("Case #").append(i).append(": ").append(c).append('\n');
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}