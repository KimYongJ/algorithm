// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0) {
			int N		= read()+1;
			int arr[]	= new int[N];
			for(int i=1; i<N; i++)
				arr[read()] = read();
			
			int cnt = 1;
			int score = arr[1];
			for(int i=2; i<N; i++)
			{
				if(arr[i] < score) 
				{
					score = arr[i];
					cnt++;
					if(score == 1)
						break;
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}