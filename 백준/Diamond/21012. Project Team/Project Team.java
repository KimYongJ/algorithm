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
	static int root[];
	static Result dummy;
	static List<Node> nodes;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());// // 사람수 1<=200,000
		root = new int[N + 1];// 업데이트시마다 루트 노드번호(버전)를 담을 배열
		dummy = new Result(0,0);
		nodes = new ArrayList<>();// 세그먼트 트리의 노드들을 담을 리스트
		root[0] = init(1, MAX);// 초기 값 세팅 및 초기 루트노드번호 세팅
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)// 각사람당 점수 1<=200,000
			root[i] = update(root[i-1], 1, MAX, Integer.parseInt(st.nextToken()));
		
		Q = Integer.parseInt(br.readLine());// 질의 수 1<=200,000
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			//(1 ≤ L ≤ R ≤ N / 1 ≤ A ≤ B ≤ 200,000 / 1 ≤ S ≤ 200,000)
			int L = Integer.parseInt(st.nextToken());// 사람번호범위L
			int R = Integer.parseInt(st.nextToken());// 사람번호범위R
			int A = Integer.parseInt(st.nextToken());// 점수범위A
			int B = Integer.parseInt(st.nextToken());// 점수범위B
			long S = Integer.parseInt(st.nextToken());// 원하는 최소 평균 점수S
			
			int startScore = A;
			int endScore = B;
			int cnt = 0;
			int midScore = 0;
			long sum = 0;

			while(startScore <= endScore)
			{
				int mid = (startScore + endScore) >> 1;
				// 오른쪽, 왼쪽-1 번째 사람 을 구해와 둘을 
				Result res = query(root[L - 1], root[R], 1, MAX, mid, B);
				
				if(res.sum >= S * res.cnt)
				{
					endScore = mid - 1;
					cnt = res.cnt;
					midScore = mid;
					sum = res.sum;
				}
				else
					startScore = mid + 1;
			}
			// 위에서 구한 cnt 값이 아래 반례 때문에 최선이 아닐 수 있다. 그러므로 mid까지 구한것에서 -1을 해서 그 값에 대해서 추가 가능한지 마지막으로 한번더 탐색해주어야 한다.
//			3
//			10 6 6
//			1
//			1 3 1 10 8
//			답 : 2 이지만, 위 코드만으로는 1이나옴, 그리디하게 midSocre에 -1값을 통해 보정해주어야 함
			if(midScore > A)// 구한 midScore가 A점수 초과일 경우만 연산
			{
				midScore -= 1;
				
				Result res = query(root[L - 1], root[R], 1, MAX, midScore, midScore);
				
				long spare = sum - S * cnt;// 평균을 무너뜨리지 않고 추가할 수 있는 여유 값
				long need = S - midScore;// midScore가 평균이 되기 위해 필요한 값
				// 최종 결과는 midScore의 개수와 평균이 무너지지 않고 만들 수 있는 개수 중 최솟값을 더함
				cnt += Math.min(res.cnt, spare / need);
			}
			
			sb.append(cnt).append('\n');
		}
		
		System.out.print(sb);
	}
	static Result query(int treeNode1, int treeNode2, int s, int e, int left, int right) {
		if(e < left || right < s)
			return dummy;
		
		Node n1 = nodes.get(treeNode1);
		Node n2 = nodes.get(treeNode2);
		
		if(left <= s && e <= right)
			return new Result(
					n2.cnt - n1.cnt,
					n2.sum - n1.sum
				);
		
		int mid = (s + e) >> 1;
		
		Result L = query(n1.left, n2.left, s, mid, left, right);
		Result R = query(n1.right, n2.right, mid + 1, e, left, right);
		
		return L.add(R);
	}
	static int update(int treeNode, int s, int e, int target) {
		// 노드 카피 후 연산 진행
		Node now = copy( nodes.get(treeNode) );
		// 타겟으로 내려갈 때 값 업데이트, 내려갔다 올라오면서 합치는 것은 코드가 길어져서 내려갈 때업데이트로 그냥 끝냄
		now.cnt += 1;
		now.sum += target;
		// 리프노드가 아닌 경우 
		if(s != e)
		{
			int mid = (s + e) >> 1;
			if(target <= mid)// 목표 값이 왼쪽에 있으면 왼쪽으로 탐색
				now.left = update(now.left, s, mid, target);
			else// 목표 값이 오른쪽이면 오른쪽 탐색
				now.right = update(now.right, mid + 1, e, target);
		}
		// 노드 삽입 후 사이즈 반환
		nodes.add(now);
		
		return nodes.size() - 1;
	}
	static int init(int s, int e) {
		Node node = new Node(0,0,0,0);// 노드 생성
		// 리프 노드가 아닌 경우 
		if(s != e)
		{
			// 왼쪽 오른쪽 탐색하여 인덱스 저장
			int mid = (s + e) >> 1;
			node.left = init(s, mid);
			node.right = init(mid + 1, e);
		}
		// 노드 삽입 후 노드 사이즈 반환
		nodes.add(node);
		return nodes.size() - 1;
	}
	static Node copy(Node o) {
		return new Node(o.left, o.right, o.cnt, o.sum);
	}
	static class Node{
		int left, right, cnt;
		long sum;
		Node(int l, int r, int c, long s){
			left = l;
			right = r;
			cnt = c;
			sum = s;
		}
	}
	static class Result{
		int cnt;
		long sum;
		Result(int c, long s){
			cnt = c;
			sum = s;
		}
		Result add(Result o){
			return new Result(
					this.cnt + o.cnt,
					this.sum + o.sum
					);
		}
	}
}
