//https://www.acmicpc.net/problem/21918
//1초 512MB

import java.util.Arrays;

class Main{
	
	static int N, M;
	static int[] tree, lazy;
	static boolean[] isXor;
	
	public static void main(String[] args)throws Exception{

		N = read();
		M = read();
		tree = new int[N * 4];
		lazy = new int[N * 4];
		isXor = new boolean[N * 4];
		
		Arrays.fill(lazy, -1);
		
		for(int i=1; i<=N; i++)
			updateRange(1, 1, N, i, i, read(), false);
		
		while(M-->0)
		{
			int i = read();
			int l = read();
			int r = read();
			
			if(i == 1)		updateRange(1, 1, N, l, l, r, false);
			else if(i == 2)	updateRange(1, 1, N, l, r, 0, true);
			else if(i == 3)	updateRange(1, 1, N, l, r, 0, false);
			else if(i == 4)	updateRange(1, 1, N, l, r, 1, false);
		}
		StringBuilder sb = new StringBuilder();

		for(int i=1; i<=N; i++)
			sb.append(query(1, 1, N, i)).append(' ');
		
		System.out.print(sb);
	}
	static void lazy(int treeNode, int s, int e) {
		if(lazy[treeNode] == -1 && !isXor[treeNode])
			return;
	
		if(s == e)
		{
			if(lazy[treeNode] != -1) // 다이렉트 저장이 있다면 저장
				tree[treeNode] = lazy[treeNode];
			
			if(isXor[treeNode])// xor도 있다면 xor 처리를 해줌
				tree[treeNode] ^= 1;
		}
		else
		{
			int left = treeNode << 1;
			int right = treeNode << 1 | 1;
			
			if(lazy[treeNode] != -1)
			{
				lazy[left] = lazy[treeNode];
				lazy[right] = lazy[treeNode];
				isXor[left] = false;
				isXor[right] = false;
			}
			
			if(isXor[treeNode])
			{
				if(lazy[left] != -1)
					lazy[left] ^= 1;
				else
					isXor[left] = !isXor[left];
				if(lazy[right] != -1)
					lazy[right] ^= 1;
				else
					isXor[right] = !isXor[right];
			}
		}
		lazy[treeNode] = -1;
		isXor[treeNode] = false;
		
	}
	static void updateRange(int treeNode, int s, int e, int left, int right, int val, boolean xor) {
		
		lazy(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		
		if(left <= s && e <= right)
		{
			if(xor)// 반전일 경우
			{
				if(lazy[treeNode] != -1)// lazy에 값이 있다면 lazy값을 변경함
					lazy[treeNode] ^= 1;
				else
					isXor[treeNode] = !isXor[treeNode];// 아닌경우 xor을 저장
			}
			else
			{
				lazy[treeNode] = val;// 다이렉트 저장은 값을 그대로 대입
				isXor[treeNode] = false;// xor은 false로 초기화
			}
			
			lazy(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		updateRange(treeNode << 1, s, mid, left, right, val, xor);
		updateRange(treeNode << 1 | 1, mid + 1, e, left, right, val, xor);
	}
	static int query(int treeNode, int s, int e, int targetIdx) {
		lazy(treeNode, s, e);
		
		if(e<targetIdx || targetIdx < s)
			return -1;
		
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		if(targetIdx <= mid)
			return query(treeNode << 1, s, mid, targetIdx);
		
		return query(treeNode << 1 | 1, mid + 1, e, targetIdx);
	}
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}