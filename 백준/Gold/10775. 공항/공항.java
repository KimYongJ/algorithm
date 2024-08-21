// https://github.com/kimyongj/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int find(int gate[], int x) 
	{
		if(gate[x] == x) return x;
		return gate[x] = find(gate, gate[x]);
	}
	public static void main(String[] args)throws Exception{
		int G		= read(); // 게이트 수 1<=105
		int P		= read(); // 비행기 수 1<=105
		int cnt		= 0;
		int gate[]	= new int[G+1];
		int x,y;
		
		for(int i=1; i<=G; i++) // 게이트 초기화
			gate[i] = i;
		
		for(int i=0; i<P && ((x = find(gate, read())) != 0); i++) 
		{
			y = find(gate,x-1);
			
			if(x > y) 
				gate[x] = y;
			
			cnt++;
		}
		System.out.print(cnt);
	}
}