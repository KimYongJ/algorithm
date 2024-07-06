// https://github.com/kimyongj/algorithm
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int inc		= read();
		int work	= read();
		int dec		= read();
		int MAX		= read();
		int energy	= 0;
		int result	= 0;
		int time	= 0;
		while(++time <= 24) 
		{
			if(energy + inc <= MAX) 
			{
				energy += inc;
				result += work;
			}
			else 
			{
				energy = Math.max(0, energy - dec);
			}
		}
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
	}
}