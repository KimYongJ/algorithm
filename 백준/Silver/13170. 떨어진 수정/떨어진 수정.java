// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
		st.nextToken();
		double P = Double.parseDouble(st.nextToken());
		double W = Double.parseDouble(st.nextToken());
		System.out.print((int)(Math.ceil(P / W)));
	}
}