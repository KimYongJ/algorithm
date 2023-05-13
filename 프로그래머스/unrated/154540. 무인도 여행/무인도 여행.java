// https://github.com/KimYongJ
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
class Solution {
    static int[][] dxy = {{0,1},{0,-1},{1,0},{-1,0}};
    static ArrayDeque<int[]> q= new ArrayDeque<>();
    static ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> solution(String[] maps) {
        char[][] crr = new char[maps.length][maps[0].length()];
        for(int i=0; i<maps.length; i++)
            crr[i] = maps[i].toCharArray();
        
        for(int i=0; i<maps.length; i++)
            for(int j=0; j<maps[0].length(); j++)
                if(crr[i][j]!='X'){
                    bfs(crr,i,j);
                }

        if(list.size() == 0) 
            list.add(-1);
        
        Collections.sort(list);

        return list;
    }
    public void bfs(char[][] crr,int i, int j){
            int num = crr[i][j]-'0';
            q = new ArrayDeque<>();
            q.add(new int[]{i,j});
            crr[i][j] = 'X';
            while(!q.isEmpty()){
                int[] qData = q.poll();
                for(int[]xy : dxy){
                    int x = xy[0] + qData[0];
                    int y = xy[1] + qData[1];

                    if(x<0 || y<0 || x==crr.length || y==crr[0].length ||
                        crr[x][y]=='X')
                        continue;
                    num += crr[x][y]-'0';
                    q.add(new int[]{x,y});
                    crr[x][y] = 'X';
                }
            }
            if(num != 0) 
                list.add(num);
    }
}