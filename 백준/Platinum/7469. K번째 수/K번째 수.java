//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7469
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class Node {
    int l, r, sum; // 세그먼트트리의 각 노드, 구간 합을 저장
    
    Node() {
        this.l = -1;
        this.r = -1;
        this.sum = 0;
    }
    
    Node(int sum) {
        this.l = -1;
        this.r = -1;
        this.sum = sum;
    }
    
    Node(int l, int r, int sum) {
        this.l = l;
        this.r = r;
        this.sum = sum;
    }
}

class IdxAndValue implements Comparable<IdxAndValue>{
	int idx, value;
	IdxAndValue(int i, int v){
		idx=i; value =v;
	}
	@Override
	public int compareTo(IdxAndValue o) {
		
		if(value != o.value)
			return value - o.value;
		
		return idx - o.idx;
	}
}

public class Main {
	static Scanner sc = new Scanner(System.in);
    static List<Node> nodes = new ArrayList<>();	// 세그먼트트리의 모든 노드를 저장하는 리스트
    static List<Integer> list;						// 초기에 배열에 입력되는 숫자들의 중복제거 + 오름차순된 리스트 
    // 세그먼트트리 초기화하는 함수
    static int init(int L, int R) {
        if (L == R)
        {
            nodes.add(new Node(0));// 리프 노드일 경우 구간합을 0으로 세팅, 왼쪽 오른쪽 자식노드는 -1로 세팅
        }
        else
        {
        	// 트리를 분할하여 왼쪽과 오른쪽 자식노드를 생성
            int mid = (L + R) / 2;
            // 왼쪽 자식노드는 L~mid까지, 오른쪽 자식노드는 mid + 1, R 까지 자식노드의 노드번호를 후위탐색으로 저장
            nodes.add(new Node(init(L, mid), init(mid + 1, R), 0));
        }
        // 자기의 노드번호를 반환
        return nodes.size() - 1;
    }
    // 세그먼트 트리의 특정 구간의 값을 업데이트하는 함수
    // node를 새롭게 추가하는데, 리프노드가 먼저 추가되고, 그 후 거기까지 오면서 지나친 노드들이 nodes에 계속 추가된다.
    // 리프노드일 때는 현재 노드의 + val만큼의 데이터만 넣고, 리프가 아닌경우 왼쪽 탐색은 왼쪽 업데이트노드만변경, 오른쪽탐색은
    // 오른쪽 노드만 변경, nodeNum은 전달할 때 왼쪽은 왼쪽노드번호, 오른쪽은 오른쪽 노드번호를 내린다.
    static int update(int i, int val, int L, int R, int nodeNum) {

        Node cur = nodes.get(nodeNum);// 해당하는 노드를 빼옴

        if (L == R)
        {
        	// 리프노드일 경우 현재 노드의 누적합에 val을 더해준다.
            nodes.add(new Node(cur.sum + val));
        }
        else
        {
        	// 리프노드가 아닌경우, 기준이되는 인덱스 i를 기준으로 왼쪽 오른쪽을 정해서 탐색한다.
            int mid = (L + R) / 2;
            if (i <= mid)
            {
            	// 기준되는 인덱스i가 mid보다 작거나 같으면 왼쪽탐색
                int l = update(i, val, L, mid, cur.l);
                nodes.add(new Node(l, cur.r, cur.sum + val));
            }
            else// 기준되는 인덱스i가 mid보다 크면 오른쪽 탐색
            {
            	// 추가한 노드의 인덱스 번호를 담은 node를 새롭게 생성
                int r = update(i, val, mid + 1, R, cur.r);
                nodes.add(new Node(cur.l, r, cur.sum + val));
            }
        }
        // 추가된 노드의 인덱스 반환
        return nodes.size() - 1;
    }
    // 특정 구간 L, R의 합을 구하는 함수
    static int sum(int L, int R, int nodeNum, int nodeL, int nodeR) {
    	
        Node cur = nodes.get(nodeNum);
        // 구간이 트리와 겹치지 않으면 0을 반환
        if (R < nodeL || nodeR < L)
        	return 0;
        // 구간이 완전 포함되면 sum을반환
        if (L <= nodeL && nodeR <= R)
        	return cur.sum;
        
        // 부분적으로 겹치면 왼쪽 오른쪽을 재귀적으로 탐색하며 sum을 구함
        int mid = (nodeL + nodeR) / 2;
        
        return sum(L, R, cur.l, nodeL, mid) + sum(L, R, cur.r, mid + 1, nodeR);
    }
    public static void main(String[] args) {
        int n = sc.nextInt();// 배열의 크기N(1<=십만)
        int q = sc.nextInt();// 함수 Q(1<=오천)
        List<Integer> roots = new ArrayList<>();	// 각 쿼리마다 세그먼트 트리의 루트노드를 저장하는 리스트
    	List<IdxAndValue> idxAndValue = new ArrayList<>();// 좌표압축ㅇ글 저장할 리스트
    	Set<Integer> set = new HashSet<>();
    	int arr[] = new int[n];
    	
    	for (int i = 0; i < n; i++) {
            set.add(arr[i] = sc.nextInt());// 배열 초기값, 절대값 십억이하
    	}
    	
    	list = new ArrayList<>(set);// 배열의 값만 저장하는 리스트
    	
    	Collections.sort(list);// 초기값으로 입력된 숫자들의 중복을 제거하고 오름차순정렬
    	
    	for (int i = 0; i < n; i++)
        	// 초기값으로 입력된 숫자들을 순서대로 돌면서, 그 숫자가 몇번째 큰수인지 저장
        	idxAndValue.add(new IdxAndValue(i, Collections.binarySearch(list, arr[i])));
        
        Collections.sort(idxAndValue);
       
        // 초기 루트번호 저장
        roots.add(init(0, n));
        
        for (int v = 0, idx = 0; v <= n; v++)
        {
            while (idx < n && idxAndValue.get(idx).value == v)
            {
            	int nodeSize = update(idxAndValue.get(idx).idx, 1, 0, n, roots.get(roots.size() - 1));
            	
                roots.set(roots.size() - 1, nodeSize);// 현재 버전 업데이트
                
                ++idx;
            }
            roots.add(roots.get(roots.size() - 1));// 다음 버전을 위해 복사
        }
        
        while (q-- > 0)
        {
            int i = sc.nextInt() - 1;	// i범위
            int j = sc.nextInt() - 1;	// j범위
            int k = sc.nextInt();		// k번째 수 출력
            
            int left = 0,right = n;
            int result = 0;
            while (left <= right)
            {
                int mid = (left + right) / 2;
                
                if (sum(i, j, roots.get(mid), 0, n) < k)
                	left = mid + 1;
                else
                {
                	result = mid;
                	right = mid - 1;
                }
            }
            System.out.println(list.get(result));
        }
        sc.close();
    }
}
