//https://www.acmicpc.net/problem/23238
//1.2초 1024MB
//5 3		// 일자(1<=100,000), 쿼리 수(1<=100,000)
//2 1 2 1 1	// 1일부터N일 까지의 최우수 학생 ID 번호를 나타내는 N개의 양의 정수 (1<=10^9)
//1 2		// s일 부터 e일 까지
//1 4
//1 5
//// 출력 : S,E 동안 가장 자주 최우수 학생으로 선발된 학생의 ID 출력, 여러명이면 그 중ID번호가 큰것 출력
//2
//2
//1
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class Main{
	
	static int sqrt;
	static int idx;// 좌표 압축시 사용, idx는 결국 주어진 값중 가장 큰 값이 된다.
	static int N, Q;
	static int arr[];// 초기 입력값
	static int brr[];// 좌표압축을 위한 배열
	static int ans[];
	static int cnt[];// 각 숫자의 등장횟수
	static int fac[];// 노드 범위를 제곱근으로 나눔, (idx : 노드의 범위) ( value : 그 노드 범위안 가장 큰 등장 횟수 )
	static int facFreq[][];// fac배열의 가장 큰 등장 횟수를 컨트롤 하기 위한 카운팅 배열 (노드의 범위 : 등장횟수) ( value : 등장 횟수 ) 
	static List<Query> query;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();// 일자(1<=100,000)
		Q = in.nextInt();// 쿼리 수(1<=100,000)
		arr = new int[N + 1];
		brr = new int[N + 1];
		ans = new int[Q + 1];
		query = new ArrayList<>();
		
		for(int i=1; i<=N; i++)
			arr[i] = brr[i] = in.nextInt();//(1<=10^9)
		
		Arrays.sort(brr);
		// 이분 탐색으로 좌표 압축
		for(int i=1; i<=N; i++)
		{
			arr[i] = binarySearch(arr[i]);
			if(idx < arr[i])
				idx = arr[i];
		}
		
		// idx = 좌표 압축으로 인해 등장 숫자중 최대 숫자가 됨
		sqrt = (int)Math.sqrt(N);
		cnt = new int[idx + 1];
		fac = new int[idx / sqrt + 2];
		facFreq = new int[idx / sqrt + 2][N + 1];
		
		for(int i=1, blockSize = (int)Math.sqrt(N); i<=Q; i++)
		{
			int s = in.nextInt();
			int e = in.nextInt();
			query.add(new Query(s, e, i, s / blockSize));
		}
		
		Collections.sort(query);
		
		int s = 1;
		int e = 0;

		for(Query q : query)
		{
			while(q.s < s) plus(arr[--s]);
			while(e < q.e) plus(arr[++e]);
			while(s < q.s) minus(arr[s++]);
			while(q.e < e) minus(arr[e--]);
			
			ans[q.idx] = brr[find()]; 
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
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
	static int find() {
		int max = 0;
		
		for(int i=0; i<fac.length; i++)
			if(fac[i] > max)
				max = fac[i];
		
		for(int i=fac.length - 1; i>=0; i--)
		{
			
			if(fac[i] != max)
				continue;
			
			int s = sqrt * i;
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
	static void minus(int nodeNum)
	{
		int facIdx = nodeNum / sqrt;

		if(--facFreq[facIdx][cnt[nodeNum]] == 0 && fac[facIdx] == cnt[nodeNum]) {
			--fac[facIdx];
		}

		--cnt[nodeNum];
		
		++facFreq[facIdx][cnt[nodeNum]];
	}
	static void plus(int nodeNum) {
		int facIdx = nodeNum / sqrt;
		
		--facFreq[facIdx][cnt[nodeNum]];

		++cnt[nodeNum];

		++facFreq[facIdx][cnt[nodeNum]];
		fac[facIdx] = Math.max(fac[facIdx], cnt[nodeNum]);
	}
	static class Query implements Comparable<Query>{
		int s, e, idx, fac;
		Query(int s, int e, int idx, int  fac){
			this.s=s;
			this.e=e;
			this.idx=idx;
			this.fac=fac;
		}
		@Override
		public int compareTo(Query o) {
			if(fac != o.fac)
				return fac - o.fac;
			
			if((fac & 1) == 0)
				return e - o.e;
			
			return o.e - e;
		}
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