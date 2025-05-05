//https://github.com/KimyongJ/algorithm
//https://www.acmicpc.net/problem/13553
//3초 512MB
import java.util.ArrayList;
import java.util.Collections;
class Main{
	
	static int len = 100_000;
	static int fenwickTree[];
	
	public static void main(String[] args)throws Exception{
		Reader in	= new Reader();
		int N		= in.nextInt();// 수열크기 1<=100,000
		int K		= in.nextInt();// 정수K 1<=100,000
		int arr[]	= new int[N + 1];
		fenwickTree	= new int[len + 1];

		for(int i=1; i<=N; i++)
			arr[i] = in.nextInt();
		
		ArrayList<Query> query = new ArrayList<>();
		int Q = in.nextInt();// 쿼리 수 1<=100,000
		long ans[] = new long[Q + 1];
		
		for(int i=1, sqrt = (int)Math.sqrt(N); i<=Q; i++)
		{
			int l = in.nextInt();
			int r = in.nextInt();
			query.add(new Query(l, r, i, l / sqrt));
		}
		
		Collections.sort(query);
		
		int left = 1;
		int right = 0;
		long pairCnt = 0;
		
		for(Query q : query)
		{
			while(right < q.right)
			{
				++right;
				// right를 하나 넣으면 이전에 |v-x| <= K인 것들의 쌍이 한개씩 더생김
				// 그래서 arr[right]와 K를 활용해 나올 수 있는 범위의 개수를 모두 카운팅해서 더한다.
				pairCnt += sum(arr[right] - K, arr[right] + K);
				
				// 해당 값의 개수를 1추가한다.
				update(arr[right], 1);
			}
			
			while(q.right < right)
			{
				// 해당 값의 개수를 -1한다.
				update(arr[right], -1);
				// arr[right]가 하나 없어졌기 때문에, |v-x| <= K인 것들의 쌍이 하나씩 없어짐
				// 그래서 arr[right]와 K를 활용해 나올 수 있는 범위의 개수를 모두 카운팅해서 뺀다.
				pairCnt -= sum(arr[right] - K, arr[right] + K);
				--right;
			}
			
			while(left < q.left)
			{
				update(arr[left], -1);
				pairCnt -= sum(arr[left] - K, arr[left] + K);
				++left;
			}
			
			while(q.left < left)
			{
				--left;
				pairCnt += sum(arr[left] - K, arr[left] + K);
				update(arr[left], 1);
			}
			
			ans[q.idx] = pairCnt;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static int sum(int l, int r) {
		if(l > r)
			return 0;
		if(l < 1)
			l = 1;
		if(r > len)
			r = len;
		
		return query(r) - query(l - 1);
	}
	static void update(int targetIdx, int value) {
		
		for(int i=targetIdx; i<=len; i+= i & -i)
			fenwickTree[i] += value;
		
	}
	static int query(int idx) {
		int sum = 0;
		
		for(int i=idx; i>0; i-= i & -i)
			sum += fenwickTree[i];
		
		return sum;
	}
	static class Query implements Comparable<Query>{
		int left, right, idx, factor;
		Query(int l, int r, int i, int f){
			left = l;
			right= r;
			idx = i;
			factor = f;
		}
		@Override
		public int compareTo(Query o) {
			if(factor != o.factor)
				return factor - o.factor;
			
			return (factor & 1) == 0 ? right - o.right : o.right - right;

		}
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

//4 31			// 수열크기 1<=100,000 , 정수K 1<=100,000
//1 16 32 64	// 주어진 수 1<=100,000
//4				// 쿼리 수 1<=100,000
//1 4
//1 2
//2 4
//2 3