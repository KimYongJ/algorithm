//https://www.acmicpc.net/problem/3295
//1초 128MB
//3 // 테스트 케이스
//4 3// 노드 수(0<=1,000 노드 번호는0부터시작), 단방향 링크 수(0<=50,000)
//3 2// 당방향 링크 수만큼 u->v로 향하는 u,v가 주어짐
//1 0
//2 3
//6 6
//0 1
//1 2
//2 3
//3 1
//3 4
//4 5
//14 19
//0 1
//1 2
//2 3
//3 4
//4 5
//5 0
//5 4
//2 1
//2 6
//6 7
//7 8
//8 9
//9 1
//8 7
//7 10
//10 11
//11 12
//12 13
//13 8
////링 또는 선형 배열로 분해했을 때 얻을 수 있는 가치의 최댓값 출력
//3
//5
//13

import java.util.ArrayList;
import java.util.List;

class Main{

	static final int LEN = 1_001;
	static int N, M;
	static int time;
	static int match[];
	static int visitTime[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		Reader in = new Reader();
		init();
		
		int T = in.nextInt();
		while(T-->0)
		{
			N = in.nextInt();// 노드 수(0<=1,000 노드 번호는0부터시작)
			M = in.nextInt();// 단방향 링크 수(0<=50,000)
			clear();
			for(int i=0; i<M; i++)
			{
				int u = in.nextInt() + 1;
				int v = in.nextInt() + 1;
				adList[u].add(v);
			}
			
			int cnt = 0;
			
			for(int i = 1; i<=N; i++) {
				++time;
				if(dfs(i))
					++cnt;
			}
			
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
	static boolean dfs(int now) {
		for(int next : adList[now]) {
			if(visitTime[next] == time)
				continue;
			
			visitTime[next] = time;
			
			if(match[next] == 0 || dfs(match[next])) {
				match[next] = now;
				return true;
			}
		}
		return false;
	}
	static void clear() {
		for(int i=0; i<=N; i++)
		{
			match[i] = 0;
			adList[i].clear();
		}
	}
	static void init() {
		match = new int[LEN + 2];
		visitTime = new int[LEN + 1];
		adList = new ArrayList[LEN + 1];
		for(int i=0; i<=LEN; i++)
			adList[i] = new ArrayList<>();
	}
	static class Reader {
	    final int SIZE = 1 << 13;
	    byte[] buffer = new byte[SIZE];
	    int index, size;
	    int nextInt() throws Exception {
	        int n = 0;
	        byte c;
	        boolean isMinus = false;
	        while ((c = read()) <= 32) { if (size < 0) return -1; }
	        if (c == 45) { c = read(); isMinus = true; }
	        do n = (n << 3) + (n << 1) + (c & 15);
	        while (isNumber(c = read()));
	        return isMinus ? ~n + 1 : n;
	    }
	    boolean isNumber(byte c) {
	        return 47 < c && c < 58;
	    }
	    byte read() throws Exception {
	        if (index == size) {
	            size = System.in.read(buffer, index = 0, SIZE);
	            if (size < 0) buffer[0] = -1;
	        }
	        return buffer[index++];
	    }
	}
}