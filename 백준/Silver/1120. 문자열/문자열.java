//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1120
class Main{
	static String read() throws Exception{
		StringBuilder sb = new StringBuilder();
		int c = System.in.read();
		while(c <= 32) {c = System.in.read();}
		while(c > 32) {sb.append((char)c);
			c = System.in.read();}
		return sb.toString();
	}
	public static void main(String[] args)throws Exception{
		String s1	= read();
		String s2	= read();
		int LEN		= s2.length() - s1.length();
		int MIN		= Integer.MAX_VALUE;
		
		for(int i=0, cnt = 0; i<=LEN && MIN != 0; i++, cnt = 0)
		{
			for(int j=0; j<s1.length(); j++)
				if(s1.charAt(j) != s2.charAt(i + j))
					++cnt;
			MIN = Math.min(cnt, MIN);
		}
		System.out.print(MIN);
	}
}