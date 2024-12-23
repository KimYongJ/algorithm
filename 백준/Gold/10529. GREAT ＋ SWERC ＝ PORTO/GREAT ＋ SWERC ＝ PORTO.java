//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10529

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Main{
	
	static ArrayList<Integer> list = new ArrayList<>();
	static int[][] arr;
	static int[] order;
	static int N, cnt, len;
	static boolean[] visit;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<Integer> set = new HashSet<>();
		N		= Integer.parseInt(br.readLine());
		arr		= new int[N][];
		
		for(int i=0; i<N; i++)
		{
			String str = br.readLine();
			int len = str.length();
			arr[i] = new int[len];
			for(int j=0; j<len; j++)
			{
				arr[i][j] = str.charAt(j) - 'A';
				set.add(arr[i][j]);
			}
		}
		
		list.addAll(set);
		
		len		= list.size();
		order	= new int[30];
		visit	= new boolean[10];
		
		comb(0);
		
		System.out.print(cnt);
	}
	public static void comb(int depth) {
		if(depth == len) {
			cal();
			return;
		}
		for(int i=0; i<10; i++)
		{
			if(!visit[i])
			{
				visit[i] = true;
				order[list.get(depth)] = i;
				comb(depth + 1);
				visit[i] = false;
			}
		}
	}
	public static void cal() {
		int number[] = new int[N];
		for(int i=0; i<N; i++)
		{
			int num = changeNum(arr[i]);
			if(num == -1)
				return;
			number[i] = num;
		}
		int sum = 0;
		for(int i=0; i<N-1; i++)
			sum += number[i];
		if(sum == number[N-1])
			++cnt;
	}
	
	public static int changeNum(int arr[]) {
		if(order[arr[0]] == 0)
			return -1;
		int num = 0;
		for(int i=0; i<arr.length; i++) {
			num = num * 10 + order[arr[i]];
		}
		return num;
	}
}