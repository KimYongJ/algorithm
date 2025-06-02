//https://www.acmicpc.net/problem/12843
//5초 512MB
//5 4// 강의 개수(1 ≤ n ≤ 2,000), 수업 간 관계의 개수 (1 ≤ m ≤ 1,000,000) 
//1 c// n개 줄에 대해 강의의 번호와 어느 학부의 강의인지 입력됨(c=컴퓨터학부, s=소프트웨어 학부)
//2 s
//3 c
//4 s
//5 c
//1 2// m개 줄에 겹치는 내용이 존재한 관계 번호들이 주어지며 동일한 두 강의의 관계가 중복해서 나오지 않음
//2 3
//3 4
//4 5
// 답 : 3

import java.util.ArrayList;
import java.util.List;

class Main{
	
	static int N, M;
	static boolean isComputer[];
	static List<Integer> adList[];
	
	// 이분 매칭시 사용
	static int time;
	static int match[];
	static int visitTime[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		adList = new ArrayList[N + 1];
		isComputer = new boolean[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			adList[i] = new ArrayList<>(); // 인접리스트 초기화
			
			int node = in.nextInt();// 노드번호
			char c = in.nextChar();// 과목
			isComputer[node] = c == 'c';
		}
		
		for(int i=1;i<=M; i++)
		{
			int a = in.nextInt();
			int b = in.nextInt();
			// 컴퓨터 과목기준으로 인접 리스트 생성
			if(isComputer[a])
				adList[a].add(b);
			else
				adList[b].add(a);
		}
		
		match = new int[N + 1];
		visitTime = new int[N + 1];
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			++time;
			if(dfs(i))
				++cnt;
		}
		System.out.print(N - cnt);
	}
	static boolean dfs(int now)
	{
		for(int next : adList[now])
		{
			if(visitTime[next] == time)
				continue;
			
			visitTime[next] = time;
			
			if(match[next] == 0 || dfs(match[next]))
			{
				match[next] = now;
				return true;
			}
		}
		return false;
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
	    char nextChar() throws Exception {
	        byte c;
	        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
	        return (char)c;
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