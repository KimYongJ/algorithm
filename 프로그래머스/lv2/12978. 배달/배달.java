// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
import java.util.ArrayList;
class Node{
    int node, dist;
    Node(int node, int dist){
        this.node = node; // 인접 노드 번호
        this.dist = dist; // 거리 
    }
}
class Solution {
    public int solution(int N, int[][] road, int K) {
        
        int result = 1; // 1은 무조건 방문하기 때문에 기본 값은 1이다.
        
        ArrayList<ArrayList<Node>> list = new ArrayList<>();// 인접 노드 담을 리스트
        
        for(int i=0; i<N+1;i++) // 리스트 초기화
            list.add(new ArrayList<>());
        
        for(int[] r: road){
            list.get(r[0]).add(new Node(r[1],r[2])); // 인접 리스트 생성
            list.get(r[1]).add(new Node(r[0],r[2])); // 인접 리스트 생성
        }
        
        int[] dist = new int[N+1]; // 1로 부터의 거리를 담는 배열
        for(int i=2; i<N+1; i++){ // 거리 연산전 세팅
            dist[i] = Integer.MAX_VALUE;
        }
        
        ArrayDeque<Integer> q = new ArrayDeque<>();// 인접 노드들을 차례로 담을 큐 선언
        q.add(1); // 초기값 1을 큐에 넣는다.
        
        while(!q.isEmpty()){
            
            int node = q.poll(); // 노드를 꺼낸다
            
            ArrayList<Node> ad_List = list.get(node); // 해당 노드의 인접 리스트를 가져온다.
            
            for(int i=0; i<ad_List.size(); i++){ // 인접 리스트 수만큼 반복
                
                Node ad_Node = ad_List.get(i); // 인접 노드를 하나 꺼낸다
                
                if(dist[ad_Node.node] > dist[node] + ad_Node.dist){ //  거리가 갱신될 수 있는 모든 상황에 대해서만 큐에 해당 노드를 넣는다.그래야 dist에 최단거리만 담기게 된다.
                    q.add(ad_Node.node); // 큐에 해당 노드를 담아 다른 인접리스트 탐색 토록함
                    dist[ad_Node.node] = dist[node] + ad_Node.dist;
                }
            }
        }
        
        for(int i=2; i<N+1; i++){ // 결과 추출
            if(dist[i]<=K)
                result++;
        }
        
        return result;
    }
}