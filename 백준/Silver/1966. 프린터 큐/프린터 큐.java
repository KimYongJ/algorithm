import java.util.ArrayDeque;
import java.util.Arrays;
class Main{
    public static void main(String[] args)throws Exception{
        StringBuilder sb = new StringBuilder();
        
        int T = readInt();
        
        while(T-->0){
            ArrayDeque<int[]> q = new ArrayDeque<>(); // 큐
            int n = readInt(); // 총 문서의 갯수
            int m = readInt(); // 몇번 째 것을 찾아야 하는지
            
            Integer[] prio = new Integer[n]; // 우선순위를 담을 배열, 추후 우선순위 비교시 사용
            for(int i=0; i<n; i++){
                int priority = readInt();
                prio[i] = priority; // 우선순위를 차례로 담는다.
                q.add(new int[]{i,priority}); // q에 기본 숫자인 i와 우선순위를 같이 담는다.
            }
            
            Arrays.sort(prio,(a,b)->b-a); // 우선순위 담은 배열을 내림차순으로 정렬
            
            int idx = 0;// prio 배열을 활용해 우선순위를 높은 것 부터 차례로 비교할 때 쓰이는 idx
            while(true){
                int[] data = q.poll();
                if(data[0]==m && prio[idx]==data[1]){ // 찾으려하는 기본숫자와 우선순위 가 같은지
                    sb.append(idx+1).append("\n");
                    break;
                }else if(prio[idx]==data[1]){
                    idx++;
                }else{
                    q.add(data);
                }
            }
            
        }
        System.out.println(sb.toString());
    }
    
    static int readInt() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
    
}
