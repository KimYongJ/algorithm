// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static long num, left, right, mid;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Long.parseLong(br.readLine());
		right = num;
		while(left < right) {
			mid = (left+right) / 2;
			if(Math.pow(mid, 2) >= num) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		System.out.println(left);
	}
}