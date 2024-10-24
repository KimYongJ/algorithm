//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/8120

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N+1];
		int res[] = new int[N+1];
		for(int i=1; i<=N; i++)
		{
			arr[i] = Integer.parseInt(br.readLine());
			list.add(i);
		}
		
		for(int i=N; i>=1; i--)
		{
			int idx = list.size() - arr[i] - 1;
			if(0<=idx && idx < list.size()) {
				res[i] = list.get(idx);
				list.remove(idx);
			}else {
				System.out.print("NIE");
				return;
			}
		}
		for(int i=1; i<=N; i++)
			sb.append(res[i]).append('\n');
		
		System.out.print(sb.toString());
	}
}