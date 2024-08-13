// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		char flag = str.charAt(0);
		int left = 0;
		int right = N-1;
		int lcntB = 0; // 왼쪽 정렬되지 않은 B
		int lcntR = 0; // 왼쪽 정렬되지 않은 R
		int rcntB = 0; // 오른쪽 정렬되지 않은 B
		int rcntR = 0; // 오른쪽 정렬되지 않은 R
		int cntB  = 0; // 최종 정렬되지 않은 것
		int cntR  = 0; // 최종 정렬되지 않은 것
		for(;left<N; left++) {
			if(str.charAt(left)!=flag) {
				break;
			}else if(flag == 'R')lcntR++;
			else lcntB++;
		}
		
		flag = str.charAt(N-1);
		for(;right>=0; right--) {
			if(str.charAt(right)!=flag) {
				break;
			}else if(flag == 'R')rcntR++;
			else rcntB++;
		}
		
		if(str.charAt(0) == str.charAt(N-1)) // 양 끝 쪽이 같다면 
		{
			if(str.charAt(0) == 'R') {
				cntR = Math.min(lcntR, rcntR);
			}else {
				cntB = Math.min(lcntB, rcntB);
			}
		}
		
		while(left<=right) {
			if(str.charAt(left) == 'R') cntR++;
			else cntB++;
			left++;
		}
		
		System.out.print(Math.min(cntR, cntB));
	}
}