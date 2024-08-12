// https://github.com/kimyongj/algorithm
import java.util.ArrayList;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		ArrayList<Integer> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		int N		= read();
		int arr[]	= new int[N];
		
		for(int i=0; i<N; i++) 
			arr[i] = read();

		for(int i=N; i>0; i--)
			list.add(arr[i-1], i);
		
		for(int r : list)
			sb.append(r).append(' ');
		
		System.out.print(sb.toString());
	}
}