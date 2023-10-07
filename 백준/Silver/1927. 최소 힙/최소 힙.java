// https://github.com/KimYongJ/algorithm
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args)throws Exception{
        
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        StringBuilder sb = new StringBuilder();
        
        int T = read();
        
        while(T-->0){
            
            int n = read();
            
            if(n==0){
                sb.append(q.isEmpty() ? n : q.poll()).append('\n');
            }else q.add(n);
            
        }
        
        System.out.print(sb);
    }
    static int read() throws Exception {
        int n = 0;
        boolean isNegative = false;
        while (true) {
            int input = System.in.read();
            if (input <= 32) {
                return isNegative ? n * -1 : n;
            } else if (input == '-')
                isNegative = true;
            else
                n = (n << 3) + (n << 1) + (input & 15);
        }
     }
}
