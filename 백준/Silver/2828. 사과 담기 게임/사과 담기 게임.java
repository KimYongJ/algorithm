// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static int getNext(int b, int s, int e) {
		if(s <= b && b<=e) {return 0;}
		else if(b<s) {return -(s-b);}
		else {return b-e;}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());
		int end		= Integer.parseInt(st.nextToken());
		int start	= 1;
		int T		= Integer.parseInt(br.readLine());
		int result	= 0;
		while(T-- > 0) 
		{
			int num		= Integer.parseInt(br.readLine());
			int move	= getNext(num, start, end);
			result		+= Math.abs(move);
			start		+= move;
			end			+= move;
		}
		System.out.print(result);
	}
}