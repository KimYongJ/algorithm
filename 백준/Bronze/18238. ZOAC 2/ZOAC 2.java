//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18238
//1초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int res = 0;
		int prv = 0;
		
		for(char c : str.toCharArray()) {
			int now = c - 'A';
			
			int max = Math.max(now, prv);
			int min = Math.min(now, prv);
			
			int diff = max - min;
			
			if(diff <= 13)
				res += diff;
			else
			{
				res += min +  26 - max;
			}
			
			prv = now;
		}
		System.out.print(res);
	}
}