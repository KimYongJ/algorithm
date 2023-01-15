import java.io.*;
import java.util.*;

class Main{
    static int n,m;
    static int[] arr = new int[9];
    static boolean[] check = new boolean[9];
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        func(0);
        
    }
    public static void func(int k){ // 현재 k개까지 수를 택함
        if(k==m){ // m개를 모두 택했으면
            for(int i=0; i<m; i++){
                System.out.print(arr[i]+" "); // arr에 기록해둔수를 출력
            }
            System.out.println();
            return;
        }
        
        for(int i=1; i<=n; i++){ // 1부터 n까지의 수에 대해 
            if(!check[i]){// 아직 i가 사용되지 않았으면
                arr[k] = i; // k번째 수를 i로 한다.
                check[i] = true; //i를 사용했다고 표시
                func(k+1); // 다음수를 정하러 한단계 더 들어감
                check[i] = false; // k번째 수를 i로 정한 모든 
                //경우에 대해 다 확인했으니 i를 사용하지 않았다 명시해야됨
            }
        }
        
    }
}