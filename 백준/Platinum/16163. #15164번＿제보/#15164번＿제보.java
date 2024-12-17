//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16163
class Main{
	
    public static String readString() throws Exception {
        StringBuilder sb = new StringBuilder(" ");
        int c;
        while ((c = System.in.read()) > 13) {sb.append((char) c).append(' ');}
        return sb.toString();
    }
    
	public static void main(String args[])throws Exception{
		
		char str[]	= readString().toCharArray();
		int len		= str.length;
		int A[]		= new int[len];
		long cnt	= 0;
		
		for(int i=0,r=0,p=0; i<len; i++)
		{
			
			if(i<=r)
				A[i] = Math.min(r-i, A[2*p-i]);
			
			while(0<=i-A[i]-1 && i+A[i]+1<len && str[i-A[i]-1] == str[i+A[i]+1])
				++A[i];
			
			if(r < i+A[i])
			{
				r = i + A[i];
				p = i;
			}

			cnt +=(A[i]+1)/2;
		}
		
		System.out.print(cnt);
	}
}