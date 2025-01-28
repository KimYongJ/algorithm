//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4373
//1초 / 128MB
//두 배열에서 차이가 최소인 것을 찾는 문제 응용(유사문제 : 14746, 17095)
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
class Node implements Comparable<Node>{
	int sum, max, min;
	Node(int s, int a, int b){
		sum=s;
		max=a;
		min=b;
	}
	@Override
	public int compareTo(Node o) {
		return sum - o.sum;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int N = read();
			if(N == 0)
				break;
			
			int arr[] = new int[N];
			
			for(int i=0; i<N; i++)
				arr[i] = read();
			
			Arrays.sort(arr);
			
			ArrayList<Node> sum = new ArrayList<>();
			ArrayList<Node> dif = new ArrayList<>();
			
			for(int i=0; i<N-1; i++)
				for(int j=i+1; j<N; j++)
				{
					sum.add(new Node(arr[i] + arr[j], arr[j], arr[i]));
					dif.add(new Node(arr[j] - arr[i], arr[j], arr[i]));
				}
			
			Collections.sort(sum);
			Collections.sort(dif);
			
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
						max = Math.max(max, node2.max);
                    
					++idx1;
				}
				
			}
			
			if(max == Integer.MIN_VALUE)
				sb.append("no solution").append('\n');
			else
				sb.append(max).append('\n');
		}
		System.out.print(sb);
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}