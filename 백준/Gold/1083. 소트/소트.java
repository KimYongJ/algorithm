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
		int N = read();
		for(int i=0; i<N; i++)
			list.add(read());
		int S = read();
		
		for(int i=0; i<N && S>0; i++) 
		{
			int maxNum = 0, maxIdx = 0;
			for(int j=i,s=S; j<N && s>=0; j++,s--) 
			{
				if(maxNum < list.get(j)) 
				{
					maxNum = list.get(j);
					maxIdx = j;
				}
			}
			S -= (maxIdx - i);
			list.remove(maxIdx);
			list.add(i,maxNum);
		}
		StringBuilder sb = new StringBuilder();
		for(int a : list) 
			sb.append(a).append(' ');
		System.out.print(sb.toString());
	}
}