//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19532
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a,b,c,d,e,f,x,y;
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		f = Integer.parseInt(st.nextToken());
		
		x = -1000;
		while(++x < 1000) {
			y = -1000;
			while(++y < 1000){
				if((a*x + b*y == c) &&( d*x + e*y == f)) {
					sb.append(x).append(' ').append(y);
					break;
				}
			}
		}
		System.out.print(sb.toString());
	}
}