// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			int N = Integer.parseInt(br.readLine());
			int nums[] = new int[N];
			char bomb1[] = new char[N];
			char bomb2[] = new char[N];
			String str1 = br.readLine();
			String str2 = br.readLine();
			for(int i=0; i<N; i++) 
			{
				nums[i] = str1.charAt(i)-'0';
				bomb2[i] = bomb1[i] = str2.charAt(i);
			}
			boolean valid1 = true;
			boolean valid2 = true;
			
			for(int i=0; i<N; i++) {
				int bomCnt1 = 0; // 자기범위의 폭탄 개수
				int bomCnt2 = 0; // 자기범위의 폭탄 개수
				int s,e; // 범위
				if(i==0) {
					s=0;
					e=1;
				}else if(i==N-1) {
					s=N-2;
					e=N-1;
				}else {
					s=i-1;
					e=i+1;
				}
				for(int o=s; o<=e; o++) {
					if(bomb1[o] == '*') {
						bomCnt1++;
					}
					if(bomb2[o] == '*') {
						bomCnt2++;
					}
				}
				if(nums[i] < bomCnt1) {
					valid1 = false;
				}else {
					int ss = s;
					int n = nums[i] - bomCnt1;
					while(n>0 && ss<=e) {
						if(bomb1[ss] == '#') {
							n--;
							bomb1[ss] = '*';
						}
						ss++;
					}
				}
				if(nums[i] < bomCnt2) {
					valid2 = false;
				}else {
					int ee = e;
					int n = nums[i] - bomCnt2;
					while(n>0 && s<=ee) {
						if(bomb2[ee] == '#') {
							n--;
							bomb2[ee] = '*';
						}
						ee--;
					}
				}
				// 확실히 불가능한것 체크
				for(int o=s; o<=e; o++) {
					if(bomb1[o] == '#')
						bomb1[o] = 'x';
					if(bomb2[o] == '#')
						bomb2[o] = 'x';
				}
			}
			
			
			int cnt1 = 0;
			int cnt2 = 0;
			if(valid1) {
				for(char c : bomb1)
					if(c=='*')
						cnt1++;
			}
			if(valid2) {
				for(char c : bomb2)
					if(c=='*')
						cnt2++;
			}
			sb.append(Math.max(cnt1, cnt2)).append('\n');
		}
		System.out.print(sb.toString());
	}
}