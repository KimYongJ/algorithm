//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2517
//1초 / 256MB
//요약 : 입력되는 순서대로 자기 앞에 자기보다 큰수가 몇개 나왔는지 세고, 그 숫자에 + 1을 출력

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main{
	
	
	static int N;
	static int[] arr, tree;
	static ArrayList<Integer> list = new ArrayList<>();
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		N	= Integer.parseInt(br.readLine());	// 3<=오십이하
		arr	= new int[N];
		tree= new int[N<<2];
		
		list.add(0);
		for(int i=0; i<N; i++)
			list.add(arr[i] = Integer.parseInt(br.readLine()));
		
		Collections.sort(list);
		
		for(int a : arr)
		{
			int idx = binarySearch(a);
			
			sb.append(query(1, 1, N, idx+1, N)+ 1).append('\n');
			
			update(1, 1, N, idx);
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e, int idx) {
		if(idx < s || e< idx)
			return;
		
		tree[treeNode]++;
		
		if(s != e)
		{
			int nextNode = treeNode << 1;
			int mid = (s + e) >> 1;
			update(nextNode, s, mid, idx);
			update(nextNode | 1, mid + 1, e, idx);
		}
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int nextNode = treeNode << 1;
		int mid = (s + e) >> 1;
		return query(nextNode, s, mid, left, right) 
				+ query(nextNode | 1, mid + 1, e, left, right);
	}
	public static int binarySearch(int target) {
		int s = 1;
		int e = N;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			int num = list.get(mid);
			if(num == target)
				return mid;
			if(num < target)
				s = mid + 1;
			else e = mid - 1;
		}
		return 0;
	}
}