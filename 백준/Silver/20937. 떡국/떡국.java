// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		int arr[]	= new int[50001];
		int max		= 0;
		int num;

		for(int i=0; i<N; i++)
		{
			num = read();
			if(++arr[num] > max) 
			{
				max = arr[num];
			}
		}
		System.out.print(max);
	}
}