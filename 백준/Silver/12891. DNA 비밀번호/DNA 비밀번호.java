//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12891
class Main{
	public static void main(String[] args)throws Exception{
		char C[] = {'A','C','G','T'};
		int len		= read();
		int P		= read();
		char str[]	= readString().toCharArray();
		int ACGT[]	= new int[90];
		int CNT[]	= new int[90];

		for(int i=0; i<4; i++)
			ACGT[C[i]] = read();
		
		for(int i=0; i<P; i++)
			CNT[str[i]]++;
		
		int cnt = cal(ACGT, CNT, C);
		
		for(int i=P; i<len; i++)
		{
			CNT[str[i - P]]--;
			CNT[str[i]]++;
			cnt += cal(ACGT, CNT, C);
		}
		
		System.out.print(cnt);
	}
	private static int cal(int[] ACGT, int[] CNT, char[] ch) {
		for(char c : ch)
			if(ACGT[c] > CNT[c])
				return 0;
		return 1;
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