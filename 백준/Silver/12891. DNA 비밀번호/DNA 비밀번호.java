//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12891
class Main{
	
	static char[] str;
	static int len, P, result;
	static int[] ACGT;
	
	public static void main(String[] args)throws Exception{
		len		= read();
		P		= read();
		str		= readString().toCharArray();
		ACGT	= new int[90];
		ACGT['A'] = read();
		ACGT['C'] = read();
		ACGT['G'] = read();
		ACGT['T'] = read();

		for(int i=0; i<P; i++)
			ACGT[str[i]]--;
		
		if(validate())
			++result;
		
		for(int i=P; i<len; i++)
		{
			ACGT[str[i - P]]++;
			ACGT[str[i]]--;
			if(validate())
				++result;
		}
		
		System.out.print(result);
	}
	public static boolean validate() {
		return 0 >= ACGT['A'] && 0 >= ACGT['C'] && 0 >= ACGT['G'] && 0 >= ACGT['T'];
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