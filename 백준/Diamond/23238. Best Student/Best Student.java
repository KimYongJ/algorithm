//https://www.acmicpc.net/problem/23238
//1.2초 1024MB
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Main{
	
	static int N, Q;
	static int idx;
	static int sqrt;
	static int ans[];
	static int arr[];
	static int brr[];
	static int cnt[];
	static int fac[];
	static int facFreq[][];
	static List<Query> query;
	
	public static void main(String[] args)throws Exception{
		N = read();
		Q = read();
		sqrt = (int)Math.sqrt(N);
		ans = new int[Q + 1];
		arr = new int[N + 1];
		brr = new int[N + 1];
		cnt = new int[100_001];
		fac = new int[N / sqrt + 1];
		facFreq = new int[N / sqrt + 1][N + 1];
		query = new ArrayList<>();
		
		for(int i=1; i<=N; i++)
			arr[i] = brr[i] = read();
		
		Arrays.sort(brr);// 좌표 압축을 위한 정렬
		
		for(int i=1; i<=N; i++)
		{
			arr[i] = binarySearch(arr[i]);
			if(idx < arr[i])
				idx = arr[i];
		}
		
		for(int i=1, blockSize = (int)Math.sqrt(N); i<=Q; i++)
		{
			int s = read();
			int e = read();
			query.add(new Query(s, e, i, s / blockSize));
		}
		
		Collections.sort(query);
		
		int s = 1;
		int e = 0;
		
		for(Query q : query)
		{
			while(e < q.e) plus(arr[++e]);
			while(q.s < s) plus(arr[--s]);
			while(q.e < e) minus(arr[e--]);
			while(s < q.s) minus(arr[s++]);
			
			ans[q.idx] = brr[ find() ]; 
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static void minus(int nodeNum) {
		int facIdx = nodeNum / sqrt;
		
		if(--facFreq[facIdx][cnt[nodeNum]] == 0 && fac[facIdx] == cnt[nodeNum])
			--fac[facIdx];
		
		--cnt[nodeNum];
	}
	static void plus(int nodeNum) {
		int facIdx = nodeNum / sqrt;
		
		++cnt[nodeNum];
		
		fac[facIdx] = Math.max(fac[facIdx], cnt[nodeNum]);
		
		++facFreq[facIdx][cnt[nodeNum]];
	}
	static int find() {
		int max = 0;
		for(int i=fac.length - 1; i>=0; i--)
			max = Math.max(fac[i], max);
		
		for(int i=fac.length - 1; i>=0; i--)
		{
			if(fac[i] != max)
				continue;
			
			int s = i * sqrt;
			int e = Math.min(s + sqrt, idx);
			while(e >= s)
			{
				if(cnt[e] == max)
					return e;
				--e;
			}
		}
		return 0;
	}
	static int binarySearch(int target) {
		int s = 1;
		int e = N;
		int res = 0;
		
		while(s <= e)
		{
			int mid = (s + e) >> 1;

			if(brr[mid] < target)
				s = mid + 1;
			else
			{
				res = mid;
				e = mid - 1;
			}
		}
		
		return res;
	}
	static class Query implements Comparable<Query>{
		int s, e, idx, fac;
		Query(int s, int e, int idx, int fac){
			this.s=s;
			this.e=e;
			this.idx=idx;
			this.fac=fac;
		}
		@Override
		public int compareTo(Query o) {
			if(fac != o.fac)
				return fac - o.fac;
			
			if((fac&1) == 0)
				return e - o.e;
			
			return o.e - e;
		}
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}