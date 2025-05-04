//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14897
//5초 1024MB
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
class Main{
	static class Query implements Comparable<Query>{
		int left, right, idx, factor;
		Query(int l, int r, int i){
			left = l;
			right= r;
			idx = i;
			factor = left / sqrt;
		}
		@Override
		public int compareTo(Query o) {
			return factor == o.factor 
					? right - o.right 
					: factor - o.factor;
		}
	}
	
	static int sqrt;
	static int idx;
	static int N, Q;
	static int arr[];
	static ArrayList<Query> query;
	static Reader in = new Reader();
	
	public static void main(String[] args)throws Exception{
		inputAndComp();
		getQueryAndSort();
		solve();
	}
	
	static void inputAndComp()throws Exception{
		Set<Integer> set = new HashSet<>();
		N		= in.nextInt();// 1<=1,000,000
		sqrt	= (int)Math.sqrt(N);
		arr		= new int[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			arr[i] = in.nextInt();
			set.add(arr[i]);
		}

		HashMap<Integer, Integer> map = new HashMap<>();
		ArrayList<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		
		for(;idx<list.size();)
			map.put(list.get(idx),++idx);
		
		for(int i=1; i<=N; i++)
			arr[i] = map.get(arr[i]);
	}
	static void getQueryAndSort()throws Exception {
		query = new ArrayList<>();
		
		Q = in.nextInt();// 1<=1,000,000
		for(int i=1; i<=Q; i++)
			query.add(new Query(in.nextInt(), in.nextInt(), i));
		
		Collections.sort(query);
	}
	static void solve() {
		int ans[] = new int[Q + 1];
		int count[] = new int[idx + 1];
		int idxL = 1;
		int idxR = 0;
		int diff = 0;
		for(Query q : query)
		{
			while(idxR < q.right)
				if(count[arr[++idxR]]++ == 0)
					diff++;

			while(q.right < idxR)
				if(--count[arr[idxR--]] == 0)
					--diff;
				
			while(q.left < idxL)
				if(count[arr[--idxL]]++ == 0)
					diff++;
			
			while(idxL < q.left)
				if(--count[arr[idxL++]] == 0)
					--diff;

			ans[q.idx] = diff; 
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
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