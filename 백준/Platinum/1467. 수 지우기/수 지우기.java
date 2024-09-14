//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1467
import java.util.ArrayList;
class Main{

	public static void main(String[] args)throws Exception{
		ArrayList<Integer> nlist = new ArrayList<>();//기본숫자 담을 배열
		ArrayList<Integer> result= new ArrayList<>();//결과를 담을 배열
		int Ncnt[] = new int[10]; // 기본숫자 카운팅 정렬
		int Dcnt[] = new int[10]; // 지울숫자 카운팅 정렬
		char c;
		
		while((c=(char)System.in.read()) != '\n')
		{
			int n = c-'0';
			nlist.add(n);
			Ncnt[n]++;// 기본 숫자 카운팅 정렬
		}
        
		while((c=(char)System.in.read()) != '\n')
			Dcnt[c-'0']++;// 지울숫자 카운팅 정렬
		
		for(int now : nlist) 
		{
			// 지울 숫자가 0개 이상이고, 기본 숫자와 지울 숫자의 개수가 같을 때
			if(Dcnt[now] > 0 && Ncnt[now] == Dcnt[now])
            {
				Ncnt[now]--;
				Dcnt[now]--;
			}
            else
            {
				while(!result.isEmpty())// 결과 배열이 빌 때 까지 반복
				{
					// 마지막 인덱스를 꺼내옴
					int last = result.get(result.size() - 1);
					// 확인하려는 값보다 리스트의 마지막 값이 크거나 같다면, 혹은 
					// 마지막 값이 지울것이 없다면 break
					if(last >= now || Dcnt[last] == 0) {
						break;
					}
                    else {
						// 리스트의 마지막에 담긴 값이 확인하려는 값보다 작은데, 지울 숫자라면 지움
						Dcnt[last]--;
						result.remove(result.size()-1);
					}
				}

				result.add(now);
				Ncnt[now]--;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int r : result)sb.append(r);
		System.out.print(sb.toString());
	}
}