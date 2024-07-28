//https://github.com/KimYongJ/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int K = read();
		int M = (int)Math.pow(2,(int)Math.ceil(Math.log(K) / Math.log(2)));
		int C = 0;
		sb.append(M).append(' ');
		if(K != M) 
		{
			while(K != 0) 
			{
				C++;
				M /= 2;
				if(K>=M)
					K-=M;
			}
		}
		sb.append(C);
		System.out.print(sb.toString());
	}
}