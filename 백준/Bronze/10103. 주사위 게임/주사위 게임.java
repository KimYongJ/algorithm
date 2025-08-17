
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int A = 100;
		int B = 100;
		
		while(n-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a < b)
				A -= b;
			else if(b < a)
				B -= a;
		}
		
		System.out.println(new StringBuilder().append(A)
				.append('\n').append(B).toString());
	}
}