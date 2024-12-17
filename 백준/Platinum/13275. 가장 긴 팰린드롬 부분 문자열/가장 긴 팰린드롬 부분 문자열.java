//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13275
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		for(char c : readString().toCharArray())
			sb.append(' ').append(c);
		sb.append(' ');
		
		System.out.print(manacher(sb.toString().toCharArray(), sb.length()));
	}
	public static int manacher(char[] str, int len) {
		int A[] = new int[len];
		int max	= 0;
		for(int i=0,r=0,p=0; i<len; i++)
		{
			if(i<=r)
				A[i] = Math.min(r-i, A[2*p - i]);
			
			while(0<=i-A[i]-1 && i+A[i]+1<len && str[i-A[i]-1] == str[i+A[i]+1])
				++A[i];
			
			if(r < i+A[i])
			{
				r = i+A[i];
				p = i;
			}
			max = Math.max(max, A[i]);
		}
		return max;
	}
    public static String readString() throws Exception {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = System.in.read()) > 13) {sb.append((char) c);}
        return sb.toString();
    }
}