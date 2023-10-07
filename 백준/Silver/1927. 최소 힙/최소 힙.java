// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        while(T-->0){
            
            int n = Integer.parseInt(br.readLine());
            
            if(n==0){
                if(!q.isEmpty()) n = q.poll();
                sb.append(n).append('\n');
            }else q.add(n);
            
        }
        
        System.out.print(sb);
    }
}