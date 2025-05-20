//https://www.acmicpc.net/problem/16264
//1// 테스트 케이스 수(1<=1,000)
//5// 배열 요소 수(1<=100,000)
//1 -1 1 1 -1
//5// 질의 수(1<=100,000)
//1 5
//1 3
//2 4
//3 4
//3 5
////각 부분 배열 중 합이 0이 되는 가장 긴 부분배열의 길이의 합 출력
////답 : 10

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;


class Main{
	
	static int OFF_SET = 100_000;
	static long res;
	static int sqrt;
	static int N, Q;
	static int fac[];// 가장 긴 차이 값의 구간을 확인할 제곱근 분할 배열
	static int cnt[];// 가장 긴 길이 차이를 담을 카운팅 배열
	static int psum[];// 누적합을 담을 배열
	static ArrayList<Query> query;
	static ArrayDeque<Integer> idxList[];
	static {
		idxList = new ArrayDeque[OFF_SET * 2 + 1];// idx : 등장한 수, value : 등장 수의 인덱스
		for(int i=0; i<idxList.length; i++)
			idxList[i] = new ArrayDeque<>();
	}
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		StringBuilder sb = new StringBuilder();
		int T = in.nextInt();//테스트 케이스 수(1<=1,000)
		while(T-->0)
		{
			N = in.nextInt();
			res = 0;
			sqrt = (int)Math.sqrt(N);
			psum = new int[N + 1];
			query = new ArrayList<>();
			fac = new int[N / sqrt + 1];
			cnt = new int[N + 1];
			psum[0] = OFF_SET;
			
			for(int i=1; i<=N; i++)// -1과 1로만 이루어진 배열
				psum[i] += psum[i-1] + in.nextInt();
			
			for(int i=0; i<idxList.length; i++)
				idxList[i].clear();
			
			Q = in.nextInt();// 질의 수(1<=100,000)
			
			for(int i=0, blockSize = (int)Math.sqrt(N); i<Q; i++)
			{
				int s = in.nextInt() - 1;
				int e = in.nextInt();
				query.add(new Query(s, e, s / blockSize));
			}
			
			Collections.sort(query);
			
			int s = 1;
			int e = 0;
			for(Query q : query)
			{
				while(e < q.e) excute(++e, 1);
				while(q.s < s) excute(--s, 2);
				while(q.e < e) excute(e--, 3);
				while(s < q.s) excute(s++, 4);
				
				res += find();
			}
			
			sb.append(res).append('\n');
		}
		System.out.print(sb);
	}
	static void cal(int num, int plus) {
		if(idxList[num].size() == 0)
			return;
		
		int diff = idxList[num].getLast() - idxList[num].getFirst();
		
		cnt[diff] += plus;
		fac[diff / sqrt] += plus;
	}
	static void excute(int idx, int flag) {
		int num = psum[idx];
		
		cal(num, -1);
		
		switch(flag)
		{
		case 1 :idxList[num].addLast(idx);break;
		case 2 :idxList[num].addFirst(idx);break;
		case 3 :idxList[num].removeLast();break;
		case 4 :idxList[num].removeFirst();break;
		}

		cal(num, 1);
	}
	static int find() {
		for(int i = N / sqrt; i>=0; i--)
		{
			if(fac[i] == 0)
				continue;
			
			int s = i * sqrt;
			int e = Math.min(N, s + sqrt -1);
			while(e >= s)
			{
				if(cnt[e] != 0)
					return e;
				
				--e;
			}
		}
		return 0;
	}
	static class Query implements Comparable<Query>{
		int s, e, fac;
		Query(int s, int e, int fac){
			this.s=s;
			this.e=e;
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
