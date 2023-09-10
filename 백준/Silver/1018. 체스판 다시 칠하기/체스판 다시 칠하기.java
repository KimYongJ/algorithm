import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    private static String[] arr;
    private static String[] WorB = {"WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW"
                             ,"WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW"
                              ,"WBWBWBWB"};
    // 9개가 선언되있다. y인덱스를 0으로 시작시 W단어로 시작, y인덱스를 1로 세팅시B로시작
    private static int min = Integer.MAX_VALUE;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        arr = new String[y];
        
        for(int i=0; i<y; i++){
            arr[i] = br.readLine();
        }
        
        for(int i=0; i<=y-8; i++){// y좌표 하나씩 증가
            for(int j=0; j<=x-8; j++){// x좌표 하나씩 증가
                //증가한 좌표대로 최소 값 체크 
                find(i,j,0);
                find(i,j,1);
            }
        }
        System.out.println(min);
    }
    public static void find(int i, int j,int startIdex){
        int cnt = 0;
        int indexX = 0;
        int indexY = startIdex;
        for(int y=i; y<i+8; y++){
            for(int x=j; x<j+8; x++){
                if(arr[y].charAt(x)!=WorB[indexY].charAt(indexX++))
                    cnt++;
            }
            indexX=0;
            indexY++;
        }
        min = cnt < min ? cnt : min;
    }
}