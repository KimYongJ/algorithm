// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
import java.util.ArrayList;
class Solution {
    public int solution(int N, int[][] road, int K) {
        
        int result = 1; // 1은 무조건 방문하기 때문에 기본 값은 1이다.
        
        int[][] weight = new int[N+1][N+1];// 가중치를 담을 배열
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();// 인접 노드 담을 리스트
        
        for(int i=0; i<N+1;i++) // 리스트 초기화
            list.add(new ArrayList<>());
        
        for(int[] r: road){
            if(weight[r[0]][r[1]] !=0){ // 같은 노드들의 가중치가 여러개 주어질 수 있으므로 해당 로직 생성
                weight[r[0]][r[1]] = weight[r[1]][r[0]] = Math.min(r[2],weight[r[0]][r[1]]);// 가중치 세팅
            }else{
                weight[r[0]][r[1]] =  weight[r[1]][r[0]] = r[2];// 가중치 세팅
            }
            list.get(r[0]).add(r[1]); // 인접 리스트 생성
            list.get(r[1]).add(r[0]); // 인접 리스트 생성
        }
        
        int[] dist = new int[N+1]; // 1로 부터의 거리를 담는 배열
        for(int i=2; i<N+1; i++){ // 거리 연산전 세팅
            dist[i] = Integer.MAX_VALUE;
        }
        
        ArrayDeque<Integer> q = new ArrayDeque<>();// 인접 노드들을 차례로 담을 큐 선언
        q.add(1); // 초기값 1을 큐에 넣는다.
        
        while(!q.isEmpty()){
            
            int node = q.poll(); // 노드를 꺼낸다
            
            ArrayList<Integer> ad_List = list.get(node); // 해당 노드의 인접 리스트를 가져온다.
            
            for(int i=0; i<ad_List.size(); i++){ // 인접 리스트 수만큼 반복
                
                int ad_Node = ad_List.get(i); // 인접 노드를 하나 꺼낸다
                
                if(dist[ad_Node] > dist[node] + weight[node][ad_Node]){ //  거리가 갱신될 수 있는 모든 상황에 대해서만 큐에 해당 노드를 넣는다.그래야 dist에 최단거리만 담기게 된다.
                    q.add(ad_Node); // 큐에 해당 노드를 담아 다른 인접리스트 탐색 토록함
                    dist[ad_Node] = dist[node] + weight[node][ad_Node];
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