//https://www.acmicpc.net/problem/10431
//4 // 테스트 케이스 수(1<=1000)
//1 900 901 902 903 904 905 906 907 908 909 910 911 912 913 914 915 916 917 918 919 // 테케번호 T와 20개의 정수가 공백으로 주어짐
//2 919 918 917 916 915 914 913 912 911 910 909 908 907 906 905 904 903 902 901 900
//3 901 902 903 904 905 906 907 908 909 910 911 912 913 914 915 916 917 918 919 900
//4 918 917 916 915 914 913 912 911 910 909 908 907 906 905 904 903 902 901 900 919
//테스트케이스 번호와 학생들이 뒤로 물러난 걸음의 수의 총합을 공백으로 구분해 출력합니다.
//1 0
//2 190
//3 19
//4 171
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		List<Integer> list = new ArrayList<>();
		int T = Integer.parseInt(br.readLine());
		int cnt = 0;
		
		while(T-->0)
		{
			list.clear();
			cnt = 0;
			st = new StringTokenizer(br.readLine());
			sb.append(st.nextToken()).append(' ');
			
			for(int i=0; i<20; i++)
			{
				int now = Integer.parseInt(st.nextToken());
				if(list.size() > 0)
				{
					int idx = binarySearch(list, now);
					if(idx >= 0)
					{
						cnt += list.size() - idx;
						list.add(idx, now);
						continue;
					}
				}
				list.add(now);
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
	static public int binarySearch(List<Integer> list, int base) {
		int s = 0;
		int e = list.size() - 1;
		int res = -1;
		while(s<=e) {
			int mid = (s + e) >> 1;
			int target = list.get(mid);
			if(base > target) {
				s = mid + 1;
			}
			else
			{
				e = mid - 1;
				res = mid;
			}
		
		}
		return res;
	}
}