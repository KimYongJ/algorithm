//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1701
class Main{
    public static String readString() throws Exception {
        StringBuilder sb = new StringBuilder(); int c;
        while ((c = System.in.read()) > 13) {sb.append((char) c);}
        return sb.toString();
    }
	public static void main(String[] args)throws Exception{
		char[] text	= readString().toCharArray();
		int len		= text.length;
		int max		= 0;
		for(int start=0; start<len; start++)
		{
			int fail[] = new int[len - start];
			
			for(int i=1, j=0; i<len - start; i++)
			{
				while(0<j && text[j + start] != text[i + start])
					j = fail[j - 1];
						
				if(text[j + start] == text[i + start])
					max = Math.max(max,fail[i] = ++j);
			}
		}
		
		System.out.print(max);
	}
}