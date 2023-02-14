import java.io.*;
import java.util.*;

class Main{
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args)throws Exception{
        int cnt=0,max=0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        char[][] arr = new char[x+2][y+2];
        Queue<int[]> q = new LinkedList<>();
        for(int i=1; i<=x; i++){
            String s = br.readLine();
            for(int j=1, index = 0; j<=y; j++,index+=2){
                arr[i][j] = s.charAt(index);
            }
        }
        for(int a=1; a<=x; a++)
            for(int b=1; b<=y; b++){
                if(arr[a][b]=='1'){
                	int max1 = 1;
                    cnt++;
                    q.add(new int[]{a,b});
                    arr[a][b]='0';
                    while(!q.isEmpty()){ 
                        int[] c = q.poll();
                        for(int i=0; i<4; i++){
                            int x1 = c[0]+dx[i];
                            int y1 = c[1]+dy[i];         
                            if(x1<1 || y1<1 || x1>x || y1>y || 
                                arr[x1][y1]=='0'){
                                continue;
                            }
                            q.add(new int[]{x1,y1});
                            arr[x1][y1]='0';
                            max1++;
                        }
                    }
                    max = max<max1 ? max1 : max;
                }
            }
        sb.append(cnt).append("\n").append(max);
        System.out.print(sb);
    }
}