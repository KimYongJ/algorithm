//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1168
//0.15초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int tree[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder	sb = new StringBuilder("<");
		int N	= Integer.parseInt(st.nextToken());
		int K	= Integer.parseInt(st.nextToken());
		tree	= new int[N * 4];
		
		init(1, 1, N);
		
		for(int remain = N, idx = 0; remain >=1; remain--)
		{
			idx = (idx + K - 1) % remain;

			sb.append( query(1, 1, N, idx + 1) );
			
			if(remain != 1)
				sb.append(", ");
		}
		
		sb.append('>');
		
		System.out.print(sb);
	}
	public static int query(int treeNode, int s, int e, int cnt) {
		tree[treeNode] --;
		
		if(s == e)
			return s;
		
		int mid = (s + e) >> 1;
		int next= treeNode << 1;
		int idx = 0;
		
		if(tree[next] >= cnt)
			idx = query(next, s, mid,  cnt);
		else
			idx = query(next | 1, mid + 1, e, cnt - tree[next]);
		
		return idx;
	}
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = 1;

		int mid = (s + e) >> 1;
		
		return tree[treeNode] = init(treeNode << 1, s, mid) + 
									init(treeNode << 1 | 1, mid + 1, e);
	}
}