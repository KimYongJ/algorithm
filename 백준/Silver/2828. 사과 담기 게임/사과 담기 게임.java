// https://github.com/kimyongj/algorithm
class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int N		= read();
		int end		= read();
		int T		= read();
		int start	= 1;
		int result	= 0;
		int move, num;
		while(T-- > 0) 
		{
			num	= read();
			if(num < start)
			{
				result	+= move = (start-num);
				start	-= move;
				end		-= move;
			}
			else if(end < num) 
			{
				result	+= move = num - end;
				start	+= move;
				end		+= move;
			}
		}
		System.out.print(result);
	}
}