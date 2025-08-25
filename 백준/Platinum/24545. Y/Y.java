//https://www.acmicpc.net/problem/24545
//1.5초 1024MB

import java.util.ArrayList;
import java.util.List;

class Main{
	
	static int N;
	static int idx;
	static int maxDepth;
	
	static int max[];
	static boolean visit[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		
		N = in.nextInt();
		max = new int[2];
		visit = new boolean[N + 1];
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++)
		{
			int a = in.nextInt();
			int b = in.nextInt();
			adList[a].add(b);
			adList[b].add(a);
		}
		
		dfs1(1, -1, 1);// 트리 지름의 양끝 노드를 구하기 위한 첫번 째 dfs
		
		idx = 1;
		maxDepth = 0;
		dfs1(max[0], -1, 1);// 트리 지름의 양끝 노드를 구하기 위한 두번 째 dfs
		
		visit[max[0]] = true;
		mark(max[0], 1);// 트리 지름을 탐색하며 visit에 마킹
		
		int max = 0;
		
		for(int i=1; i<=N; i++)// 모든 노드를 순회
		{
			// 지름에 포함된 노드가 아니거나, 지름에 포함된 노드이지만 인접 노드가 2개 이하인 경우 연산 스킵
			if(!visit[i] || adList[i].size() < 3)
				continue;
			
			for(int next : adList[i])
				if(!visit[next])// 인접 노드를 순회하며 지름이 아닌 노드 발견시 거기서 부터 길이를 구함
					max = Math.max(max, dfs2(next));
		}
		
		System.out.print(max != 0 ? maxDepth + max : 0);
	}
	static int dfs2(int now) {
		visit[now] = true;
		
		int maxSize = 0;
		
		for(int next : adList[now])
		{
			if(visit[next])
				continue;

			int childSize = dfs2(next);// 자식노드의 길이를 가져옴
			// 자식 노드들 중 가장 긴 길이를 저장
			maxSize = Math.max(maxSize, childSize);
		}
		// 자식 노드들 중 가장 긴 길이에 자기 자신을 더해 반환
		return maxSize + 1;
	}
	static boolean mark(int now, int depth) {
		
		if(max[1] == now)// 목표에 도달시 true 리턴
			return true;
		
		for(int next : adList[now])
		{
			if(!visit[next])
			{
				visit[next] = true;
				
				if(mark(next, depth + 1))
					return true;
				
				visit[next] = false;
			}
		}
		return false;
	}
	static void dfs1(int now, int prev, int depth) {
		if(maxDepth < depth)
		{
			maxDepth = depth;
			max[idx] = now;
		}
		for(int next : adList[now])
			if(next != prev)
				dfs1(next, now, depth + 1);
	}
}

class Reader {
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
