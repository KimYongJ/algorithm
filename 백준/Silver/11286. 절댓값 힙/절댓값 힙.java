// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> p = new PriorityQueue<>();// 양수를 담을 것
        PriorityQueue<Integer> n = new PriorityQueue<>((a,b)->b-a);// 음수를 담을 것 음수는 내림차순
        
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while(N-->0){
            int num = Integer.parseInt(br.readLine());
            if(num==0){
                Integer num1 = p.peek();
                Integer num2 = n.peek();
                if(num1==null && num2==null) sb.append(0).append('\n');
                else if( num1==null && num2!=null ){
                    sb.append(num2).append('\n');
                    n.poll();
                }else if(num1!=null && num2==null){
                    sb.append(num1).append('\n');
                    p.poll();
                }else{
                    if(Math.abs(num2)<=num1){
                        sb.append(num2).append('\n');
                        n.poll();
                    }else{
                        sb.append(num1).append('\n');
                        p.poll();
                    }
                }
            }else if(num<0){
                n.add(num);
            }else{
                p.add(num);
            }
        }
        System.out.println(sb.toString());
    }
}