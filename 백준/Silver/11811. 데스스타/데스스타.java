//https://www.acmicpc.net/problem/11811
//1초 256MB

class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = read();
		
		for(int i=0; i<N; i++)
		{
			int bit = 0;
			for(int j=0; j<N; j++)
				bit |= read();
			sb.append(bit).append(' ');
		}

		System.out.print(sb);
	}
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}