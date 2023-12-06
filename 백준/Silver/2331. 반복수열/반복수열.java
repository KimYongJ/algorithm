// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main{
    
    static ArrayList<Long> list = new ArrayList<>();
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long r = Long.parseLong(st.nextToken());
        DFS(n,r);
    }
    public static void DFS(long n, long r){
        if(list.contains(n)){
            System.out.print(list.indexOf(n));
            return;
        }
        list.add(n);
        long result = 0;
        while(n != 0){
            result += (long)Math.pow(n%10,r);
            n/=10;
        }
        DFS(result,r);
    }
}