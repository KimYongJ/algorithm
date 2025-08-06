//https://www.acmicpc.net/problem/3865
//1초 128MB
//2 // 학회의 수 N(1<=100)
//development:alice,bob,design,eve. // 콜론(:)앞은 학회 이름, 뒤쪽 회원의 이름은 콤마(,)로 구분되며 마지막에는 마침표(.)가 하나 주어진다.
//design:carol,alice.
//3
//one:another.
//another:yetanother.
//yetanother:dave.
//3
//friends:alice,bob,bestfriends,carol,fran,badcompany.
//bestfriends:eve,alice.
//badcompany:dave,carol.
//5
//a:b,c,d,e.
//b:c,d,e,f.
//c:d,e,f,g.
//d:e,f,g,h.
//e:f,g,h,i.
//4
//aa:bb.
//cc:dd,ee.
//ff:gg.
//bb:cc.
//0 // 마지막 입력에는 0이 주어짐
//이하 정답
//4
//1
//6
//4
//2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Main{

	static final int len = 100 * 100;
	static int N, idx;
	static boolean isIns[];// 학회인지 아닌지 체크
	static List<Integer> adList[];// 인접노드
	static HashMap<String, Integer> id;// 문자열에 대응되는 인덱스
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		id = new HashMap<>();
		isIns = new boolean[len + 1];
		adList = new ArrayList[len + 1];
		
		for(int i=0; i<=len; i++)
			adList[i] = new ArrayList<>();
		
		while((N = Integer.parseInt(br.readLine())) != 0)
		{
			idx = 0;
			id.clear();
			for(int i=0; i<=len; i++)
			{
				adList[i].clear();
				isIns[i] = false;
			}
			
			int start = 0;// 맨처음 학회 번호 반환
			
			for(int i=0; i<N; i++)
			{
				String f[] = br.readLine().split(":");
				
				f[1] = f[1].substring(0, f[1].length()-1);// 마지막 점(.) 제거
				
				if(!id.containsKey(f[0])) {
					id.put(f[0], ++idx);
				}
				
				int insIdx = id.get(f[0]); // 학회의 인덱스
				
				if(start == 0){// 맨처음 학회 번호 세팅
					start = insIdx;
				}
				
				isIns[insIdx] = true; // 학회인 것을 표시 
				
				for(String mem : f[1].split(","))
				{
					if(!id.containsKey(mem))
					{
						id.put(mem, ++idx);
					}
					
					adList[insIdx].add(id.get(mem));
				}
			}
			
			sb.append( dfs(start, new boolean[idx + 1]) )
				.append('\n');
			
		}
		System.out.print(sb);
	}
	static int dfs(int now, boolean visit[]) {
		
		int cnt = 0;
		
		for(int next : adList[now])
		{
			if(!visit[next])
			{
				visit[next] = true;
				
				if(!isIns[next])
					++cnt;
				
				cnt += dfs(next, visit);
			}
		}
		
		return cnt;
	}
}