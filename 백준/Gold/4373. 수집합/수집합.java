//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4373
//1초 / 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
class Node{
	int sum, max, min;
	Node(int s, int a, int b){
		sum=s;
		max=a;
		min=b;
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
			
			ArrayList<Node> sum = new ArrayList<>();
			ArrayList<Node> dif = new ArrayList<>();
			
			for(int i=0; i<N-1; i++)
				for(int j=i+1; j<N; j++)
				{
					sum.add(new Node(arr[i] + arr[j], arr[j], arr[i]));
					dif.add(new Node(arr[j] - arr[i], arr[j], arr[i]));
				}
			
			Collections.sort(sum, (a,b)->a.sum - b.sum);
			Collections.sort(dif, (a,b)->a.sum - b.sum);
			
			int max = Integer.MIN_VALUE;

			int idx1 = 0;
			int idx2 = 0;
			
			while(idx1<sum.size() && idx2<dif.size())
			{
				Node node1 = sum.get(idx1);
				Node node2 = dif.get(idx2);
				if(node1.sum < node2.sum)
					++idx1;
				else if(node1.sum > node2.sum)
					++idx2;
				else
				{
					if(node1.max != node2.max && node1.max != node2.min && 
							node1.min != node2.max && node1.min != node2.min)
					{
						max = Math.max(max, node2.max);
					}
					++idx2;
				}
				
			}
			
			if(max == Integer.MIN_VALUE)
				sb.append("no solution");
			else
				sb.append(max);
			
			sb.append('\n');
		}
		System.out.print(sb);
	}
}