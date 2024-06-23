import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[][] arr;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		StringTokenizer st;
        
		for(int i=0; i<n; i++) {
			int vertex = 0;
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				vertex += arr[i][j];
			}
			if(vertex%2!=0) {
				System.out.println(-1);
				return;
			}
		}
		
		sb = new StringBuilder();
		getEulerCircuit(0);
		System.out.println(sb.toString());
	}
	
	static void getEulerCircuit(int cur) {
		for(int nxt=0; nxt<arr.length; nxt++) {
			while(arr[cur][nxt] > 0) {
				arr[cur][nxt]--;
				arr[nxt][cur]--;
				getEulerCircuit(nxt);
			}
		}
		sb.append((cur+1)+" ");
	}
}