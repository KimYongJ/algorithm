//https://www.acmicpc.net/problem/17731
//4초 512MB
//5 5	// 일자(1<=100,000), 쿼리수(1<=100,000)
//9 8 7 8 9	// 사건의 종류(1<=1,000,000,000)
//1 2	// 범위
//3 4
//4 4
//1 4
//2 4
//// 답
//9
//8
//8
//16
//16

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Main{
	
	static final int PLUS = 1;
	static final int MINUS = -1;
	static int sqrt;
	static int N, Q;
	static int arr[];// 초기 입력되는 사건의 종류
	static int brr[];// 값 압축을 위한 배열
	static long ans[];// 최종 결과를 담을 배열
	static long tree[];// 구간 최댓값 세그먼트트리, (idx : 압축된 사건의 종류) ( value : 해당 사건 종류의 중요도 )
	static List<Query> query;// 쿼리
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		Q = in.nextInt();
		sqrt = (int)Math.sqrt(N);
		arr = new int[N + 1];
		brr = new int[N + 1];
		ans = new long[Q + 1];
		tree = new long[N * 4];
		query = new ArrayList<>();
		
		// 값 압축을 위해 두 배열에 모두 값을 초기화 함
		for(int i=1; i<=N; i++)
			arr[i] = brr[i] = in.nextInt();
		// 값 압축을 위한 brr배열 정렬
		Arrays.sort(brr);
		// 값 압축
		for(int i=1; i<=N; i++)
			arr[i] = binarySearch(arr[i]);
		// 쿼리 입력및 mo's 정렬
		for(int i=1; i<=Q; i++)
		{
			int s = in.nextInt();
			int e = in.nextInt();
			query.add(new Query(s, e, i, s / sqrt));
		}
		
		Collections.sort(query);
		
		int s = 1;
		int e = 0;
		
		for(Query q : query)
		{
			while(e < q.e) cal(arr[++e], PLUS);
			while(q.s < s) cal(arr[--s], PLUS);
			while(q.e < e) cal(arr[e--], MINUS);
			while(s < q.s) cal(arr[s++], MINUS);
			// 최댓값 세그먼트 트리이기 때문에 tree[1]에 최댓값이 있다.
			ans[q.idx] = tree[1]; 
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static void cal(int idx, int flag) {
		// 주어진 값을 세그먼트 트리에 더하거나, 뺀다. flag는 양, 음 플레그
		update(1, 1, N, idx, brr[idx] * flag);
		
	}
	static void update(int treeNode, int s, int e, int targetIdx, long value) {
		if(targetIdx < s || e < targetIdx)
			return;
		
		if(s == e)
		{
			tree[treeNode] += value;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, targetIdx, value);
		update(treeNode << 1 | 1, mid + 1, e, targetIdx, value);
		
		tree[treeNode] = Math.max(tree[treeNode << 1],  tree[treeNode << 1 | 1]);
	}
	static int binarySearch(int target) {
		int s = 1;
		int e = N;
		int res = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(brr[mid] >= target)
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
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
			
			if((fac & 1)==0)
				return e - o.e;
			
			return o.e - e;
		}
	}
}


class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    String nextString() throws Exception {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) < 32) { if (size < 0) return "endLine"; }
        do sb.appendCodePoint(c);
        while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
    }
    
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

    long nextLong() throws Exception {
        long n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    double nextDouble() throws Exception {
        double n = 0, div = 1;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -12345; }
        if (c == 45) { c = read(); isMinus = true; }
        else if (c == 46) { c = read(); }
        do n = (n * 10) + (c & 15);
        while (isNumber(c = read()));
        if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
        return isMinus ? -n : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    boolean isAlphabet(byte c){
        return (64 < c && c < 91) || (96 < c && c < 123);
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}
