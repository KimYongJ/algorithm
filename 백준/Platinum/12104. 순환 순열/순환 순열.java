//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12104
class Main{
    public static String readString() throws Exception {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = System.in.read()) > 13) {
            sb.append((char) c);
        }
        return sb.toString();
    }
	public static void main(String[] args)throws Exception{
		char pattern[]	= readString().toCharArray();
		int plen		= pattern.length;
		int fail[]		= new int[plen];
		int cnt			= 0;
		
		for(int i=1, j=0; i<plen; i++)
		{
			while(0<j && pattern[i] != pattern[j])
				j = fail[j - 1];
			
			if(pattern[i] == pattern[j])
				fail[i] = ++j;
		}
		
		String str = readString();

		StringBuilder sb = new StringBuilder();
		sb.append(str).append(str);

		char[] text = sb.toString().toCharArray();
		int tlen	= text.length - 1;
		
		for(int i=0,j=0; i<tlen; i++)
		{
			while(0<j && text[i] != pattern[j])
				j = fail[j - 1];
			
			if(text[i] == pattern[j])
			{
				if(j == plen - 1)
				{
					j = fail[j];
					++cnt;
				}
				else ++j;
			}
		}
		
		
		System.out.print(cnt);
	}
}