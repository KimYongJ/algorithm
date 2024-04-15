// https://github.com/kimyongj/algorithm

class Main{
	
	static int day, withCnt, money[], ans, left, right, mid;
    static int read() throws Exception {// 빠른 입력을 위한 함수
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static boolean check(int mid) {
		int cnt = 0, nowMoney = 0;
		for(int m : money) {
			if(m > nowMoney) 
			{
				cnt++;
				nowMoney = mid - m;
				if(nowMoney < 0) 	// 돈을 새로 출금해도 부족할 때 false리턴
					return false;
			}else
				nowMoney -= m;
		}
		return cnt <= withCnt;
	}
	public static void main(String[] args)throws Exception{
		day 		= read();
		withCnt 	= read();
		money 		= new int[day];
		for(int i=0; i<day; i++) {
			money[i] = read();
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
