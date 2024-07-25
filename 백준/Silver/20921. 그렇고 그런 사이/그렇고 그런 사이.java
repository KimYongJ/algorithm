// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N		= read();
		int K		= read();
		int arr[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = i;

		for(int i=1; i<=N && K != 0; i++) 
		{
			for(int j=N; j>i; j--) 
			{
				int tmp = arr[j];
				arr[j]	= arr[j-1];
				arr[j-1]= tmp;
				if(--K == 0) 
					break;
			}
		}
		
		for(int a=1;a<=N;a++)
			sb.append(arr[a]).append(' ');
		
		System.out.print(sb.toString());
	}
}