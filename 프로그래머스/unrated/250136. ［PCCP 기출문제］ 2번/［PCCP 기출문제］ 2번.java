// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
class Solution {
    
    static int unique = 2;
    static HashMap<Integer, Integer> hm = new HashMap<>(); // 고유번호 : 석유 매장량
    static int ylen, xlen, max, land[][];
    static int dxy[][] = {{0,1},{0,-1},{1,0},{-1,0}};
    static ArrayDeque<int[]> q;  
    
    public int solution(int[][] land) {
        this.land = land;
        ylen = land.length; 
        xlen = land[0].length;
        
        // 먼저 land에 석유 매장량을 고유번호로 가득 채운다. 동시에 map에 고유번호에 따른 매장량을 담는다.
        for(int i=0; i<xlen; i++){// 가로 반복
            for(int j=0; j<ylen; j++){ // 세로 반복
                if(land[j][i] == 1){ // 석유가 있고, 방문하지 않았다면
                    BFS(j,i,1);// BFS 진행
                }
            }
        }
        
        for(int i=0; i<xlen; i++){
            boolean[] visit = new boolean[unique];
            int sum = 0;
            for(int j=0; j<ylen; j++){
                if(!visit[land[j][i]] && land[j][i]>1){
                    visit[land[j][i]] = true;
                    sum += hm.get(land[j][i]);
                }
            }
            max = Math.max(max, sum);
        }
        return max;
    }
    public void BFS(int y, int x, int sum){
        q = new ArrayDeque<>();
        q.add(new int[]{y,x});
        land[y][x] = unique;
        
        while(!q.isEmpty()){
            int[] node = q.poll();
            for(int[] xy : dxy){
                int x1 = node[1] + xy[0];
                int y1 = node[0] + xy[1];
                
                if(x1>=0 && y1>=0 && x1<xlen && y1<ylen &&
                  land[y1][x1] == 1){
                    land[y1][x1] = unique;
                    sum++;
                    q.add(new int[]{y1,x1});
                }
            }
        }
        
        hm.put(unique, sum);// 고유번호에 따른 석유량을 담는다.
        unique++; // 고유번호를 증가시킨다.
    }
}