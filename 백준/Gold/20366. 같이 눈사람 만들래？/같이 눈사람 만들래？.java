//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20366
//2초 / 1024MB
//요약 : 배열에서 2개씩 2번골라서 각각 합친 값의 차이가 최소인 값 출력 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class pair{int sum, i,j;pair(int sum, int i, int j){this.sum=sum; this.i=i; this.j=j;}}

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());// 4<=600
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// 지름(1<=십억)
		
		ArrayList<pair> list = new ArrayList<>();
		for(int i=0; i<N-1; i++)
			for(int j=i+1; j<N; j++)
				list.add(new pair(arr[i] + arr[j], i, j));
		
		Collections.sort(list,(a,b)->a.sum-b.sum);
		
		int min = Integer.MAX_VALUE;
		for(int i=1; i<list.size(); i++)
		{
			pair p1 = list.get(i-1);
			pair p2 = list.get(i);
			if(p1.i == p2.i || p1.i == p2.j || p1.j == p2.i || p1.j == p2.j)
				continue;
			min = Math.min(min, Math.abs(p1.sum-p2.sum));
		}
		System.out.print(min);
	}
}