// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int day, withCnt, money[], ans, left, right, mid;
	public static boolean check(int mid) {
		int cnt = 0, nowMoney = 0;
		for(int m : money) {
			if(m > nowMoney) {
				cnt++;
				nowMoney = mid - m;
				if(nowMoney < 0) 	// 돈을 새로 출금해도 부족할 때 false리턴
					return false;
			}else {
				nowMoney -= m;
			}
			if(cnt > withCnt)
				return false;
		}
		return true;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		day 		= Integer.parseInt(st.nextToken());
		withCnt 	= Integer.parseInt(st.nextToken());
		money 		= new int[day];
		for(int i=0; i<day; i++) {
			money[i] = Integer.parseInt(br.readLine());
			right 	 += money[i];
		}
		
		left = 1;
		while(left <= right) {
			mid = (left + right) / 2;
			if(check(mid)) {
				ans = mid;
				right = mid - 1;
			}else {
				left = mid + 1;
			}
		}
		System.out.println(ans);
	}
}
