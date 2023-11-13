// https://github.com/KimYongJ/algorithm
import java.util.Comparator;
import java.util.PriorityQueue;

class Main{
    public static void main(String[] args)throws Exception{
        PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer a1, Integer a2){
                if(Math.abs(a1) == Math.abs(a2)) return a1-a2;
                return Math.abs(a1)-Math.abs(a2);
            }
        });
        StringBuilder sb = new StringBuilder();
        int N = read();
        while(N-->0){
            int num = read();
            if(num==0) 
                sb.append(q.isEmpty() ? 0 : q.poll()).append('\n');
            else q.add(num);
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