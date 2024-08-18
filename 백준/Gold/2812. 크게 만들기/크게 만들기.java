// https://github.com/kimyongj/algorithm
class Main{
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
	public static void main(String args[])throws Exception{
		StringBuilder sb = new StringBuilder();
		int N = read(); // 자리수
		int K = read(); // 지울 수
		int limit = N-K; // 최종 만들어야하는 길이
		int str[] = new int[N];
		
		for(int i=0; i<N; i++)
			str[i] = System.in.read()-'0';

		for(int i=0; i<N; i++) 
		{
			int len = sb.length() - 1;
			while(K > 0 && len>=0 && sb.charAt(len)-'0' < str[i]) 
			{
				sb.deleteCharAt(len--);
				--K;
			}
			sb.append(str[i]);
		}
		
		
		System.out.print(sb.toString().substring(0,limit));
	}
} 