//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13546
//4초 512MB
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

class Main{
	
	static int sqrt;
	static int N, K, Q;
	static int arr[];					// 초기 입력될 원소들
	static int ans[];					// 각 쿼리당 정답을 담을 배열
	static int fac[];					// 같은 원소들의 차이에 대한 구간을 마킹할 배열
	static int count[];					// 같은 원소들의 차이에 대한 카운팅 배열
	static ArrayList<Query> query;
	static ArrayDeque<Integer>[] idxList;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N		= in.nextInt();//배열수 (1 ≤ N ≤ 100,000)
		K		= in.nextInt();//등장 수 중 최댓값 (1 ≤ K ≤ 100,000)
		sqrt	= (int)Math.sqrt(N);
		fac		= new int[(N / sqrt) + 1];
		arr		= new int[N + 1];
		count	= new int[N + 1];	// 같은 원소들의 인덱스 차이에 대해서 놓아야 하기 때문에 N + 1
		query	= new ArrayList<>();
		idxList	= new ArrayDeque[K + 1];
		
		for(int i=0; i<=K; i++)
			idxList[i] = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++)
			arr[i] = in.nextInt();
		
		Q = in.nextInt();
		ans = new int[Q + 1];
		
		for(int i=1; i<=Q; i++)
		{
			int l = in.nextInt();
			int r = in.nextInt();
			query.add(new Query(l, r, i , l / sqrt));
		}
		
		Collections.sort(query);
		
		int L = 1;
		int R = 0;
		for(Query q : query)
		{
			while(q.left < L) {
				--L;
				
				int now = arr[L];
				
				cal(now, -1);
				
				idxList[now].addFirst(L);
				
				cal(now, 1);
			}

			while(R < q.right) {
				++R;
				
				int now = arr[R];
				
				cal(now, -1);
				
				idxList[now].addLast(R);
				
				cal(now, 1);
			}
			while(L < q.left) {
				
				int now = arr[L];

				cal(now, -1);
				
				idxList[now].removeFirst();
				
				cal(now, 1);
				
				++L;
			}
			while(q.right < R) {
				int now = arr[R];

				cal(now, -1);
				
				idxList[now].removeLast();
				
				cal(now, 1);
				
				--R;
			}
			
			ans[q.idx] = find(); 
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static void cal(int now, int plus) {
	    if(idxList[now].size() > 0)
	    {
	        int diff = idxList[now].peekLast() - idxList[now].peekFirst();
	        count[diff] += plus;
	        fac[diff / sqrt] += plus;
	    }
	}
	static int find() {
		for(int i=N / sqrt; i>=0; i--)
		{
			if(fac[i] != 0)
			{
				int s = i * sqrt;
				int e = Math.min(N,s + sqrt - 1);
				
				while(e >= s)
				{
					if(count[e] > 0)
						return e;

					--e;
				}
			}
		}
		return 0;
	}
	static class Query implements Comparable<Query>{
		int left, right, idx, fac;
		Query(int l, int r, int i, int f){
			left = l;
			right = r;
			idx = i;
			fac = f;
		}
		@Override
		public int compareTo(Query o) {
			if(fac != o.fac)
				return fac - o.fac;
			
			if((fac & 1) == 0)
				return right - o.right;
			
			return o.right - right;
		}
	}
}
//7 7
//4 5 6 6 5 7 4
//5
//6 6
//5 6
//3 5
//3 7
//1 7
//답,구간의 같은 숫자가 나올 경우 그 같은 숫자의 인덱스 차이 중 최댓값 출력
//0
//0
//1
//1
//6


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

