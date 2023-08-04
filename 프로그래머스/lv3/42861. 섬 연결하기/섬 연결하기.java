import java.util.Arrays;
class Solution {
    static int[] parent;// 부모 노드를 담을 배열선언, index가 노드의 숫자이고, 안의 value가 부모노드 값이다.
    public int solution(int n, int[][] costs) {
        int result = 0;
        parent = new int[n]; // 부모 노드를 담을 배열 초기화, 섬이n개 이므로 n으로 초기화
        for(int i=0; i<n; i++)
            parent[i] = i;// 초기값을 자기 자신으로 초기화한다.
        Arrays.sort(costs, (a,b)-> a[2]-b[2]); // 가중치가 작은순에서 높은순으로 오름차순 정렬한다.
        
        for(int[] x : costs){
            int aParent = getParent(x[0]);
            int bParent = getParent(x[1]);
            
            if(aParent==bParent) continue; // 두 부모노드의 값이 같다면 이미 연결된 것이므로 pass
            
            parent[aParent] = bParent; // parent[aParent]의 값은 aParent 자신일 것이다. (부모이기 때문에)
                                    // 부모를 bParent로 만들어 버림으로 서로 연결하는 효과가 있다.
            result += x[2];
            
        }
        return result;
    }
    public int getParent(int x){// x의 부모노드를 찾는 함수로 재귀함수로 되어있다.
        if(parent[x]==x) return x;
        return getParent(parent[x]);
    }
}