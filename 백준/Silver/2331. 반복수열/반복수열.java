// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main{
    
    public static void main(String[] args)throws Exception{
    	ArrayList<Integer> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        DFS(n, r, list);
    }
    public static void DFS(int n, int r, ArrayList<Integer> list){
        if(list.contains(n)){
            System.out.print(list.indexOf(n));
            return;
        }
        list.add(n);
        int result = 0;
        while(n != 0){
            result += (int)Math.pow(n%10,r);
            n/=10;
        }
        DFS(result, r, list);
    }
}