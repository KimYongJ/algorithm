//https://www.acmicpc.net/problem/1017
//2초 128MB
//6// 수의 크기 1<=50
//1 4 7 10 11 12// 원소 수 1<=1,000, 중복되지 않음
//답(없으면 -1출력) : 4 10
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
class Main{
	
	static int time;// 각 숫자의 방문 시간을 표시할 변수,
	static int N;
	static int arr[];// 초기값을 입력 받을 배열
	static int match[];// 이분 매칭시 매칭된 값을 담을 배열
	static int visitTime[];
	static boolean isNotPrime[];// 소수면 false, 소수가 아니면 true
	static List<Integer> pairList;// 첫번째 수와 더했을 때 소수인 숫자들의 리스트
	static List<Integer> pq;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		match = new int[N + 1];
		visitTime = new int[N + 1];
		pairList = new ArrayList<>();
		isNotPrime = new boolean[2000];
		pq = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		setEratos();
		
		// 첫번째 인자와 더해서 소수가 되는 모든 수를 저장한다.
		for(int i=2; i<=N; i++)
			if(!isNotPrime[arr[1] + arr[i]])
				pairList.add(i);
		
		// 첫번째 인자와 더해서 소수가 되는 모든 수를 순회 하면서 답을 구함
		for(int idx : pairList)
		{
			Arrays.fill(match,0);
			int pair = 0;
			// 2번부터 끝까지 돌면서 매칭 가능한 개수를 찾음
			for(int i=2; i<=N; i++)
			{
				visitTime[1] = visitTime[idx] = ++time;
				if(dfs(i))
				{
					++pair;
				}
			}
			// 두개를 제외하고 모두 매칭이 되었으면 결과 저장
			if(pair == N - 2)
				pq.add(arr[idx]);
		}
		
		Collections.sort(pq);
		
		StringBuilder sb = new StringBuilder();
		
		for(int res : pq)
			sb.append(res).append(' ');
		
		System.out.print(sb.length() == 0 ? -1 : sb);
	}
	static boolean dfs(int idx) {
		for(int i=2; i<=N; i++)
		{
			// 인덱스가 같거나, 이미 방문했거나, 두 숫자를 더했을 때 소수가 아니면 스킵
			if(i == idx || visitTime[i] == time || isNotPrime[arr[i] + arr[idx]])
				continue;
			// 방문 시간 체크
			visitTime[i] = time;
			
			// 매칭 가능한지 재귀 탐색
			if(match[i] == 0 || dfs(match[i]))
			{
				match[i] = idx;
				return true;
			}
		}
		return false;
	}
	static void setEratos() {
		// 소수면 false;
		for(int i=2; i<2000; i++)
			if(!isNotPrime[i])
				for(int j=i+i; j<2000; j+=i)
					isNotPrime[j] = true;
	}
}