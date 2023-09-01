import java.util.ArrayList;
import java.util.ArrayDeque;
class Solution {
    private int result = 0;
    private int maxLength = 0;
    
    public int solution(int n, int[][] edge) {
        ArrayList<Integer>[] list = new ArrayList[n+1];
        
        for(int i=0; i<n+1; i++) 
            list[i] = new ArrayList<>();
        
        for(int[] node : edge){
            int aNode = node[0];
            int bNode = node[1];
            list[aNode].add(bNode);
            list[bNode].add(aNode);
        }
        int[] dist = new int[n+1]; // 각 노드당 거리를 저장할 배열 선언
        boolean[] visit = new boolean[n+1];// 방문했는지 확인을 위해 선언
        ArrayDeque<Integer> BFS = new ArrayDeque<>();
        visit[1]=true;
        BFS.add(1);
        while(!BFS.isEmpty()){
            int nowNode = BFS.poll();
            for(int i=0;i<list[nowNode].size(); i++){
                int num = list[nowNode].get(i);
                if(!visit[num]){
                    visit[num] = true;
                    BFS.add(num);
                    dist[num] = dist[nowNode]+1;
                    maxLength = maxLength<dist[num] ? dist[num] : maxLength;
                }
            }
        }
        
        for(int x : dist)
            if(maxLength == x) result++;
        
        return result;
    }
}