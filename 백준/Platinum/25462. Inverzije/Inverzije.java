//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/25462
//4초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	
	static int N, Q;
	static int[] arr;			// 입력을 받을 배열
	static long[] ans;			// 최종 결과를 담을 배열
	static int[] fenwickTree;	// 구간합을 구할 펜윅 트리
	static ArrayList<Query> query;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N			= Integer.parseInt(st.nextToken());// 원소 수(1<=100,000)
		Q			= Integer.parseInt(st.nextToken());// 쿼리 수(1<=100,000)
		arr			= new int[N + 1];
		ans			= new long[Q + 1];
		fenwickTree = new int[N + 1];
		query		= new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// 1부터 N까지 서로 다른 자연수가 주어짐
		
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
		long cnt = 0;
		
		for(Query q : query)
		{
			// 현재 R보다 범위가 크면, 현재 숫자를 펜윅트리에 넣고 현재 숫자보다 큰 숫자들의 개수를 더함
			while(R < q.right)
				cnt += rightCal(arr[++R], 1);
			// 현재 범위보다 R이 크다면, 현재 숫자를 펜윅트리에서 빼고, 현재 숫자보다 큰 숫자들의 개수를 뺀다.
			while(q.right < R)
				cnt -= rightCal(arr[R--], -1);
			// 현재 L보다 범위가 크다면, 현재 숫자를 펜윅트리에서 빼고, 현재 숫자보다 작은 숫자들의 개수를 뺀다.
			while(L < q.left)
				cnt -= leftCal(arr[L++], -1);
			// 현재 범위보다 L이 크다면, 현재 숫자를 펜윅트리에 넣고, 현재 숫자보다 작은 숫자들의 개수를 더함
			while(q.left < L)
				cnt += leftCal(arr[--L], 1);

			ans[q.idx] = cnt;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static int rightCal(int num, int value) {
		update(num, value);
		return query(num + 1, N);
	}
	static int leftCal(int num, int value) {
		update(num, value);
		return query(1, num - 1);
	}
	static int query(int l, int r) {
		return sum(r) - sum(l - 1);
	}
	static int sum(int idx) {
		int ans = 0;
		while(idx > 0)
		{
			ans += fenwickTree[idx];
			idx -= idx & -idx;
		}
		return ans;
	}
	static void update(int idx, int value) {
		while(idx <= N)
		{
			fenwickTree[idx] += value;
			idx += idx & -idx;
		}
	}
	static class Query implements Comparable<Query>{
		int left, right, idx, fac;
		Query(int l, int r, int i, int f){
			left = l;
			right= r;
			idx = i;
			fac = f;
		}
		@Override
		public int compareTo(Query o) {
			// 구간이 같지 않을 경우 구간 기준 오름차순 정렬
			if(fac != o.fac)
				return fac - o.fac;
			// 구간이 짝수인 경우 오른쪽 기준 오름차순 정렬
			if((fac&1) == 0)
				return right - o.right;
			// 구간이 홀수인 경우 오른쪽 기준 내림차순 정렬
			return o.right - right;
		}
	}
}
//i<j 일때(i,j는인덱스), P[i]>P[j]를 만족하는 쌍의 개수
//5 5			// 원소 수(1<=100,000), 쿼리 수(1<=100,000)
//4 3 5 1 2	// 1부터 N까지 서로다른 자연수가 주어짐
//2 3
//1 5
//4 4
//1 4
//2 4
//답
//0
//7
//0
//4
//2