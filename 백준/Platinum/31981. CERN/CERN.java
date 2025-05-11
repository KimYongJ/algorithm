//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/31981
//4초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static int N, Q;
	static int countElement;
	static int count2;
	static int maxFreq;
	static int ans[];
	static int arr[];
	static int cnt[];
	static int freq[];
	static ArrayList<Query> query;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());// 원소 개수 2<=500,000
		Q		= Integer.parseInt(st.nextToken());// 질의 개수 1<=500,000
		arr		= new int[N + 1];
		ans		= new int[Q + 1];
		cnt		= new int[500_002];
		freq	= new int[N + 1];
		query	= new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1, sqrt = (int)Math.sqrt(N); i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			query.add(new Query(l, r, i, l / sqrt));
		}
		
		Collections.sort(query);
		
		int L = 1;
		int R = 0;
		for(Query q : query)
		{
			while(q.left < L)	plus(arr[--L]);
			while(R < q.right)	plus(arr[++R]);
			while(L < q.left)	sub(arr[L++]);
			while(q.right < R)	sub(arr[R--]);
			
			ans[q.idx] = getAns(q.right - q.left + 1); 
		}
		
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static void sub(int num) {
		if(cnt[num] == maxFreq && freq[cnt[num]] == 1)
			--maxFreq;
		
		--freq[cnt[num]];
		++freq[--cnt[num]];
		
		
		if(cnt[num] == 0)
			countElement--;
		if(cnt[num] == 1)
			count2--;
	}
	static void plus(int num) {
		if(cnt[num] == maxFreq && freq[cnt[num] + 1] == 0)
			++maxFreq;
		
		--freq[cnt[num]];
		++freq[++cnt[num]];
		
		if(cnt[num] == 1)
			countElement++;
		if(cnt[num] == 2)
			count2++;
	}
	static int getAns(int len) {
		// 원소 개수가 2개이고, 빈도가 가장 높은게 배열 길이와 같다면 항상 모두 충돌 가능
		if(countElement == 2 && len == maxFreq * 2)
			return 0;
		// 빈도가 가장 높은게 구간의 길이 절반보다 크다면 무조건 그 원소만 남음
		if(len <= maxFreq * 2)
			return 1;
		// 구간의 길이가 홀수면, 각 원소 수만큼 남길 수 있고
		// 구간의 길이가 짝수면, 2번이상 등장하는 것만 남길 수 있음
		return (len & 1) == 1 ? countElement : count2;
	}
	static class Query implements Comparable<Query>{
		int left, right, idx, fac;
		Query(int l, int r, int i , int f){
			left = l;
			right= r;
			idx = i;
			fac = f;
		}
		@Override
		public int compareTo(Query o) {
			if(fac != o.fac)
				return fac - o.fac;
			
			return fac % 2 == 0 ? right - o.right : o.right - right;
		}
	}
}
//11 5
//2 4 2 3 4 4 3 1 4 4 4
//1 4
//2 8
//6 9
//8 10
//8 11
//답
//1
//4
//1
//1
//1
