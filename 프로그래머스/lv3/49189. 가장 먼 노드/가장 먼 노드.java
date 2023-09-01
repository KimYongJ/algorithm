import java.util.ArrayList;
import java.util.ArrayDeque;
class Solution {
    private int result = 0;   
    public int solution(int n, int[][] edge) {
        ArrayList<ArrayList<Integer>> adlist = new ArrayList<>();// 인접리스트 구현을 위한 list 배열 선언
        boolean[] visit = new boolean[n+1]; // 방문했는지 여부 체크
        int[] dist = new int[n+1]; // 1로부터 각 노드까지의 거리를 담을 배열
        int maxDist = 0;
        for(int i=0;i<n+1; i++)
            adlist.add(new ArrayList<>()); // 리스트 배열 초기화
        
        for(int[] node : edge){
            int aNode = node[0];
            int bNode = node[1];
            adlist.get(aNode).add(bNode);// 해당 노드에 인접 노드를 add한다.
            adlist.get(bNode).add(aNode);// 해당 노드에 인접 노드를 add한다.
        }
        ArrayDeque<Integer> q = new ArrayDeque<>(); // BFS진행할 큐 선언
        q.add(1); // 큐에 1을 넣어줌
        visit[1] = true; // 1방문 처리
        while(!q.isEmpty()){ // 큐가 빌 때까지 반복
            int nowNode = q.poll(); // 큐에서 node값을 가져온다.
            ArrayList<Integer> nodeList = adlist.get(nowNode); // 가져온 node값으로 인접 list를 가져온다.
            for(int i=0; i<nodeList.size(); i++){ 
                int node = nodeList.get(i); // 인접리스트 안에 노드들을 하나씩 가져온다.
                if(!visit[node]){ // 방금 꺼낸 노드를 기존에 방문한 적이 없다면 실행
                    visit[node] = true; // 방문처리 
                    q.add(node);    // 큐에 노드를 담에 해당 노드에 대해 BFS를 싱행하도록 한다.
                    dist[node] = dist[nowNode] +1; // 꺼낸 노드의 거리를 현재노드의 거리 +1로 세팅한다
                    maxDist = maxDist<dist[node] ? dist[node] : maxDist; // 최대 거리를 알기 위해 3항연산자로 최대거리를 계속 저장
                }
            }
        }       
        
        for(int d : dist){
            if(maxDist ==d) // 최대길이가 같다면 결과에 +1
                result++;
        }
        return result;
    }
}