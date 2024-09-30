//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16566
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static int getIdx(int arr[], int target) {
		int s = 0;
		int e = arr.length - 1;
		int idx = 0;
		while(s <= e) {
			int mid = (s+e) >> 1;
			if(arr[mid] <= target) {
				s = mid + 1;
			}else {
				e = mid - 1;
				idx = mid;
			}
		}
		return idx;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());	// 숫자 범위 1<=사백만
		int M = Integer.parseInt(st.nextToken());	// 내카드번호 1<=N
		int K = Integer.parseInt(st.nextToken());	// 철수가 낼 카드 순서
		int arr[] = new int[M];
		int parent[] = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;	// 자기보다 바로 큰 값의 위치를 담을 것
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			arr[i] = Integer.parseInt(st.nextToken());
			
		Arrays.sort(arr);
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++)
		{
			int target = Integer.parseInt(st.nextToken());
			int idx = getIdx(arr, target);
			
			int maxIdx = find(parent, idx);
			sb.append(arr[maxIdx]).append('\n');
			parent[idx] = find(parent, maxIdx + 1);
		}
		System.out.print(sb);
	}
	public static int find(int [] parent, int idx) {
		if(parent[idx] == idx) return idx;
		return parent[idx] = find(parent, parent[idx]);
	}
}