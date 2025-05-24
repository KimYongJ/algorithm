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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final int MAX = 200_000;
	static int N, Q;
	static int ans[];
	static int root[];// 영속성 세그먼트 트리의 버전을 담을 배열
	static Result dummy;
	static List<Node> nodes;// 영속성 세그먼트 트리의 노드들
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		root = new int[N + 1];
		dummy = new Result(0, 0);
		nodes = new ArrayList<>();
		
		root[0] = init(1, MAX);// 영속성 세그먼트 트리 초기값 세팅
		
		// 영속성 세그먼트 트리를 값 입력과 동시에 업데이트 해나간다. 입력 하나당 버전이 1나씩 늘어난다.
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			root[i] = update(root[i - 1], 1, MAX, Integer.parseInt(st.nextToken()));

		Q = Integer.parseInt(br.readLine());
		for(int i=1; i<=Q; i++)
		{
			//(1 ≤ L ≤ R ≤ N / 1 ≤ A ≤ B ≤ 200,000 / 1 ≤ S ≤ 200,000)
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());// 사람번호범위L
			int R = Integer.parseInt(st.nextToken());// 사람번호범위R
			int startScore = Integer.parseInt(st.nextToken());// 점수범위A
			int endScore = Integer.parseInt(st.nextToken());// 점수범위B
			long S = Integer.parseInt(st.nextToken());// 원하는 최소 평균 점수S
			
			Result res;

			int s = startScore;
			int e = endScore;
			int cnt1 = 0;
			int cnt2 = 0;
			int midScore = 0;
			long sum = 0;
			while(s <= e)
			{
				int mid = (s + e) >> 1;
				// 사람 범위중, 점수 제한을 두어  평균 S보다 크거나 같은 가장 작은 평균을 찾는다.
				res = query(root[L - 1], root[R], 1, MAX, mid, endScore);
				
				if(res.sum >= S * res.cnt)
				{
					cnt1 = res.cnt;
					sum = res.sum;
					e = mid - 1;
					midScore = mid;
				}
				else
					s = mid + 1;
			}
			
			if(midScore > startScore)
			{
				midScore -= 1;
				
				res = query(root[L - 1], root[R], 1, MAX, midScore, midScore);
			
				long traget = sum - S * cnt1;
				
				cnt2 = (int)Math.min(res.cnt, traget / (S - midScore));
			}
			
			
			sb.append(cnt1 + cnt2).append('\n');
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
}