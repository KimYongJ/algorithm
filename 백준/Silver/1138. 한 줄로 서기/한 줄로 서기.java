// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N			= read();
		int result[]	= new int[N];
		for(int i=1; i<=N; i++) 
		{
			int num = read()+1;
			int idx = 0;
			while(true) 
			{
				if(result[idx] == 0 && --num == 0) {
					result[idx] = i;
					break;
				}
				idx++;
			}
		}
		
		for(int r : result)
			sb.append(r).append(' ');
		System.out.print(sb.toString());
	}
}