// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
class Main{
	
	static final	String inf = "Hello, BOJ 2023!";
	static int[]	num;	// 처음 입력받은 문자열을 숫자로 변환한 것
	static int		len;
	static HashSet<Integer> set;
	public static int getNum(int m) {
		int sum = 0;
		for(int n : num) {
			if(n != 0)
				sum += Math.pow(n,m);
		}
		return sum;
	}

	public static void backtracking(int depth,int sum, int start) {
		if(depth == len) 
		{
			set.add(sum + start);
			return;
		}
		backtracking(depth + 1,sum, start * 10 + num[depth]);	// 그냥 플러스넣지 않고 문자를 그냥 하나로 합침
		backtracking(depth + 1,sum + start, num[depth]);		// 플러스를 넣음 
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			boolean flag = true;
			String str = br.readLine();
			len = str.length();
			num = new int[len];
			for(int i=0; i<len; i++)
			{
				num[i] = str.charAt(i)-'0';
				if(num[i] != 0 && num[i] != 1) 
				{
					flag = false;
				}
			}
			
			if(flag) 
			{
				sb.append(inf);
			}
			else
			{
				set = new HashSet<>();		// 플러스를 사용해 만들 수 있는 숫자의 모음
				backtracking(0,0,0);
				
				int cnt = 0;
				for(int i=1; i<=30; i++) 
				{
					int num = getNum(i);	// 만들 수 있는 숫자
					if(num > 1000000000) 
					{
						break;
					}
 					if(set.remove(num))		
					{
						cnt++;
					}
				}
				sb.append(cnt);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}