import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> asc = new PriorityQueue<>();
        
        int T = readInt();
        int per = (int)Math.round(T*0.15); // 15프로의 명수
        int len = T-per-per; // 평균을 구할 명수
        
        for(int i=0; i<T; i++) // 우선순위 큐에 데이터를 넣는다.
            asc.add(readInt());
        
        // 하위 15% 제외
        for(int i=0; i<per; i++)
            asc.poll();
        
        float sum = 0;
        
        for(int i=0; i<len; i++)
            sum += asc.poll();
        
        System.out.println(T==0 ? 0 : (int)Math.round(sum/len));
    }
    
    static int readInt() throws Exception {
        int n = 0;
        while (true) {
            int input = System.in.read();
            if (input <= 32)
                return n;
            else
                n = (n << 3) + (n << 1) + (input & 15);
        }
    }
    
}
