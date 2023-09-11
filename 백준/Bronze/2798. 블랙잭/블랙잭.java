import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
    private static int max, sum,result,arr[],len;
    private static boolean[] visit;
    public static void main(String[] args)throws Exception{
        BufferedReader br     = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st    = new StringTokenizer(br.readLine());
        len = Integer.parseInt(st.nextToken());
        result = 0;
        arr = new int[len];
        visit = new boolean[len];
        max = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<len; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        BRUT(0,0);
        
        System.out.println(result);
    }
    public static void BRUT(int depth,int sum){
        if(depth==3){
            if(max>=sum && result<sum){
                result = sum;
            }
            return;
        }
        for(int i=0; i<len;i++){
            if(!visit[i]){
                visit[i] = true;
                BRUT(depth+1,sum+arr[i]);
                visit[i] = false;
            }
        }
        
    }
}