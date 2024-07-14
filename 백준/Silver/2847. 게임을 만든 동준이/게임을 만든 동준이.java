// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		int ans		= 0;
		int arr[]	= new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		for(int i=N-2; i>=0; i--) 
			if(arr[i] >= arr[i+1]) 
			{
				ans += arr[i] - (arr[i+1] - 1);
				arr[i] = (arr[i+1] - 1);
			}
		
		System.out.print(ans);
	}
}