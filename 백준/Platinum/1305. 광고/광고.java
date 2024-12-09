//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1305
class Main{
	public static void main(String[] args)throws Exception{
		int N				= read();
		StringBuilder sb	= readString();
		int len				= sb.length();
		int []fail			= new int[len];
		
		for(int i=1,j=0; i<len; i++)
		{
			while(0<j && sb.charAt(i) != sb.charAt(j))
				j = fail[j - 1];
			if(sb.charAt(i) == sb.charAt(j))
				fail[i] = ++j;
		}
		
		System.out.print(N - fail[len-1]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
    public static StringBuilder readString() throws Exception {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = System.in.read()) > 13) {sb.append((char) c);}
        return sb;
    }
}