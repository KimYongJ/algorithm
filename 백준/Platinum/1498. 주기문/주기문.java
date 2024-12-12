//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1498
class Main{
    public static String readString() throws Exception {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = System.in.read()) > 13) {sb.append((char) c);}
        return sb.toString();
    }
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		char text[] = readString().toCharArray();
		int len		= text.length;
		int fail[]	= new int[len];
		// fail을 구함과 동시에 답도 구한다. 
		for(int i=1,j=0; i<len; i++)
		{
			while(0<j && text[i] != text[j])
				j = fail[j - 1];
			
			if(text[i] == text[j])
				fail[i] = ++j;
			// 답구하는 부분
			if(fail[i] != 0)
			{
				int idx		= i + 1;
				int period	= idx - fail[i];
				if(idx % period == 0)
					sb.append(idx).append(' ').append(idx / period).append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}
