// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Solution {
    
    private int result = Integer.MAX_VALUE;
    private int[] parent;
    
    public int solution(int n, int[][] wires) {
        // wires에서 1개를 제외하고 송전탑 연결 탐색
        for(int i=0; i<n-1; i++){           // 완전 탐색을 위한 2중 for문
            setParent(n);                   // 부모노드를 담을 배열 초기화 함수
            for(int j=0; j<n-1; j++){       // 완전 탐색을 위한 2중 for문
                if(i==j){
                    continue;
                }else{
                    int aParent = getParent(wires[j][0]); // 부모노드를 불러옴
                    int bParent = getParent(wires[j][1]); // 부모노드를 불러옴
                    
                    if(aParent > bParent){
                        change(aParent,bParent);// parent배열에서 값이 aParent인 것을 모두 bParent로 바꾼다.
                    }else{
                        change(bParent,aParent);// parent배열에서 값이 bParent인 것을 모두 aParent로 바꾼다.
                    }
                    
                }
            }
            // 이하 네트워크가 2개인지 확인하는 코드
            int[] counting = new int[n+1];
            for(int x=1; x<n+1; x++){
                counting[parent[x]]++; // 부모노드의 갯수 카운팅 정렬
            }
            ArrayList<Integer> list = new ArrayList<>(); // 부모노드를 담는다(내부 값은 자식노드의 갯수)
            for(int cnt : counting){
                if(cnt!=0){
                    list.add(cnt); // list에 담긴 것은 같은 부모노드의 갯수들
                }
            }
            if(list.size()==2){ // 노드들이 2개로 나누어져있다면 아래 코드실행
                result = Math.min(result,Math.abs(list.get(0)-list.get(1)));
            }
            // 이하 네트워크가 2개인지 확인하는 코드 version 2(hashMap사용법)
            // HashMap<Integer,Integer> hm = new HashMap<>(); // 부모노드가 2개인 것만 추려내기 위해 hashmap사용
            // for(int x=1; x<n+1; x++){
            //     int pNode = parent[x];
            //     hm.put(pNode,hm.getOrDefault(pNode,0)+1); // 부모노드의 
            // }
            // if(hm.size()==2){
            //     int[] arr = new int[2];
            //     int idx = 0;
            //     for(Map.Entry<Integer,Integer> entry : hm.entrySet()){
            //         arr[idx++] = entry.getValue();
            //     }
            //     result = Math.min(result,Math.abs(arr[0]-arr[1]));
            // }
        }
        
        return result;
    }
    public void change(int before,int after){
        for(int i=0;i <parent.length; i++){
            if(parent[i]==before){
                parent[i] = after;
            }
        }
    }
    public void setParent(int n){ // 부모노드를 담을 배열 초기화 함수
        parent = new int[n+1];
        for(int i=1; i<=n; i++)
            parent[i] = i;
    }
    public int getParent(int x){ // 부모 노드의 값을 가져오는 것
        if(parent[x]==x) return x;
        return getParent(parent[x]);
    }
    
}