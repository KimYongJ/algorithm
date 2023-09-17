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
        boolean flag =true;
        while(q.size()!=1){
            if(flag){
                q.poll();
                flag = false;
            }else{
                q.add(q.poll());
                flag = true;
            }
        }
        System.out.println(q.peek());
    }
}