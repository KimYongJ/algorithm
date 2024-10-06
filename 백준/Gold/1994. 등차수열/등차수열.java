//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1994
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
class Main{
	public static int find(int dp[][], int arr[], int i, int j) {
		if(i > j)
			return 0;
		if(i == j)
			return 1;
		
		int ret = dp[i][j];
		if(ret != -1)
			return ret;
		
		ret = 2;
		
		int d = arr[j] - arr[i];
		int next = arr[j] + d;
		
		int idx = Arrays.binarySearch(arr,  next);
		if(0 <= idx && arr[idx] == next)
			return ret = find(dp, arr, j, idx) + 1;
		
		return ret;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		int max	= 1;

		HashMap<Integer,Integer> hm = new HashMap<>();
		ArrayList<Integer> list		= new ArrayList<>();
		
		for(int i=0; i<N; i++)
		{
			int num = Integer.parseInt(br.readLine());
			int cnt = hm.getOrDefault(num, 0);
			if(cnt == 0)
				list.add(num);

			hm.put(num, ++cnt);
			
			if(max < cnt)
				max = cnt;
		}
		
		int arr[] = list.stream().mapToInt(Integer::intValue).toArray();

		N = arr.length;
		
		Arrays.sort(arr);
		
		int dp[][] = new int[2001][2001];
		
		for(int d[] : dp)
			Arrays.fill(d, -1);
		
		for(int i=0; i<N-1; i++)
			for(int j=i+1; j<N; j++)
				max = Math.max(max, find(dp, arr, i, j));

		System.out.print(max);
	}
}