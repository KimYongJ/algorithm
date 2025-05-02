//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13547
//2초 512MB

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

class Main{
	
    static class FastScanner {
        private final byte[] buf = new byte[1 << 16];
        private int idx, len;
        private final InputStream in;
        FastScanner(InputStream in) { this.in = in; }
        private int read() throws IOException {
            if (idx >= len) { len = in.read(buf); idx = 0; }
            return len == -1 ? -1 : buf[idx++];
        }
        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            while ((c = read()) <= 32 && c != -1);
            if (c == '-') { sign = -1; c = read(); }
            while (c > 32 && c != -1) { val = val * 10 + (c - '0'); c = read(); }
            return val * sign;
        }
    }
    
	static class Query implements Comparable<Query>{
		int left, right, idx, sqrt;
		Query(int l, int r, int i, int s){
			left = l;
			right= r;
			idx = i;
			sqrt = s;
		}
		@Override
		public int compareTo(Query o) {
			int l = left / sqrt;
			int r = o.left / sqrt;
			return l == r ? right - o.right : l - r;
		}
	}
	public static void main(String[] args)throws Exception{
		FastScanner fs = new FastScanner(System.in);
		int N		= fs.nextInt();	//수열의 크기 1<=100,000
		int sqrt	= (int) Math.sqrt(N);
		int arr[]	= new int[N + 1];
		int count[] = new int[1_000_001];
		
		for(int i=1; i<=N; i++)
			arr[i] = fs.nextInt();// 배열 원소 값 1<=1,000,000
		
		ArrayList<Query> query = new ArrayList<>();
		
		int Q		= fs.nextInt();// 쿼리 개수 1<=100,000
		int ans[]	= new int[Q + 1];
		
		for(int i=1; i<=Q; i++)
			query.add(new Query(fs.nextInt(), fs.nextInt(), i, sqrt));
		
		
		// left / log 한 값을 기준으로 오름차순, 같으면 right가 작은 값을 기준으로 오름차순
		Collections.sort(query);
		
		int idxL = 1;
		int idxR = 0;
		int cnt = 0;
		
		for(int i=0; i<query.size(); i++)
		{
			Query q = query.get(i);
			
			while(idxR < q.right)
				if(count[arr[++idxR]]++ == 0)
						cnt++;

			while(idxL < q.left)
				if(count[arr[idxL++]]-- == 1)
					cnt--;

			while(q.left < idxL)
				if(count[arr[--idxL]]++ == 0)
					cnt++;

			while(q.right < idxR)
				if(count[arr[idxR--]]-- == 1)
					cnt--;

			ans[q.idx] = cnt; 
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=Q; i++)
			sb.append(ans[i])
				.append('\n');
		
		System.out.print(sb);
	}
}
//5			// 수의 길이 1<=100,000
//1 1 2 1 3		// 배열 원소 값 1<=1,000,000
//3			// 쿼리 개수 1<=100,000
//1 5			// 배열의 위치 1<=N
//2 4
//3 5
