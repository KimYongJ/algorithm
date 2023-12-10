// github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    
    static int R, C, arr[][];
    static int result = 0;
    static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
    static int visit[][];
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R+2][C+2];
        visit = new int[R+2][C+2];
        for(int i=1; i<=R; i++){
            String str = br.readLine();
            for(int j=1; j<=C; j++)
                arr[i][j] = str.charAt(j-1)-'A'; // 숫자로 해당 문자를 변환해 넣음
        }
        for(int j=0; j<C+2; j++) { // 상 하에 1,1좌표 값으로 패딩을 넣어 불필요한 좌표 유효성검증을 없앰
        	arr[0][j] = arr[1][1];
        	arr[R+1][j] = arr[1][1];
        }
        for(int i=0; i<R+2; i++) { // 좌 우에 1,1좌표 값으로 패딩을 넣어 불필요한 좌표 유효성검증을 없앰
        	arr[i][0] = arr[1][1];
        	arr[i][C+1] = arr[1][1];
        }
        
        DFS(1,1,(1<<arr[0][0]),1); // 방문한 문자를 bit로 연산한다. A부터 Z까지 26개이므로 26비트로 계산가능
        
        System.out.println(result);
    }
    public static void DFS(int i, int j, int visit_bit, int cnt){
    	if(visit[i][j] == visit_bit) return; // 해당 좌표에 해당 값이 똑같이 들어간적이 있다면 스킵
    	visit[i][j] = visit_bit;
        for(int xy[] : dxy){
            int newI = i + xy[0]; // 새로운 좌표 I 생성
            int newJ = j + xy[1]; // 새로운 좌표 J 생성
            if((visit_bit & (1<<arr[newI][newJ]))== 0) // &연산자를 쓰는이유는 visit_bit와 해당 넣으려는 비트가 겹치는게 하나도 없어야 되기때문(그래야 방문안한것이니까)
            	DFS(newI, newJ, (visit_bit | (1<<arr[newI][newJ])), cnt+1);// 새로 만든 좌표 전달, 좌표전달시 visit_bit에 해당 좌표의 비트 마킹처리
        }
        result = Math.max(result, cnt);
    }
}