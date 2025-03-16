//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15509
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 물질 개수(1<=천)
		int M = Integer.parseInt(st.nextToken());	// 분리할 수 없는 쌍(1<=백만)
		int K = Integer.parseInt(st.nextToken());	// 하나의 공간에 놓는 항아리수, 다른 곳은 N-K개 가능
		int parent[] = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int aParent = getParent(parent, Integer.parseInt(st.nextToken()));//1<=N
			int bParent = getParent(parent, Integer.parseInt(st.nextToken()));//1<=N
			
			if(aParent < bParent)
			{
				parent[bParent] = aParent;
				continue;
			}
			
			parent[aParent] = bParent;
		}
		
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=1; i<=N; i++)
		{
			int p = getParent(parent, parent[i]);
			map.put(p, map.getOrDefault(p, 0) + 1);
		}
		
		int arr[] = new int[map.size()+1];
		int len = 1;
		
		for(int key : map.keySet())
			arr[len++] = map.get(key);
		
		boolean dp[] = new boolean[K + 1];
		
		dp[0] = true;
		
		for(int i=1; i<len; i++)
			for(int j=K; j>=arr[i]; j--)
				dp[j] |= dp[j - arr[i]];
		
		System.out.print(dp[K] ? "SAFE" : "DOOMED");
	}
	public static int getParent(int[] parent, int node) {
		return parent[node] = parent[node] == node ? 
								node : getParent(parent, parent[node]);
	}
}