//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15489
//1초 / 512mb

class Main{
	public static void main(String[] args)throws Exception{
		int R		= read();	// y 좌표
		int C		= read();	// x 좌표
		int W		= read();	// 변길이
		int len		= R + W;
		int arr[][] = new int[len][len];
		
		arr[0][0] = 1;
		
		for(int y=1; y<len; y++)
			for(int x=1; x<=y; x++)
				arr[y][x] = arr[y-1][x] + arr[y-1][x-1];

		long sum = 0;
		
		for(int y=R, limit = C; y<len; y++, limit++)
			for(int x=C; x<=limit; x++)
				sum += arr[y][x];

		System.out.print(sum);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}