import java.util.ArrayDeque;
import java.util.ArrayList;
class Solution {
    public int solution(int n, int[][] edge) {
        ArrayList<Integer>[] list = new ArrayList[n+1]; // 인접 리스트 담을 배열 선언
        
        for(int i=1; i<n+1; i++)
            list[i] = new ArrayList<>(); // 배열 초기화
        
        for(int[]node : edge){
            int Anode = node[0];
            int Bnode = node[1];
            list[Anode].add(Bnode); // 인접리스트를 저장
            list[Bnode].add(Anode); // 인접리스트를 저장
        }
        int maxLeng = 0;
        int[] dist = new int[n+1]; // 각 정점을 index로해 1부터의 거리를 담을 배열
        boolean[] visit = new boolean[n+1];// 각 정점을 index로해 해당 노드에 방문 유무 체크
        ArrayDeque<Integer> q = new ArrayDeque<>(); // BFS를 위한 큐 선언
        q.add(1); // 1을 만저 담는다.
        visit[1] = true; // 1 방문 체크 
        while(!q.isEmpty()){
            int nowNode = q.poll(); // 큐에서 데이터를 꺼낸다.
            for(int i=0; i<list[nowNode].size(); i++){ // 꺼낸 데이터의 인접리스트 갯수만큼 반복
                int vertex = list[nowNode].get(i);// 인접 정점을 꺼낸다
                if(!visit[vertex]){// 인접정점이 방문한 적 없다면 이하 실행
                    visit[vertex] = true; // 인접정점 방문처리
                    dist[vertex] = dist[nowNode] + 1;// 인접정점 거리 세팅
                    q.add(vertex); // 인접정점을 q에 담아서 인접정점의 인접정점을 계산토록함
                    maxLeng = maxLeng < dist[vertex] ? dist[vertex] : maxLeng;// 최대길이를 미리 구해 놓기 위해 3항 연산자 사용
                }    
            }
        }
        
        int result = 0;
        for(int d : dist)
            if(maxLeng == d) // 최대길이와 거리배열의 값이 같은 것들이 있다면 결과에 +1
                result++;
        
        return result;
    }
}