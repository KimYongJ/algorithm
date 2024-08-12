// https://github.com/kimyongj/algorithm
class Main{
    private static int read() throws Exception{
        int val = 0,c = System.in.read();
        while (c <= ' ') {c = System.in.read();        }
        do {val = 10 * val + c - 48;} 
        while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
	public static void main(String[] args)throws Exception{
		int N	= read();
		int max = 0;
		int day = 0;
		int counting[] = new int[1000001];
		
		for(int i=0; i<N; i++)
			counting[read()]++;
		
		for(int i=1000000; i>=1; i--) 
			if(counting[i] > 0) 
				max = Math.max(max, (day+=counting[i])+i);
		
		System.out.print(max + 1); // 이장 초대는 하루 뒤이므로
	}
}