//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1786
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		char[] text		= readString().toCharArray();
		char[] pattern	= readString().toCharArray();
		int tlen		= text.length;
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

		for(int i=0, j=0; i<tlen; i++)
		{
			while(0<j && text[i] != pattern[j])
				j = fail[j - 1];
			
			if(text[i] == pattern[j])
			{
				if(j == plen - 1)
				{
					sb.append(i - j + 1).append(' ');
					j = fail[j];
					++cnt;
				}
				else ++j;
			}
		}
		sb.insert(0, '\n');
		sb.insert(0, cnt);
		System.out.print(sb);
	}
    public static String readString() throws Exception {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = System.in.read()) > 13) {
            sb.append((char) c);
        }
        return sb.toString();
    }
}

