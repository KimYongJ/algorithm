// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
class Main{
	
	static final	String inf = "Hello, BOJ 2023!";
	static int[]	num;	// 처음 입력받은 문자열을 숫자로 변환한 것
	static int[] 	plus;	// 플러스 위치를 담을 배열, 1은 +, 0은 아무것도 아님
	static int		len;
	static int		plen;
	static HashSet<Integer> set;
	public static long getNum(int m) {
		long sum = 0;
		for(int n : num) {
			if(n != 0)
				sum += Math.pow(n,m);
		}
		return sum;
	}
	public static void insert() {
		int start = num[0];
		int result = 0;
		for(int i=0; i<plen; i++) {
			if(plus[i] == 1) {
				result += start;
				start = num[i+1];
			}else {
				start = (start * 10) + num[i+1]; 
			}
		}
		set.add(result + start);
	}
	public static void backtracking(int depth) {
		insert();
		if(depth == len-1) return;
		backtracking(depth + 1);
		plus[depth] = 1;
		backtracking(depth + 1);
		plus[depth] = 0;
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
				plen= len -1;
				plus = new int[plen];
				
				backtracking(0);
				
				int cnt = 0;
				for(int i=1; i<=30; i++) 
				{
					long num = getNum(i);	// 만들 수 있는 숫자
					if(num > 1000000000) 
					{
						break;
					}
 					if(set.remove((int)num))		
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