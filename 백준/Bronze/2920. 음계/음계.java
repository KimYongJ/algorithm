import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean asc = true;
        boolean des = true;
        int n = Integer.parseInt(st.nextToken());
        for(int i=0; i<7; i++){
            int nextN = Integer.parseInt(st.nextToken());
            if(n-1 != nextN){
                des = false;
            }
            if(n+1 != nextN){
                asc = false;
            }
            if(!asc && !des)
                break;
            n = nextN;
        }
        if(asc){
            System.out.println("ascending");
        }else if(des){
            System.out.println("descending");
        }else{
            System.out.println("mixed");
        }
    }
}