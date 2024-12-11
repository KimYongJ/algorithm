//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13506
class Main{
    public static String readString() throws Exception {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = System.in.read()) > 13) {sb.append((char) c);}
        return sb.toString();
    }
	public static void main(String[] args)throws Exception{
		String text		= readString();
		int tlen		= text.length();
		int textFail[]	= new int[tlen];
		for(int i=1,j=0; i<tlen; i++)
		{
			while(0<j && text.charAt(i) != text.charAt(j))
				j = textFail[j - 1];
			
			if(text.charAt(i) == text.charAt(j))
				textFail[i] = ++j;
		}
		
		int len = textFail[tlen - 1];
		
		while(len > 0)
		{
			for(int i=0; i<tlen-1; i++)
				if(textFail[i] == len) {
					System.out.print(text.substring(0,len));
					return;
				}
			len = textFail[len - 1];
		}
		System.out.print(-1);
	}
}
