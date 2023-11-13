// https://github.com/KimYongJ/algorithm
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args)throws Exception{
        PriorityQueue<Integer> p = new PriorityQueue<>();// 양수를 담을 것
        PriorityQueue<Integer> n = new PriorityQueue<>((a,b)->b-a);// 음수를 담을 것 음수는 내림차순
        
        StringBuilder sb = new StringBuilder();
        int N = read();
        while(N-->0){
            int num = read();
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
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }
}