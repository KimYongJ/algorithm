// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{

	static StringBuilder sb = new StringBuilder();
	static int N, M, number[];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//1부터 N까지 자연수 중에서 M개를 고른 수열
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		number = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++)
			number[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(number); // 오름차순 출력해야 하므로 정렬처리.
		
		DFS(0,new int[M], new boolean[N]); // 순서 : 깊이, 값을 담을 배열, 방문 처리할 배열	

		System.out.println(sb);
	}
	public static void DFS(int depth, int[] arr, boolean[] visit) {
		if(depth == M) {// M개를 고른 경우 출력 처리.
			for(int i=0; i<M; i++)
				sb.append(arr[i]).append(' ');
			sb.append('\n');
			return;
		}
		for(int i=0; i<N; i++) { // 0부터 N까지 반복
			if(!visit[i]) { // 주어진 값들 중 방문하지 않은 값이라면 이하 실행  
				arr[depth] = number[i];// arr에 값을 하나 담는다.
				visit[i] = true; // 담은 값 방문처리 
				DFS(depth+1,arr,visit);// DFS실행
				visit[i] = false;// 백트래킹 : 담은 값에 대해 방문처리를 풀어 다음에 또 담을 수 있게 한다. 
			}
		}
	}
}