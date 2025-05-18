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
	
	static int N, Q;
	static int idx;// 좌표 압축 후 가장 큰 학생의 번호
	static int sqrt;// 학생의 번호를 구간으로 나눌 제곱근 값
	static int ans[];// 최종 결과를 담을 배열
	static int arr[];// 처음 입력되는 학생의 번호
	static int brr[];// 좌표 압축에 사용할 배열
	static int cnt[];// idx : 학생의 번호, value : 그 학생의 등장 횟수
	static int fac[];// idx : 학생번호의 구간, value : 해당 구간의 등장한 최대 횟수
	static int facFreq[][];// 각 학생의 구간마다 등장 횟수의 등장횟수를 담는 배열, value : 등장 횟수
	static List<Query> query;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		Q = in.nextInt();
		sqrt = (int)Math.sqrt(N);
		ans = new int[Q + 1];
		arr = new int[N + 1];
		brr = new int[N + 1];
		cnt = new int[100_001];
		fac = new int[N / sqrt + 1];
		facFreq = new int[N / sqrt + 1][N + 1];
		query = new ArrayList<>();
		// 좌표 압축을 위해 brr배열에 같은 값을 담음
		for(int i=1; i<=N; i++)
			arr[i] = brr[i] = in.nextInt();
		
		Arrays.sort(brr);// 좌표 압축을 위한 정렬
		
		// 좌표 압축 및 압축 값의 가장 큰 값을 idx에 저장
		for(int i=1; i<=N; i++)
		{
			arr[i] = binarySearch(arr[i]);
			if(idx < arr[i])
				idx = arr[i];
		}
		// 쿼리를 입력 받음
		for(int i=1, blockSize = (int)Math.sqrt(N); i<=Q; i++)
		{
			int s = in.nextInt();
			int e = in.nextInt();
			query.add(new Query(s, e, i, s / blockSize));
		}
		// mo's 정렬 
		Collections.sort(query);
		
		int s = 1;
		int e = 0;
		
		for(Query q : query)
		{
			while(e < q.e) plus(arr[++e]);
			while(q.s < s) plus(arr[--s]);
			while(q.e < e) minus(arr[e--]);
			while(s < q.s) minus(arr[s++]);
			// ans에 값을 담을 때 값 압축 전 값으로 저장
			ans[q.idx] = brr[ find() ]; 
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static void minus(int nodeNum) {
		// 해당 노드 번호의 구간을 구함
		int facIdx = nodeNum / sqrt;
		// 해당 구간의 등장 횟수(cnt[nodeNum])이 0이 되었고,
		// 그 등장 횟수(cnt[nodeNum])이 해당 구간(fac[facIdx])의 최댓 값이였을 경우
		// 그 최댓 값(fac[facIDx])을 -1 감소 시킴
		if(--facFreq[facIdx][cnt[nodeNum]] == 0 && fac[facIdx] == cnt[nodeNum])
			--fac[facIdx];
		// 해당 학생 등장 횟수 하나 감소
		--cnt[nodeNum];
	}
	static void plus(int nodeNum) {
		int facIdx = nodeNum / sqrt;
		// 해당 학생 등장 횟수 하나 추가
		++cnt[nodeNum];
		// 해당 학생이 있는 구간의 최댓 값 갱신
		fac[facIdx] = Math.max(fac[facIdx], cnt[nodeNum]);
		// 해당 학생이 있는 구간의 해당 등장횟수(cnt[nodeNum]) 값 증가
		++facFreq[facIdx][cnt[nodeNum]];
	}
	static int find() {
		int max = 0;
		// 구간에서 가장 큰 값을 모두 순회하여 찾아냄
		for(int i=0; i<fac.length; i++)
			max = Math.max(fac[i], max);
		
		// 구간에 대해 역으로 순회하며, max와 같은 첫번 째 값만 연산대상
		for(int i=fac.length - 1; i>=0; i--)
		{
			if(fac[i] != max)
				continue;
			// 해당 구간의 s와 e를 구해서 그중 max와 같은 최대 등장 횟수를
			// 갖는 학생 아이디를 반환함
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
