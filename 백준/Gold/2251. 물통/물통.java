// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main{
	static int MAX_WATER[];
	static int arr[], INDEX[][] = {{1,2},{0,2},{0,1}};
	static boolean visit[][][];
	static HashSet<Integer> set;
	public static void DFS() {
		if(!visit[arr[0]][arr[1]][arr[2]]) {
			visit[arr[0]][arr[1]][arr[2]] = true;
			if(arr[0] == 0)
				set.add(arr[2]);
			for(int nowIdx=0; nowIdx<3; nowIdx++) {
				for(int nextIdx : INDEX[nowIdx]) {
					int nextNum = arr[nextIdx];
					int nowNum = arr[nowIdx];
					arr[nextIdx] += arr[nowIdx];
					if(arr[nextIdx] > MAX_WATER[nextIdx]) {
						arr[nowIdx] = arr[nextIdx] - MAX_WATER[nextIdx];
						arr[nextIdx] = MAX_WATER[nextIdx];
					}else{
						arr[nowIdx] = 0;
					}
					DFS();
					arr[nextIdx] = nextNum;
					arr[nowIdx]  = nowNum;
				}
			}
			visit[arr[0]][arr[1]][arr[2]] = false;
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		set   		= new HashSet<>();
		visit 		= new boolean[a+1][b+1][c+1];
		arr			= new int[] {0,0,c};
		MAX_WATER 	= new int[] {a,b,c};
		
		DFS();
		
		// 이하 출력
		ArrayList<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int i : list)
			sb.append(i).append(' ');
		
		System.out.print(sb);
	}
}
