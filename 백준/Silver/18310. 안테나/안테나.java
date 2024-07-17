// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N				= read();
		int idx				= N / 2 + (N % 2 != 0 ? 1 : 0) ;
		int arr[]			= new int[100001];
		
		for(int i=0; i<N; i++) 
			arr[read()]++;
		
		for(int i=0; i<=100000 && idx > 0; i++) 
			if((idx-= arr[i]) <= 0) 
				System.out.print(i);
	}
}