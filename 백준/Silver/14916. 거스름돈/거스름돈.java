// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int DummyN = N;
		int five = 0;
		int two = 0;
		
		if(N % 2 == 0) {
			two = N/2;
		}
		
		for(int i=N/5,cnt = 1; i>0; i--,cnt++) {
			DummyN -= 5;
			if(DummyN % 2 == 0) {
				five = cnt;
				two = DummyN / 2;
			}
		}
		if(two == 0 && five == 0) {
			two = -1;
		}
		
		System.out.print(five + two);
	}
}