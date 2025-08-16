import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());

		System.out.println(N *(N -1) *(N-2)/6);
		System.out.print(3);
	}
}