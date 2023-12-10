// github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    
    static int R, C, arr[][];
    static int result = 1;
    static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        
        for(int i=0; i<R; i++){
            String str = br.readLine();
            for(int j=0; j<C; j++)
                arr[i][j] = str.charAt(j)-'A'; // 숫자로 해당 문자를 변환해 넣음
        }
        
        DFS(0,0,0,0); // 방문한 문자를 bit로 연산한다. A부터 Z까지 26개이므로 26비트로 계산가능
        
        System.out.println(result);
    }
    public static void DFS(int i, int j, int visit_bit, int cnt){
    	if((visit_bit | (1<<arr[i][j])) == visit_bit || result == 26) { // 방문한 비트면 종결
            if(result < cnt) {
            	result = cnt;
            }
            return;
    	}
    	visit_bit = (visit_bit | (1<<arr[i][j])); // 해당 비트 방문처리
        for(int xy[] : dxy){
            int newI = i + xy[0]; // 새로운 좌표 I 생성
            int newJ = j + xy[1]; // 새로운 좌표 J 생성
            if(newI>=0 && newJ >=0 && newI <R && newJ <C) { // 좌표 유효성 검사
                DFS(newI, newJ, visit_bit, cnt+1);// 새로 만든 좌표 전달
            }
        }
    }
}