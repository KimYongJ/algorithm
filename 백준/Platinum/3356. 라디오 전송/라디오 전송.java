//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3356
class Main{
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
	public static void main(String[] args)throws Exception{
		int len		= read();
		char ptn[]	= readString().toCharArray();
		int fail[]	= new int[len];
		
		for(int i=1, j=0; i<len; i++)
		{
			while(0<j && ptn[i] != ptn[j])
				j = fail[j - 1];
			
			if(ptn[i] == ptn[j])
				fail[i] = ++j;
		}
		System.out.print(len - fail[len-1]);
	}
}