//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5875
class Main{
	public static void main(String[] args)throws Exception{
		int cnt		= 0;
		int left	= 0;
		int right	= 0;
		int ans		= 0;
		int c;
		while((c=System.in.read()) >= 13)
		{
			if(c == '(')
			{
				++cnt;
				++right;
			}
			else
			{
				--cnt;
				++left;
			}
			if(cnt == -1)		// ')'가 더 많이 나오면, 현재까지 ')'의 개수가 오타 후보이다.
			{
				ans = left;
				break;
			}
			if(cnt == 1)
				right = 0;
			
			ans = right;
		}
		System.out.print(ans);
	}
}