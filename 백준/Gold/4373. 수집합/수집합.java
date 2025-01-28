//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4373
//1초 / 128MB
//두 배열에서 차이가 최소인 것을 찾는 문제 응용(유사문제 : 14746, 17095)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
class Node implements Comparable<Node>{
	int sum, max, min;
	Node(int s, int mx, int mn){
		sum=s; max=mx; min=mn;
	}
	@Override
	public int compareTo(Node o) {
		return sum - o.sum;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int N = Integer.parseInt(br.readLine());
			if(N == 0)
				break;
			int arr[] = new int[N];
			for(int i=0; i<N; i++)
				arr[i] = Integer.parseInt(br.readLine());
			
			Arrays.sort(arr);
			
			ArrayList<Node> sum = new ArrayList<>();
			ArrayList<Node> diff= new ArrayList<>();
			for(int i=0; i<N-1; i++)
				for(int j=i+1; j<N; j++)
				{
					sum.add(new Node(arr[i] + arr[j], arr[j], arr[i]));
					diff.add(new Node(arr[j] - arr[i], arr[j],arr[i]));
				}
			
			Collections.sort(sum);
			Collections.sort(diff);
			
			int idx1 = 0;
			int idx2 = 0;
			int max = Integer.MIN_VALUE;
			while(idx1<sum.size() && idx2<diff.size()) {
				Node s = sum.get(idx1);
				Node d = diff.get(idx2);
				if(s.sum <= d.sum)
					++idx1;
				else if(s.sum > d.sum)
					++idx2;
				
				if(s.sum == d.sum && s.max != d.max && s.max != d.min && s.min != d.max && s.min != d.min)
					max = Math.max(max, d.max);
			}
			
			if(max == Integer.MIN_VALUE)
				sb.append("no solution").append('\n');
			else
				sb.append(max).append('\n');
			
		}
		System.out.print(sb);
	}
}