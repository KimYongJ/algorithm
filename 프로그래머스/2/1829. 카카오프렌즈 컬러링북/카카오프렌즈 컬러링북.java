import java.util.ArrayDeque;    
// https://github.com/KimYongJ/algorithm
    /*
     * 문제 : 몇개의 영역이있는지, 가장 큰 영역의 넓이는 얼마인지 계산하는 프로그램을 작성해라.
     * 출력 : 영역의 갯수 , 가장 큰 영역의 값
     * 해설
     * 1) BFS로 너비 우선탐색 진행 
     * 2) 2번 방문할 필요 없으므로 같은 영역을 지날 때 해당 영역의 벨류를 0으로 한다. 이 때 0인것은 지나치도록 코딩
     * 3) 또한 영역의 넓이를 알기 위해 큐에 데이터를 넣을 때마다 영역의 크기를 더하도록 코딩 한다.
     * */
    class Solution {
    	
    	private int dx[] = {0,0,1,-1},
    			    dy[] = {1,-1,0,0},
    			    cntArea = 0,
    			    maxArea = -1;
    	private ArrayDeque<Picture>q = new ArrayDeque<>();
        public int[] solution(int m, int n, int[][] picture) {
            for(int y=0; y<m; y++)
            	for(int x=0; x<n; x++)
            		if(picture[y][x]!=0) {
            			q.add(new Picture(y,x));
            			int area = BFS(0,picture[y][x],m,n,picture);
            			maxArea = Math.max(maxArea, area);
            			cntArea++;
            		}
            return new int[] {cntArea,maxArea};
        }
        public int BFS(int area,int type,int ylen, int xlen, int[][] picture) {
        	while(!q.isEmpty()) { // 큐가 빌 때까지 반복 
        		Picture pic = q.poll();
        		for(int i=0; i<4; i++) {
        			int newY = pic.y + dy[i];
        			int newX = pic.x + dx[i];
        			
        			if(newY<0 || newY>=ylen || newX<0 || newX>=xlen || 
        					picture[newY][newX]==0 || picture[newY][newX]!=type) {
        				continue;
        			}
        			q.add(new Picture(newY,newX));
        			picture[newY][newX] = 0; // 방문한곳을 0으로 바꿔 더 이상 같은곳이 탐색되지 않도록 합니다.
        			area++; // 영역을 넓힐 때마다 area를 1씩 더 해주어 같은 영역의 크기가 얼마인지를 알도록 합니다. 
        		}
        	}
        	return area;
        }
        class Picture{
        	int y,x;
        	Picture(int y,int x){
        		this.y=y;
        		this.x=x;
        	}
        }
    }