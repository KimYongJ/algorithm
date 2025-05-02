//https://github.com/KimyongJ/algorithm
//https://www.acmicpc.net/problem/13548
//2초 512MB
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
class Main{
	
	static int sqrt;
	
	static class Query implements Comparable<Query>{
		int left, right, idx;
		Query(int l, int r, int i){
			left = l;
			right=r;
			idx = i;
		}
		@Override
		public int compareTo(Query o) {
			int l = left / sqrt;
			int r = o.left / sqrt;
			return l == r ? right - o.right : l - r;
		}
	}
	
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
    
	public static void main(String[] args)throws Exception{
		FastScanner fs = new FastScanner(System.in);
		int N		= fs.nextInt();// 수열 크기 1<=100,000
		int arr[]	= new int[N + 1];
		int cnt[]	= new int[100_001];
		int freq[]	= new int[100_001];	// 빈도수가 i인 숫자의 개수
		sqrt		= (int)Math.sqrt(N);
		
		for(int i=1; i<=N; i++)
			arr[i] = fs.nextInt();// 1<=100,000
		
		int Q = fs.nextInt();// 쿼리수 1<=100,000
		int ans[] = new int[Q + 1];
		ArrayList<Query> query = new ArrayList<>();
		
		for(int i=1; i<=Q; i++)
			query.add(new Query(fs.nextInt(), fs.nextInt(), i));
		
		
		Collections.sort(query);
		
		int idxL = 1;
		int idxR = 0;
		int max = 0;
		for(Query q : query)
		{
			while(idxR < q.right)
			{
				++idxR;
				--freq[cnt[arr[idxR]]];
				++freq[++cnt[arr[idxR]]];
				max = Math.max(max, cnt[arr[idxR]]);
			}
			
			while(q.right < idxR)
			{
				if(--freq[cnt[arr[idxR]]] == 0 && max == cnt[arr[idxR]])
					--max;

				++freq[--cnt[arr[idxR]]];
				--idxR;
			}
			
			while(idxL < q.left)
			{
				if(--freq[cnt[arr[idxL]]] == 0 && max == cnt[arr[idxL]])
					--max;

				++freq[--cnt[arr[idxL]]];
				++idxL;
			}
            
			while(q.left < idxL)
			{
				--idxL;
				--freq[cnt[arr[idxL]]];
				++freq[++cnt[arr[idxL]]];
				max = Math.max(max, cnt[arr[idxL]]);
			}

            
			ans[q.idx]= max;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
}
//5			// 수열 크기 1<=100,000
//1 2 1 3 3	// 1<=100,000
//3			// 쿼리수 1<=100,000
//1 3
//2 3
//1 5
////답
//2
//1
//2