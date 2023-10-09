// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
class Solution {
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        Loop : 
        for(int a=0; a<5; a++){
            
            char[][] arr = new char[5][5];
            for(int i=0; i<5; i++)
                arr[i] = places[a][i].toCharArray();

            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    if(arr[i][j]=='P'){                 // 응시자의 좌표라면 아래 실행
                        if(validate(arr,i,j)){  //응시자의 맨해튼 거리 안에 다른 응시자가 있는데 파티션이 없다면 아래 실행
                            answer[a] = 0;
                            continue Loop;
                        }
                    }
                }
            }
            answer[a] = 1;
        }
        
        return answer;
    }
    public int[][] xy = {{0,1},{1,0},{-1,0},{0,-1}};  // BFS 진행시 플러스할 좌표들
    
    public boolean validate(char[][] arr, int i, int j){
        boolean[][] visit = new boolean[5][5];  // 방문 표시할 visit 배열
        ArrayDeque<int[]> q = new ArrayDeque<>();   // BFS진행할 큐 선언
        q.add(new int[]{i,j});                      // 초기 값 세팅
        visit[i][j] = true;                         // 초기 값 방문 세팅
        
        while(!q.isEmpty()){
            
            int[] p = q.poll();                     // 좌표를 하나 꺼낸다
            
            for(int a=0; a<4; a++){                 // 좌표의 상하 좌우 반복
                
                int y = p[0] + xy[a][0];
                int x = p[1] + xy[a][1];
                
                if(y<0 || x<0 || y>=5 || x>=5 || arr[y][x]=='X' ||visit[y][x] ||
                    (Math.abs(i-y) + Math.abs(j-x))>2
                  ){ // 기준 좌표에서 새로 만든 좌표가 맨해튼 거리 이상이거나, 새로 만든 좌표가 범위를 벗어나거나, 방문한적이 있을 때 탐색 중단(continue)
                    continue;
                }
                if(arr[y][x]=='P'){                 // 좌표안에 응시자가 있을 경우 true리턴
                    return true;
                }
                visit[y][x] = true;
                q.add(new int[]{y,x});
            }
        }
        
        return false;                               // 주변에 응시자가 없다면 마지막으로 false 리턴
    }
}