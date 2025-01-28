//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4373
//1초 / 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
class Node{
	int sum, a, b;
	Node(int s, int a, int b){
		sum=s;
		this.a=a;
		this.b=b;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int N = Integer.parseInt(br.readLine().trim());
			if(N == 0)
				break;
			
			int arr[] = new int[N];
			
			for(int i=0; i<N; i++)
				arr[i] = Integer.parseInt(br.readLine().trim());
			
			Arrays.sort(arr);
			
			ArrayList<Node> list1 = new ArrayList<>();
			ArrayList<Node> list2 = new ArrayList<>();
			
			for(int i=0; i<N-1; i++)
				for(int j=i+1; j<N; j++)
				{
					list1.add(new Node(arr[i] + arr[j], arr[i], arr[j]));
					list2.add(new Node(arr[j] - arr[i], arr[j], arr[i]));
				}
			
			Collections.sort(list1, (a,b)->a.sum - b.sum);
			Collections.sort(list2, (a,b)->b.sum - a.sum);
			
			int max = Integer.MIN_VALUE;
			for(Node now : list2)
				if(binarySearch(list1, now))
					max = Math.max(max, now.a);

			if(max == Integer.MIN_VALUE)
				sb.append("no solution");
			else
				sb.append(max);
			
			sb.append('\n');
		}
		System.out.print(sb);
	}
	public static boolean binarySearch(ArrayList<Node> list, Node target) {
		int s = 0;
		int e = list.size()-1;
		int end = 0;
		while(s<=e)
		{
			int mid = (s + e) >> 1;
			int sum = list.get(mid).sum;
			if(sum <= target.sum)
			{
				end = mid;
				s = mid + 1;
			}
			else
				e = mid - 1;
		}
		
		if(list.get(end).sum != target.sum)
			return false;
		
		int start = end;
		while(0<= start - 1 &&list.get(start-1).sum == target.sum)
			--start;
		
		while(start <= end) {
			int a = list.get(start).a;
			int b = list.get(start).b;
			if(a != target.a && a != target.b && b != target.a && b !=target.b)
				return true;
			++start;			
		}
		return false;
	}
}