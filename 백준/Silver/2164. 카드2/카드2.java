import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=n; i++){
            q.add(i);
        }
        while(q.size()!=1){
            q.poll();
            q.add(q.poll());
        }
        System.out.println(q.peek());
    }
}