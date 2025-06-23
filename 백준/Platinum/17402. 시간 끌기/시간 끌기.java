//https://www.acmicpc.net/problem/17402
//1초 512MB
//서 입력되는 경우는 없다.첫 번째 줄에 철수와 영희가 합쳐서 총 몇 번의 선택을 하게 되는지 출력한다.
//4 4 6// N(1<=200), M(1<=200), X 표시된 칸의 수 K(1<=N*M)
//1 1//K줄에 걸쳐 두 자연수 X가 있는 좌표 X(1 ≤ N),Y(1 ≤ M)가 주어짐( 좌표 중복은 없음 )
//1 3
//2 4
//3 2
//3 4
//4 3
////총 몇번의 선택이 가능한지 : 4

import java.util.ArrayList;
import java.util.List;

class Main{
	
	static int N, M, K;
	static List<Integer> adList[];
	
	static int time;
	static int match[];
	static int visitTime[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();// N(1<=200)
		M = in.nextInt();// M(1<=200)
		K = in.nextInt();// X 표시된 칸의 수 K(1<=N*M)
		adList = new ArrayList[N + 1];
		match = new int[M + 1];
		visitTime = new int[M + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=0; i<K; i++)
			adList[in.nextInt()].add(in.nextInt());
		
		int cnt = 0;
		
		for(int x=1; x<=N; x++)
		{
			++time;
			if(dfs(x))
				++cnt;
		}
		System.out.print(N + M - cnt);
	}
	static boolean dfs(int x)
	{
		for(int y : adList[x])
		{
			if(visitTime[y] == time)
				continue;
			
			visitTime[y] = time;
			
			if(match[y] == 0 || dfs(match[y]))
			{
				match[y] = x;
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