// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static boolean findpal(String str, int left, int right) {
		while(left < right)
			if(str.charAt(left++) != str.charAt(right--))
				return false;			
		return true;
	}
	public static int find(String str, int left, int right) {
		while(left < right) {
			if(str.charAt(left) != str.charAt(right)) {
				if(findpal(str,left, right -1) || findpal(str,left+1, right)) {
					return 1;
				}else
					return 2;				
			}
			left++;
			right--;
		}
		return 0;
	}
	public static void main(String args[])throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder 	sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			String str = br.readLine();
			sb.append(find(str, 0, str.length()-1)).append('\n');
		}
		System.out.print(sb.toString());
	}
}