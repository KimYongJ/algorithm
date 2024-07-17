// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long max = 0;
		long sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			long num = Long.parseLong(st.nextToken());
			sum += num;
			if(max < num) {
				max = num;
			}
		}
		double result = (double)max + (sum - max)/2.0; 
		System.out.print(result);
	}
}