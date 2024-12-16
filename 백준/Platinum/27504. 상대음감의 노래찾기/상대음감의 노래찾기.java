//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27504
//1초, 1024MB
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		int arr[][]	= new int[N][];
		// 각 순서마다 각 음의 차이 값을 담음
		for(int i=0, len = 0; i<N; i++)
		{
			len		= read() - 1;
			arr[i]	= new int[len];
			for(int j=0,s = read(), e=0; j<len; j++, s = e)
			{
				e = read();
				arr[i][j] = e - s;
			}
		}
		
		int len			= read() - 1;
		int pattern[]	= new int[len];
		// 패턴에 대해 차이 값을 기준으로 배열에 담음
		for(int i=0, s = read(), e = 0; i<len; i++, s = e)
		{
			e = read();
			pattern[i] = e - s;
		}
		// 입력된 패턴에 대해 fail을 구함
		int fail[] = new int[len];
		for(int i=1, j=0; i<len; i++)
		{
			while(0<j && pattern[i] != pattern[j])
				j = fail[j - 1];
			
			if(pattern[i] == pattern[j])
				fail[i] = ++j;
		}
		// 패턴에 대해 매칭되는 것이 있는지 확인
		StringBuilder sb = new StringBuilder();
		for(int idx=0; idx<N; idx++)
		{
			int len2 = arr[idx].length;
			if(len <= len2)
			{
				for(int i=0, j=0; i<len2; i++)
				{
					while(0<j && arr[idx][i] != pattern[j])
						j = fail[j - 1];
					
					if(arr[idx][i] == pattern[j])
					{
						if(j == len - 1)
						{
							sb.append(idx + 1).append(' ');
							break;
						}
						else ++j;
					}
				}
				
			}
		}
		System.out.print(sb.length() == 0 ? -1 : sb.toString());
	}
}
