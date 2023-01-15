import java.io.*;

class Main{
    static int n, cnt = 0;
    static boolean[] use1 = new boolean[29];
    static boolean[] use2 = new boolean[29];
    static boolean[] use3 = new boolean[29];
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        back(0);
        System.out.println(cnt);
    }
    public static void back(int depth){
        if(n==depth){
            cnt++;
            return;
        }
        for(int i=0; i<n; i++){
            if(use1[i] || use2[i+depth] || use3[depth-i+n-1])
                continue;
            use1[i] = true;
            use2[i+depth] = true;
            use3[depth-i+n-1] = true;
            back(depth+1);
            use1[i] = false;
            use2[i+depth] = false;
            use3[depth-i+n-1] = false;
        }
    }
}