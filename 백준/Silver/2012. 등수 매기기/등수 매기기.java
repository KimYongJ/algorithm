//https://github.com/KimYongJ/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		long res	= 0;
		int rnk		= 1;
		int N		= read()+1;
		int arr[]	= new int[500001];
		
		for(int i=1; i<N; i++)
			arr[read()]++;

		for(int i=1; i<500001 && rnk != N; i++) 
			while(arr[i]-- > 0)
				res += Math.abs(i - rnk++);
		
		System.out.print(res);
	}
}