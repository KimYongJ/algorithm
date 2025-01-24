//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/22988
//1초 / 1024mb
import java.util.ArrayList;
import java.util.Collections;
class Main{
	public static void main(String[] args)throws Exception{
		long N	= read();	// 용기 수 1<=십만
		long X	= read();	// 10의18승
		long P	= (X + 1) / 2;
		int res = 0;
		
		ArrayList<Long> list = new ArrayList<>();
		while(N-->0)
		{
			long num = read();
			if(num == X)
				++res;
			else
				list.add(num);
		}
		
		Collections.sort(list);
		
		int remain = 0;// 그리디하게 못만드는 값들
		int s = 0;
		int e = list.size()-1;
		while(s<=e)
		{
			long sum = list.get(s) + list.get(e);
			if(s<e && P <= sum)
			{
				++res;
				++s;
				--e;
			}
			else
			{
				++s;
				++remain;
			}
		}
		System.out.print(res + remain / 3);
	}
	static long read() throws Exception {
		long c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}