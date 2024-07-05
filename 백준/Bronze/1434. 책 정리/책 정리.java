// https://github.com/kimyongj/algorithm
class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int result	= 0;
		int N		= read();
		int M		= read();
		int box[]	= new int[N];
		int book[]	= new int[M];
		
		for(int i=0; i<N; i++) {box[i]	= read();}
		for(int i=0; i<M; i++) {book[i] = read();}
		
		int boxIdx = 0;			// 박스 인덱스
		for(int i=0; i<M; i++)	// 책인덱스
		{
			if(box[boxIdx] - book[i] < 0) // 박스에 책을 못 넣는다면 책인덱스 +1 을 하고 낭비된 공간 더하기.
			{
				result += box[boxIdx++];
			}
			else {
				box[boxIdx] -= book[i];
				book[i] = 0;
			}
			if(book[i] != 0)	// 책을 넣지 못하였다면 책 인덱스를 -1을 하여 원래 인덱스로 돌림(추후 +1을하니까)
			{
				i--;
			}
		}
		for(int i=boxIdx; i<N; i++) 
		{
			result += box[i]; // 남은 인덱스 더하기 
		}
		System.out.print(result);
	}
}