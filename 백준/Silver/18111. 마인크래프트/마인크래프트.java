// https://github.com/KimYongJ/algorithm/tree/main
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    
    private static int N,M,B,arr[][];
    private static int[] result = new int[]{Integer.MAX_VALUE , 0}; // 순서 : 시간 , 땅의 높이
    
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max,arr[i][j]); // 높이 중 최댓 값
                min = Math.min(min,arr[i][j]); // 높이 중 최솟 값
            }
        }
        
        for(int i=min; i<=max; i++){// 높이의 최솟값 ~ 최댓값 사이에 대해 드는 시간을 구한다.
            int time = getTime(i); // 높이 i로 만들 때 걸리는 시간을 구하는 함수
            if(time<=result[0]){ // 시간이 더 짧을 경우 result 값 갱신 , 같을 경우도 갱신하는 이유는, 같은 시간일 때 높이가 높은 것이 답이기 때문.
                result[0] = time;
                result[1] = i;
            }
        }
        System.out.println(result[0]+" "+result[1]);
    }
    static public int getTime(int hight){
        int resultTime = 0;
        int block = B;
        // 블록 제거 후 인벤토리에 넣는 로직
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++){
                if(arr[i][j]>hight){ // 블록 제거 후 인벤토리에 넣는 부분
                    block += arr[i][j]-hight;
                    resultTime+= (arr[i][j]-hight)*2;
                }else if(arr[i][j]<hight){// 인벤토리에서 블록을 꺼내 위에 놓는 부분
                    block  -= (hight - arr[i][j]);
                    resultTime+=(hight - arr[i][j]);
                }
            }
        if(block<0){ // 블록이 0보다 작으면 만들 수 없는 높이이다.
            resultTime = Integer.MAX_VALUE;
        }
        return resultTime;
    }
    
}