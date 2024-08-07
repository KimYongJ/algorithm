// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size1 = Integer.parseInt(br.readLine());
		int size2 = Integer.parseInt(br.readLine()) * 4;
		int size3 = Integer.parseInt(br.readLine());
		int size4 = Integer.parseInt(br.readLine());
		int size5 = Integer.parseInt(br.readLine());
		int res = Integer.parseInt(br.readLine()) + size5 + size4;
		size1 -= size5 * 11; // 5cm의 여백을 1에서 뺀다.
		size2 -= 20 * size4;// 4cm 종이의 여백을 구한다.

		res += Math.ceil(size3/4.0); // 3cm가 들어갈 곳 결과에 더함
		
		int remain = 4 - ((size3 % 4)); // 비어있는 3cm의 칸을 구함
		if(remain == 1) {
			size2 -= 4;
			size1 -= 5;
		}
		else if(remain == 2) {
			size2 -= 12;
			size1 -= 6;
		}
		else if(remain == 3) {
			size2 -= 20;
			size1 -= 7;
		}
			
		if(size2 < 0) {
			size1 += size2;
		}
		
		if(size2 > 0){
			res += Math.ceil(size2 / 36.0);
			if(size2 % 36 != 0) {
				size1 -= 36 - (size2 % 36);
			}
		}
		
		if(size1 > 0) {
			res += Math.ceil(size1 / 36.0);
		}
		
		System.out.print(res);
	}
}
