// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.HashSet;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        HashSet<String> nSet = new HashSet<>(); // n개의 string담을 Set선언

        
        for(int i=0; i<n; i++)
            nSet.add(br.readLine()); // set에 문자열을 담는다.
        
        PriorityQueue<String> q = new PriorityQueue<>(); // 사전순 자동 정렬을 위한 우선순위 큐
        for(int i=0; i<m; i++){// m개를 돌면서 같은게 있는지 찾는다. 
            String str = br.readLine();
            if(nSet.contains(str)){
                q.add(str);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(q.size()).append("\n"); // 큐의 사이즈 즉, 듣보잡의 숫자 
        while(!q.isEmpty()){
            sb.append(q.poll()).append("\n"); // 오름차순 정렬된 string을 차례로 담는다. 
        }
        
        System.out.println(sb.toString());
       
    }
}
