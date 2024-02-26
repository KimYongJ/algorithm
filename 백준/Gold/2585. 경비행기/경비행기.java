// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Node
{
	int idx, k;
	Node(int idx, int k)
	{
		this.idx = idx;
		this.k = k;
	}
}
class Main
{
	
	static int N, K, fvalue, endIdx, point[][];
	static int mid = 14_142;
	static boolean visit[];
	static ArrayDeque<Node> q;
	public static int getFuel(int idx1, int idx2) 
	{
		return (int)(Math.ceil(Math.sqrt(Math.pow(point[idx1][0]-point[idx2][0], 2) + Math.pow(point[idx1][1]-point[idx2][1], 2))/10.0));
	}
	public static boolean BFS() {
		visit	= new boolean[N];
		q 		= new ArrayDeque<>();
		q.add(new Node(0,0));
		
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
				
			if(getFuel(now.idx,endIdx) <= mid) 
				return true;
			
			if(now.k == K) 
				continue;
			
			for(int i=1; i<N; i++) 
			{
				if(!visit[i] && getFuel(i,now.idx) <= mid)
				{
					q.add(new Node(i, now.k+1));
					visit[i] = true;
				}
			}
			
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		N 					= Integer.parseInt(st.nextToken())+2;
		K 					= Integer.parseInt(st.nextToken());
		endIdx				= N-1;
		point 				= new int[N][2];
		point[0][0] 		= point[0][1] 		= 0; 		// 초기좌표 셋팅
		point[endIdx][0] 	= point[endIdx][1] 	= 10000; 	// 마지막 좌표 셋팅
		for(int i=1; i<endIdx; i++) 
		{
			st = new StringTokenizer(br.readLine());
			point[i][0] = Integer.parseInt(st.nextToken());
			point[i][1] = Integer.parseInt(st.nextToken());
		}
		int left = 0, right = getFuel(0, endIdx);
		while(left < right) {
			mid = (right+left) / 2;
			if( BFS() )
				right = mid;
			else 	
				left = mid+1;
		}
		
		System.out.println(left);
		
	}
	
}