// https://github.com/KimYongJ
import java.util.HashSet;
class Solution {
    private int[] parent; // 부모노드 저장할 배열
    
    public int solution(int n, int[][] computers) {
        parent = new int[n]; 
        for(int i=0; i<n; i++)
            parent[i] = i;// 부모노드를 자기자신으로 초기화
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(computers[i][j] == 1){
                    int Aparent = getParent(i); // 각 노드에 대해 부모노드를 가져온다
                    int Bparent = getParent(j); // 각 노드에 대해 부모노드를 가져온다
                    if(Aparent>Bparent){ // 부모노드의 값 비교 
                        for(int a=0; a<n; a++){
                            if(parent[a]==Aparent) // 부모노드 배열을 순회하면서 부모노드의 값이 Aparent인 것을 모두 Bparent로 변경하여 하나의 네트워크에 있도록한다.(논리적인 연결)
                                parent[a] = Bparent; 
                        }
                    }else{
                        for(int a=0; a<n; a++){
                            if(parent[a]==Bparent)
                                parent[a] = Aparent;
                        }
                    }
                }
            }
        }
        
        HashSet<Integer> set = new HashSet<>(); 
        for(int i=0; i<n; i++)
            set.add(parent[i]);// 부모노드의 숫자가 결국 네트워크의 갯수가 된다.
        
        return set.size();
    }
    public int getParent(int x){ // 부모노드를 가져오는 재귀 코드
        if(parent[x] == x) return x;
        return getParent(parent[x]);
    }
}