//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19947
//1초 / 512mb

class Main{
	public static void main(String[] args)throws Exception{
		int H		= read();	// 초기비용 만<=십만
		int Y		= read();	// 투자 기간 0<=10
		int arr[]	= new int[11];
		arr[0]		= H;
		
		for(int i=1; i<=Y; i++)
		{
			arr[i] = (int)(arr[i-1] * 1.05);
			
			if(3 <= i)
				arr[i] = Math.max(arr[i], (int)(arr[i-3] * 1.2));
		
			if(5 <= i)
				arr[i] = Math.max(arr[i], (int)(arr[i-5] * 1.35));
		}
		
		System.out.print(arr[Y]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}