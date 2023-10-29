// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->b-a);
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while(n-->0){
            int num = Integer.parseInt(br.readLine());
            if(num==0){
                if(!q.isEmpty())
                    num = q.poll();
                sb.append(num).append('\n');
            }else q.add(num);
        }
        System.out.print(sb.toString());
    }
}