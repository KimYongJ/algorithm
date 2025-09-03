//https://www.acmicpc.net/problem/17140
//0.5초 512MB
//1 2 3 // R,C,K가 주어짐(1<=100)
//1 2 1 // 3*3크기에 들어있는 수가 주어짐(1<=100)
//2 1 3
//3 3 3
//A[R][C]의 값이 K가 되는 가장 짧은 시간 : 2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	
	static final int len = 100;
	static int xLen = 3;
	static int yLen = 3;
	static int R, C, K;
	static int map[][];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()) - 1;
		C = Integer.parseInt(st.nextToken()) - 1;
		K = Integer.parseInt(st.nextToken());
		map = new int[len + 1][len + 1];
		
		for(int y=0; y<yLen; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<xLen; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		System.out.print( solve() );
	}
	static int solve() {
		for(int time = 0; time<=len; time++)
		{
			if(map[R][C] == K)
				return time;
			
			if(yLen >= xLen)
				R();
			else
				C();
		}
		
		return -1;
	}
	static void R() {
		for(int y=0; y<yLen; y++)
		{
			int arr[] = order(map[y]);
			xLen = Math.max(xLen, arr.length);
			
			for(int x=0; x<arr.length; x++)map[y][x] = arr[x];
			for(int x=arr.length; x<=len; x++)map[y][x] = 0;
		}
	}
	static void C() {
		for(int x=0; x<xLen; x++)
		{
			int xrr[] = getXrr(x);
			int arr[] = order(xrr);
			yLen = Math.max(yLen, arr.length);
			
			for(int y=0; y<arr.length; y++)map[y][x] = arr[y];
			for(int y=arr.length; y<=len; y++)map[y][x] = 0;
		}
	}
	static int[] getXrr(int x) {
		int xrr[] = new int[yLen];
		
		for(int y = 0; y<xrr.length; y++)
			xrr[y] = map[y][x];
		
		return xrr;
	}
	static int[] order(int arr[]) {
		int counting[] = new int[len + 1];
		
		for(int i=0; i<arr.length; i++)
		{
			if(arr[i] == 0)continue;
			counting[arr[i]]++;
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i=1; i<=len; i++)
			if(counting[i] > 0)
				pq.add(new Node(counting[i], i));
		
		int result[] = new int[Math.min(100, pq.size()*2)];
		
		int i = 0;
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			result[i++] = now.num;
			result[i++] = now.cnt;
		}
		
		return result;
	}
	static class Node implements Comparable<Node>{
		int cnt;
		int num;
		Node(int c, int n){
			cnt = c;
			num = n;
		}
		@Override
		public int compareTo(Node o) {
			return cnt != o.cnt ? cnt - o.cnt : num - o.num;
		}
	}
}