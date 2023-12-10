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
        arr = new int[R][C];
        visit = new int[R][C];
        for(int i=0; i<R; i++){
            String str = br.readLine();
            for(int j=0; j<C; j++)
                arr[i][j] = str.charAt(j)-'A'; // 숫자로 해당 문자를 변환해 넣음
        }
        
        DFS(0,0,(1<<arr[0][0]),1); // 방문한 문자를 bit로 연산한다. A부터 Z까지 26개이므로 26비트로 계산가능
        
        System.out.println(result);
    }
    public static void DFS(int i, int j, int visit_bit, int cnt){
    	if(visit[i][j] == visit_bit) return; // 해당 좌표에 해당 값이 똑같이 들어간적이 있다면 스킵
    	visit[i][j] = visit_bit;// 해당 좌표에 방문한 내역 저장
    	
        for(int xy[] : dxy){
            int newI = i + xy[0]; // 새로운 좌표 I 생성
            int newJ = j + xy[1]; // 새로운 좌표 J 생성
            if(newI>=0 && newJ >=0 && newI <R && newJ <C && (visit_bit & (1<<arr[newI][newJ]))==0) { // 좌표 유효성 검사, &연산자를 쓰는이유는 visit_bit와 해당 넣으려는 비트가 겹치는게 하나도 없어야 되기때문(그래야 방문안한것이니까)
                DFS(newI, newJ, (visit_bit | (1<<arr[newI][newJ])), cnt+1);// 새로 만든 좌표 전달, 좌표전달시 visit_bit에 해당 좌표의 비트 마킹처리
            }
        }
        result = Math.max(result, cnt);
    }
}