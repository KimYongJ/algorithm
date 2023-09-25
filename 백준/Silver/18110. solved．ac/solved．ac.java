// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> asc = new PriorityQueue<>();
        PriorityQueue<Integer> des = new PriorityQueue<>((a,b)->b-a);
        
        int T = Integer.parseInt(br.readLine());
        int per = (int)Math.round(T*0.15); // 15프로의 명수

        
        for(int i=0; i<T; i++){
            int num = Integer.parseInt(br.readLine());
            asc.add(num);
            des.add(num);
        }
        
        // 상,하위 15% 제외
        for(int i=0; i<per; i++){
            asc.poll();
            des.poll();
        }
        
        double sum = 0;
        
        for(int i=0; i<T-per*2; i++){
            sum += (asc.poll() + des.poll());
        }
        
        System.out.println(T==0 ? 0 : (int)Math.round((sum/2)/(T-per*2)));
        
    }
}