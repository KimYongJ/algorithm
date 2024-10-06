//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/20191
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
class Main{
	public static int binarySearch(ArrayList<Integer> list, int targetIdx) {
		int s	= 0;
		int e	= list.size() - 1;
		int res	= -1;
		
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(list.get(mid) <= targetIdx)
				s = mid + 1;
			else
			{
				res = mid;
				e = mid - 1;
			}
		}
		
		if(res != -1)
			return list.get(res) == targetIdx ? -1 : list.get(res);

		return -1;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();		// 기본 문자열
		String t = br.readLine();		// 반복 추가할 문자열/ s를 포함하도록 t를 일정하게 늘린다.

		// t의 알파벳당 인덱스를 저장할 리스트 배열
		ArrayList<Integer> list[] = new ArrayList[26];
		for(int i=0; i<26; i++)
			list[i] = new ArrayList<>();
		// t의 알파벳당 인덱스를 저장한다.
		for(int i=0; i<t.length(); i++)
			list[t.charAt(i) - 'a'].add(i);
		
		int res		= 1;	// 반복횟수, 처음 한번은 무조건 반복하기에 1 기본값
		int nowIdx	= -1;	// 현재 패턴에 탐색중인 list의 인덱스
		
		for(char cur : s.toCharArray())
		{
			// 찾으려는 것이 t에 없으면 종료
			if(list[cur - 'a'].size() == 0)
			{
				System.out.print(-1);
				return;
			}
			
			// list에서 찾으려는 cur문자의 인덱스를 가져옴, list['a']에서 인덱스 1,3,6 저장시 listIdx값이 2라면, 3을 반환
			nowIdx = binarySearch(list[cur-'a'], nowIdx);
			if(nowIdx < 0)	// 해당 알파뱃이 
			{
				res ++;
				nowIdx = list[cur - 'a'].get(0);
			}
		}
		
		System.out.print(res);
	}
}