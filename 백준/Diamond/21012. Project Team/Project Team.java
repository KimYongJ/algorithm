//https://www.acmicpc.net/problem/21012
//5초 512MB
//6 // 사람수 1<=200,000
//1 2 3 4 5 6 // 각사람당 점수 1<=200,000
//4 // 질의 수 1<=200,000
//1 6 1 6 1 // 사람번호범위L, 사람번호범위R, 점수범위A, 점수범위B, 원하는 최소 평균 점수S(1 ≤ L ≤ R ≤ N / 1 ≤ A ≤ B ≤ 200,000 / 1 ≤ S ≤ 200,000)
//1 6 1 6 5
//1 6 5 6 5
//1 3 4 5 1
//목표 : L부터 R 사이의 번호를 가지며, 점수가 A부터 B 사이인 사람들 중에 평균 잠재력 점수가 최소 S 이상이 되도록 최대한 많은 사람을 선택해서 그 사람수 출력
//6
//3
//2
//0

import java.util.ArrayList;
import java.util.List;

class Main{
	
	static final int MAX = 200_000;
	static int N, Q;
	static int root[];// 영속성 세그먼트 트리의 버전을 담을 배열
	static Result dummy;
	static List<Node> nodes;// 영속성 세그먼트 트리의 노드들
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		Reader in = new Reader();
		
		N = in.nextInt();
		root = new int[N + 1];
		dummy = new Result(0, 0);
		nodes = new ArrayList<>();
		
		root[0] = init(1, MAX);// 영속성 세그먼트 트리 초기값 세팅
		
		// 영속성 세그먼트 트리를 값 입력과 동시에 업데이트 해나간다. 입력 하나당 버전이 1나씩 늘어난다.
		for(int i=1; i<=N; i++)
			root[i] = update(root[i - 1], 1, MAX, in.nextInt());

		Q = in.nextInt();
		for(int i=1; i<=Q; i++)
		{
			//(1 ≤ L ≤ R ≤ N / 1 ≤ A ≤ B ≤ 200,000 / 1 ≤ S ≤ 200,000)
			int L = in.nextInt();// 사람번호범위L
			int R = in.nextInt();// 사람번호범위R
			int startScore = in.nextInt();// 점수범위A
			int endScore = in.nextInt();// 점수범위B
			long S = in.nextInt();// 원하는 최소 평균 점수S
			
			Result res;

			int s = startScore;
			int e = endScore;
			int cnt = 0;
			int midScore = 0;
			long sum = 0;
			while(s <= e)
			{
				int mid = (s + e) >> 1;
				// 사람 범위 중, 점수 제한을 두어 S보다 크거나 같은 가장 작은 평균을 찾는다.
				res = query(root[L - 1], root[R], 1, MAX, mid, endScore);
				
				if(res.sum >= S * res.cnt)
				{
					cnt = res.cnt;
					sum = res.sum;
					e = mid - 1;
					midScore = mid;
				}
				else
					s = mid + 1;
			}
			// 위에서 구한 값이 아래 반례 때문에 최선이 아닐 수 있다. 그러므로 mid까지 구한것에서 -1을 해서 그 값에 대해서 추가 가능한지 마지막으로 한번더 탐색해주어야 한다.
//			3
//			10 6 6
//			1
//			1 3 1 10 8
//			답 : 2 이지만, 위 코드만으로는 1이나옴, 그리디하게 midSocre에 -1값을 통해 보정해주어야 함

			midScore -= 1;// 이전 이분 탐색에서 최저로 구한 mid값에서 -1을 해준다.
			
			if(midScore >= startScore)
			{
				// 이전 값에 대해 추가되지 못한 부분이 있는지 확인하는 부분들..
				res = query(root[L - 1], root[R], 1, MAX, midScore, midScore);
			
				long spare = sum - S * cnt; // 다른 곳에 추가해 줄 수 있는 여유 분
				long need = S - midScore; // midScore가 평균이 되기 위해 추가적으로 필요한 값 
				
				// 최종 값은 cnt와, 추가 할 수 있는 여유 분 나누기, midSocre가 평균이 되기 위해 필요한 값을 하여 둘 중 작은 것이 답이된다.
				cnt += Math.min(res.cnt, spare / need);
			}
			
			sb.append(cnt).append('\n');
		}
		
		System.out.print(sb);
	}
	static Result query(int root1, int root2, int s, int e, int left, int right)
	{
		if(e < left || right < s)
			return dummy;
		
		Node r1 = nodes.get(root1);
		Node r2 = nodes.get(root2);
		
		if(left <= s && e <= right)
			return new Result(r2.cnt - r1.cnt , r2.score - r1.score);
		
		int mid = (s + e) >> 1;
		
		Result leftRes = query(r1.left, r2.left, s, mid, left, right);
		Result rightRes = query(r1.right, r2.right, mid + 1, e, left, right);
		
		return leftRes.add(rightRes);
	}
	static int update(int treeNode, int s, int e, int targetValue) {
		Node nowNode = copy( nodes.get(treeNode) );// 업데이트마다 노드를 복사해서 늘린다.
		// 빠른 업데이트를 위해 내려갈 때마다 값을 미리 더함
		nowNode.cnt += 1;
		nowNode.score += targetValue;
		
		if(s != e)
		{		
			int mid = (s + e) >> 1;
			
			if(targetValue <= mid)
				nowNode.left = update(nowNode.left, s, mid, targetValue);
			else
				nowNode.right = update(nowNode.right, mid + 1, e, targetValue);
		}
		
		nodes.add(nowNode);
		
		return nodes.size() - 1;
	}
	static int init(int s, int e)
	{
		Node node = new Node(0,0,0,0);
		
		if(s != e)
		{
			int mid = (s + e) >> 1;
			
			node.left = init(s, mid);
			node.right= init(mid + 1, e);
		}
		
		nodes.add(node);
		
		return nodes.size() - 1;
	}
	static Node copy(Node prev) {
		return new Node(
						prev.left,
						prev.right,
						prev.cnt,
						prev.score
				);
	}
	static class Result{
		int cnt;
		long sum;
		
		Result(int c, long s){
			cnt = c;
			sum = s;
		}
		Result add(Result o) {
			return new Result(
					this.cnt + o.cnt,
					this.sum + o.sum
					);
		}
	}
	static class Node{
		int left;
		int right;
		int cnt;
		long score;
		Node(int l, int r, int c, long s){
			left = l;
			right = r;
			cnt = c;
			score = s;
		}
	}
	static class Reader {
	    final int SIZE = 1 << 13;
	    byte[] buffer = new byte[SIZE];
	    int index, size;
	
	    int nextInt() throws Exception {
	        int n = 0;
	        byte c;
	        boolean isMinus = false;
	        while ((c = read()) <= 32) { if (size < 0) return -1; }
	        if (c == 45) { c = read(); isMinus = true; }
	        do n = (n << 3) + (n << 1) + (c & 15);
	        while (isNumber(c = read()));
	        return isMinus ? ~n + 1 : n;
	    }
	
	    boolean isNumber(byte c) {
	        return 47 < c && c < 58;
	    }
	
	
	    byte read() throws Exception {
	        if (index == size) {
	            size = System.in.read(buffer, index = 0, SIZE);
	            if (size < 0) buffer[0] = -1;
	        }
	        return buffer[index++];
	    }
	}
}