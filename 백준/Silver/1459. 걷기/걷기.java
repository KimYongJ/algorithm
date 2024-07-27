//https://github.com/KimYongJ/algorithm
class Main{
	static long read() throws Exception {// 빠른 입력을 위한 함수
		long c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		long X = read();
		long Y = read();
		long W = read(); // 가로세로
		long S = read(); // 대각선
		long result1 = (X+Y)*(long)W; // 가로세로로만 
		long result2 = Math.min(X, Y) *S + Math.abs(X-Y)*W;
		long result3 = 0;
		if((X+Y) % 2 == 0) // 대각선으로만 갈 수 있다
			result3 = Math.max(X, Y) * S;
		else 
			result3 = (Math.max(X, Y)-1) * S + W;

		System.out.print(Math.min(result3,Math.min(result1, result2)));
	}
}