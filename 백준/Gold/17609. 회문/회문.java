// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static int getCnt(String str, int left, int right) {
		int cnt = 0;
		while(left < right) {
			char cl = str.charAt(left);
			char cr = str.charAt(right);
			if(cl != cr) {
				cnt++;
				if(right-1 != left) {
					if(cl == str.charAt(right-1)) right--;
					else if(str.charAt(left+1) == cr) left++;
					else cnt++;
				}
			}
			left++;
			right--;
		}
		return cnt;
	}
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			String str = br.readLine();
			int len = str.length();
			int cnt1 = getCnt(str, 0, len-1);
			int cnt2 = 0;
			int left = 0;
			int right = len-1;
			while(left < right) {
				char cl = str.charAt(left);
				char cr = str.charAt(right);
				if(cl != cr) {
					cnt2++;
					if(right-1 != left) {
						if(str.charAt(left+1) == cr) left++;
						else if(cl == str.charAt(right-1)) right--;
						else cnt2++;
					}
				}
				left++;
				right--;
			}
			sb.append(Math.min(Math.min(cnt1, cnt2),2)).append('\n');
		}
		System.out.print(sb.toString());
	}
}