//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3779
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder	sb = new StringBuilder();
		int CASE = 0;
		while(true)
		{
			int N = read();
			
			if(N == 0)
				break;
			
			String text = readString();
			int len		= text.length();
			int fail[]	= new int[len];
			
			for(int i=1, j=0; i<len; i++)
			{
				while(0<j && text.charAt(i) != text.charAt(j))
					j = fail[j - 1];
				
				if(text.charAt(i) == text.charAt(j))
					fail[i] = ++j;
			}
			
			sb.append("Test case #").append(++CASE).append('\n');
			
			for(int i=1; i<len; i++)
				if(fail[i] != 0)
				{
					int idx		= i + 1;
					int period	= idx - fail[i];
					if(idx % period == 0)
						sb.append(idx).append(' ')
							.append(idx / period).append('\n');
				}

			sb.append('\n');
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
