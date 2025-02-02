//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9711
//1초 / 128mb
import java.math.BigInteger;

class Main{
	public static void main(String[] args)throws Exception{
		BigInteger dp[] = new BigInteger[10_001];
		dp[1] = BigInteger.ONE;
		dp[2] = BigInteger.ONE;
		
		for(int i=3; i<10_001; i++)
			dp[i] = dp[i-1].add(dp[i-2]);
		
		StringBuilder sb = new StringBuilder();
		int T = read();
		for(int i=1; i<=T; i++)
		{
			int P = read();
			BigInteger Q = new BigInteger(readString());
			
			sb.append("Case #").append(i).append(": ")
				.append(dp[P].mod(Q)).append('\n');
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
    public static String readString() throws Exception {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = System.in.read()) > 13) {sb.append((char) c);}
        return sb.toString();
    }
}